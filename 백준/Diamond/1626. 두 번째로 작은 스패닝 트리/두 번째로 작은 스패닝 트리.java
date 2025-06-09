//https://www.acmicpc.net/problem/1626
//7 12//정점의 수 (1 ≤ 50,000)와 간선의 수 (1 ≤ 200,000)
//1 2 8// 간선수만큼 노드 2개와 가중치(1<=100,000)가 들어옴
//1 3 5
//2 3 10
//2 4 2
//2 5 18
//3 4 3
//3 6 16
//4 5 12
//4 6 30
//4 7 14
//5 7 4
//6 7 26
//두번째로 작은 스패닝 트리의 값 출력 : 44
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
class Main{
	
	static int N, E;
	static boolean isMst[];
	static List<Node> adList[];
	static List<Edge> edgeList;
	static DSU dsu;
	static HLD hld;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//정점의 수 (1 ≤ 50,000)
		E = Integer.parseInt(st.nextToken());//간선의 수 (1 ≤ 200,000)
		edgeList = new ArrayList<>();
		dsu = new DSU(N);
		adList = new ArrayList[N + 1];
		isMst = new boolean[E + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=0; i<E; i++)
		{
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(n1, n2, dist));
		}
		
		Collections.sort(edgeList);
		
		long edgeSum = 0;
		int edgeCnt = 1;
		
		for(int i=0; i<edgeList.size() && edgeCnt != N; i++)
		{
			Edge now = edgeList.get(i);
			if(dsu.union(now.n1, now.n2))
			{
				edgeCnt += 1;
				edgeSum += now.dist;
				adList[now.n1].add(new Node(now.n2, now.dist));
				adList[now.n2].add(new Node(now.n1, now.dist));
				isMst[i] = true;
			}
		}
		
		if(edgeCnt != N)// mst를 못만들 경우 -1 출력
		{
			System.out.print(-1);
			return;
		}
		
		hld = new HLD(N, adList);
		
		long res = 1L<<60;
		for(int i=0; i<edgeList.size(); i++)
		{
			if(isMst[i])// mst 간선은 연산하지 않음
				continue;
			
			Edge now = edgeList.get(i);
			// 추가할 간선의 두 노드의 MST내의 경로 중, 가장 큰 값과 두번째 큰 값을 가져옴
			Obj o = hld.find(now.n1, now.n2);
			// 현재 간선을 추가하고, 두 노드 경로중 가장 큰 가중치를 삭제할 때
			long nextSum = edgeSum + now.dist - o.max1;
			// 총 값과 같다면 두번 째로 큰 가중치를 삭제
			if(edgeSum == nextSum && o.max2 != -1)
			{
				nextSum = edgeSum + now.dist - o.max2;
			}
			// MST보다 무조건커야 한다.
			if(edgeSum < nextSum)
			{
				res = Math.min(res, nextSum);
			}
		}
		
		System.out.print(res == (1L<<60) ? -1 : res);
		
	}
	static class HLD{
		
		int N;
		int idx;
		int segIdx[];
		int chainLevel[];
		int chainParent[];
		int chainHeader[];
		Obj tree[];
		Obj result[];
		Obj dummy;
		List<Node> adList[];
		
		HLD(int N, List<Node> adList[]){
			this.N = N;
			this.adList = adList;
			this.segIdx = new int[N + 1];
			this.chainLevel = new int[N + 1];
			this.chainParent = new int[N + 1];
			this.chainHeader = new int[N + 1];
			this.chainHeader[1] = 1;
			this.tree = new Obj[N * 4];
			this.result = new Obj[N * 4];
			this.dummy = new Obj(-1,-1);
			// 트리 초기화
			for(int i=0; i<tree.length; i++)
			{
				tree[i] = new Obj(0,0);
				result[i] = new Obj(0,0);
			}
			// HLD를 위해 무거운 자식노드를 인접리스트의 맨 앞으로 옮김
			setChild(1, 0, new int[N + 1]);
			// HLD를 위해 체인 정보 세팅 및 노드 -> 세그인덱스 변환 진행
			setHLD(1, 0);
		}
		Obj find(int n1, int n2)
		{
			Obj res = new Obj(-1, -1);
			while(chainHeader[n1] != chainHeader[n2])
			{
				if(chainLevel[n1] > chainLevel[n2])
				{
					int tmp = n1;
					n1 = n2;
					n2 = tmp;
				}
				res.comp(query(1, 1, N, segIdx[chainHeader[n2]], segIdx[n2]) );
				n2 = chainParent[n2];
			}
			if(segIdx[n1] > segIdx[n2])
			{
				int tmp = n1;
				n1 = n2;
				n2 = tmp;
			}
			// 간선 정보는 자식노드에 저장되어 있으므로 탐색은 LCA + 1부터 시작한다.
			res.comp(query(1, 1, N, segIdx[n1] + 1, segIdx[n2]) );
			
			return res;
		}
		Obj query(int treeNode, int s, int e, int left, int right) {
			if(e < left || right < s)
				return dummy;
			if(left <= s && e <= right)
				return tree[treeNode];
			
			int mid = (s + e) >> 1;
			
			Obj l = query(treeNode << 1, s, mid, left, right);
			Obj r = query(treeNode << 1 | 1,mid + 1, e, left, right);
			
			merge(result[treeNode], l, r);
			
			return result[treeNode];
		}
		void update(int treeNode, int s, int e, int targetIdx, int dist) {
			if(e < targetIdx || targetIdx < s)
				return;
			if(s == e)
			{
				tree[treeNode].max1 = dist;
				tree[treeNode].max2 = -1;
				return;
			}
			
			int mid = (s + e) >> 1;
			
			if(targetIdx <= mid)
				update(treeNode << 1, s, mid, targetIdx, dist);
			else
				update(treeNode << 1 | 1, mid + 1, e, targetIdx, dist);
			
			merge(tree[treeNode], tree[treeNode << 1], tree[treeNode << 1 | 1]);
		}
		void merge(Obj res, Obj l, Obj r) {
	        int max = Math.max(l.max1, r.max1);
	        int min = Math.min(l.max1, r.max1);
	        res.max1 = max;
	        res.max2 = -1;
	        
	        if(min != res.max1)
	        	res.max2 = min;
	        
	        res.max2 = Math.max(res.max2, Math.max(l.max2, r.max2));
	    }
		void setHLD(int nowNode, int level)
		{
			segIdx[nowNode] = ++idx;
			chainLevel[nowNode] = level;
			for(int i=0; i<adList[nowNode].size(); i++)
			{
				Node next = adList[nowNode].get(i);
				if(chainHeader[next.node] != 0)// 이미 방문했으면 스킵
					continue;
				if(i == 0)// 무거운 자식노드일 경우 체인 유지
				{
					chainHeader[next.node] = chainHeader[nowNode];
					chainParent[next.node] = chainParent[nowNode];
					setHLD(next.node, level);
				}
				else
				{
					// 가벼윤 자식노드인 경우 새로운 체인 시작
					chainHeader[next.node] = next.node;// 새 체인의 헤더는 자기 자신
					chainParent[next.node] = nowNode;// 이전 체인으로 점프하기 위해 이전 체인 정보 저장
					setHLD(next.node, level + 1);// 레벨 + 1
				}
				update(1, 1, N, segIdx[next.node], next.dist);
			}
		}
		void setChild(int nowNode, int parentNode, int size[]) {
			int heavyIdx = 0;
			int heavySize = 0;
			size[nowNode] = 1;
			for(int i=0; i<adList[nowNode].size(); i++)
			{
				Node next = adList[nowNode].get(i);
				if(next.node == parentNode)
					continue;
				
				setChild(next.node, nowNode, size);
				
				size[nowNode] += size[next.node];
				
				if(heavySize < size[next.node])
				{
					heavySize = size[next.node];
					heavyIdx = i;
				}
			}
			if(adList[nowNode].size() > 0)
				Collections.swap(adList[nowNode], heavyIdx, 0);
		}
	}
	static class DSU{
		int N;
		int parent[];
		DSU(int N){
			this.N = N;
			this.parent = new int[N + 1];
			for(int i=1; i<=N; i++)
				parent[i] = i;
		}
		boolean union(int n1, int n2) {
			int p1 = find(n1);
			int p2 = find(n2);
			if(p1 == p2)
				return false;
			
			if(parent[p1] < parent[p2])
				parent[p2] = p1;
			else
				parent[p1] = p2;
			
			return true;
		}
		int find(int node) {
			if(parent[node] == node) return node;
			return parent[node] = find(parent[node]);
		}
	}
	static class Edge implements Comparable<Edge>{
		int n1, n2, dist;
		Edge(int n1, int n2, int dist){
			this.n1=n1;
			this.n2=n2;
			this.dist=dist;
		}
		@Override
		public int compareTo(Edge o) {
			return dist - o.dist;
		}
	}
	static class Node{
		int node, dist;
		Node(int node, int dist){
			this.node = node;
			this.dist = dist;
		}
	}
	static class Obj{
		int max1, max2;
		Obj(int m1, int m2){
			this.max1 = m1;
			this.max2 = m2;
		}
		void comp(Obj o) {
            int max = Math.max(max1, o.max1);
            int min = Math.min(max1, o.max1);
            max1 = max;
            
            if(min != max)
            	max2 = Math.max(max2, Math.max(min,o.max2));
		}
	}
}