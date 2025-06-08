//https://www.acmicpc.net/problem/15481
//2초 512MB
//5 8//정점 개수(2 ≤ 200,000), 간선 개수(N-1 ≤ 200,000)
//1 2 5// 노드 두개와 가중치(1<=10^9)가 주어짐
//2 3 4
//1 3 2
//3 4 8
//4 5 3
//3 5 6
//1 4 9
//2 5 1
// 각 간선마다 그 간선을 포함하는 최소 스패닝 트리의 가중치 합 출력
//11
//10
//10
//14
//10
//12
//15
//10

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main{
	
	static int N, M;
	static List<Edge> edgeList;
	static List<Node> adList[];
	static DSU dsu;
	static HLD hld;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();//정점 개수(2 ≤ 200,000)
		M = in.nextInt();//간선 개수(N-1 ≤ 200,000)
		edgeList = new ArrayList<>();
		adList = new ArrayList[N + 1];
		dsu = new DSU(N);// Union Find를 위한 클래스
		// 인접 리스트 초기화
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++)
		{
			int n1 = in.nextInt();
			int n2 = in.nextInt();
			int dist = in.nextInt();
			edgeList.add(new Edge(n1, n2, dist, i));// 간선의 정보를 담음
		}
		// 크루스칼 알고리즘을 위한 정렬
		Collections.sort(edgeList);
		
		int edgeCnt = 1;// 조기 종료를 위한 간선 카운팅 변수
		long edgeSum = 0;// 최소 스패닝 트리의 총합
		
		for(int i=0; i<edgeList.size() && edgeCnt != N; i++)
		{
			Edge now = edgeList.get(i);
			if(dsu.union(now.n1, now.n2))// 처음 연결하는 경우만 이하 연산 실행
			{
				edgeCnt += 1;// 간선 개수 추가
				edgeSum += now.dist;// 최소 스패닝 트리 간선 가중치 추가
				adList[now.n1].add(new Node(now.n2, now.dist));// 인접리스트 생성
				adList[now.n2].add(new Node(now.n1, now.dist));// 인접리스트 생성
			}
		}
		//HLD 알고리즘을 위한 클래스 초기화
		hld = new HLD(N, adList);
		
		long res[] = new long[M];
		
		for(int i=0; i<edgeList.size(); i++)
		{
			Edge now = edgeList.get(i);
			// 최소 스패닝 트리의 총합 + 현재 추가하려는 간선 - 트리상에서 n1,n2 노드 사이 가장 큰 간선
			res[now.idx] = edgeSum + now.dist - hld.find(now.n1, now.n2);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(long r : res)
			sb.append(r).append('\n');
		
		System.out.print(sb);
	}
	static class HLD{
		
		int N;
		int idx;
		int segIdx[];
		int chainLevel[];
		int chainHeader[];
		int chainParent[];
		int maxTree[];
		List<Node> adList[];
		
		HLD(int N, List<Node> adList[]){
			this.N = N;
			this.adList = adList;
			this.segIdx = new int[N + 1];
			this.chainLevel = new int[N + 1];
			this.chainHeader = new int[N + 1];
			this.chainParent = new int[N + 1];
			this.maxTree = new int[N * 4];
			this.chainHeader[1] = 1;
			// HLD 알고리즘을 위해 무거운 자식노드를 인접리스트의 맨 앞으로 이동
			setChild(1, 0, new int[N + 1]);
			// HLD 알고리즘을 위해 체인 정보 입력 및 노드번호 -> seg인덱스 변환 진행
			setHLD(1, 0);
		}
		int find(int n1 , int n2)// n1, n2 노드 사이 가장큰 간선 가중치를 반환하는 함수
		{
			int max = 0;
			// header가 같아질 때 까지 레벨이 높은 것을 낮게 올린다.
			while(chainHeader[n1] != chainHeader[n2])
			{
				if(chainLevel[n1] > chainLevel[n2])
				{
					int tmp = n1;
					n1 = n2;
					n2 = tmp;
				}
				max = Math.max(max, query(1, 1, N, segIdx[chainHeader[n2]], segIdx[n2]));
				n2 = chainParent[n2];
			}
			
			if(segIdx[n1] > segIdx[n2])
			{
				int tmp = n1;
				n1 = n2;
				n2 = tmp;
			}
			// 간선 가중치는 자식 노드에 저장되어 있으므로 LCA + 1부터 n2까지 탐색
			return Math.max(max, query(1, 1, N, segIdx[n1] + 1, segIdx[n2]));
		}
		int query(int treeNode, int s, int e, int left, int right) {
			if(e < left || right < s)
				return 0;
			
			if(left <= s && e <= right)
				return maxTree[treeNode];
			
			int mid = (s + e) >> 1;
			
			return Math.max(
						query(treeNode << 1, s, mid, left, right),
						query(treeNode << 1 | 1, mid + 1, e, left, right)
					);
		}
		void update(int treeNode, int s, int e, int targetIdx, int value) {
			if(e < targetIdx || targetIdx < s)
				return;
			
			if(s == e)
			{
				maxTree[treeNode] = value;
				return;
			}
			
			int mid = (s + e) >> 1;
			
			if(targetIdx <= mid)
				update(treeNode << 1, s, mid, targetIdx, value);
			else
				update(treeNode << 1 | 1, mid + 1, e, targetIdx, value);
			
			maxTree[treeNode] = Math.max(maxTree[treeNode << 1], maxTree[treeNode << 1 | 1]);
		}
		void setHLD(int nowNode, int level)
		{
			segIdx[nowNode] = ++idx;
			chainLevel[nowNode] = level;
			for(int i=0; i<adList[nowNode].size(); i++)
			{
				Node next = adList[nowNode].get(i);
				
				if(chainHeader[next.node] != 0)// 이미 방문한 경우 스킵
					continue;
				
				if(i == 0)// 무거운 자식노드 인경우 체인 정보 유지
				{
					chainHeader[next.node] = chainHeader[nowNode];
					chainParent[next.node] = chainParent[nowNode];
					setHLD(next.node, level);
				}
				else// 가벼운 자식노드인 경우 체인 정보 새롭게 시작
				{
					chainHeader[next.node] = next.node;// 체인의 헤더는 자기자신
					chainParent[next.node] = nowNode;// 이전 체인 점프를 위한 이전 체인의 노드 정보 저장
					setHLD(next.node, level + 1);// 레벨 증가
				}
				// 자식 노드에 간선의 정보 업데이트 처리
				update(1, 1, N, segIdx[next.node], next.dist);
			}
		}
		void setChild(int nowNode, int parentNode, int size[]) {
			int heavySize = 0;
			int heavyIdx = 0;
			size[nowNode] = 1;
			for(int i=0; i<adList[nowNode].size(); i++)
			{
				Node next = adList[nowNode].get(i);
				
				if(next.node == parentNode)
					continue;
				
				setChild(next.node, nowNode, size);
				// nowNode의 사이즈를 지속적으로 추가해나감
				size[nowNode] += size[next.node];
				// 무거운 자식노드의 인덱스를 구해나간다.
				if(heavySize < size[next.node])
				{
					heavySize = size[next.node];
					heavyIdx = i;
				}
			}
			// 무거운 자식노드를 맨앞으로 교체(swap)
			if(adList[nowNode].size() > 0)
				Collections.swap(adList[nowNode],0, heavyIdx);
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
			int parent1 = find(n1);
			int parent2 = find(n2);
			if(parent1 == parent2)// 이미 연결되어있으면 false
				return false;
			
			// 새연결이면 true반환
			if(parent[parent1] < parent[parent2])
				parent[parent2] = parent1;
			else
				parent[parent1] = parent2;
			
			return true;
		}
		int find(int node) {
			if(parent[node] == node) return node;
			return parent[node] = find(parent[node]);
		}
	}
	static class Node{
		int node, dist;
		Node(int n, int d){
			node = n;
			dist = d;
		}
	}
	static class Edge implements Comparable<Edge>{
		int n1, n2, dist, idx;
		Edge(int n1, int n2, int dist, int idx){
			this.n1 = n1;
			this.n2 = n2;
			this.dist = dist;
			this.idx = idx;
		}
		@Override
		public int compareTo(Edge o) {
			return dist - o.dist;
		}
	}
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;
	
	    int nextInt() throws Exception {
	        int n = 0;
	        byte c;
	        boolean isMinus = false;
	        while ((c = read()) <= 32) { if (size < 0) return -1; }
	        if (c == 45) { c = read(); isMinus = true; }
	        do n = (n << 3) + (n << 1) + (c & 15);
	        while (isNumber(c = read()));
	        return isMinus ? ~n + 1 : n;
	    }
	    boolean isNumber(byte c) {
	        return 47 < c && c < 58;
	    }
	    byte read() throws Exception {
	        if (index == size) {
	            size = System.in.read(buffer, index = 0, SIZE);
	            if (size < 0) buffer[0] = -1;
	        }
	        return buffer[index++];
	    }
	}
}