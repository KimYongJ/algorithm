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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static List<Edge> edgeList;
	static List<Node> adList[];
	static DSU dsu;
	static HLD hld;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//정점 개수(2 ≤ 200,000)
		M = Integer.parseInt(st.nextToken());//간선 개수(N-1 ≤ 200,000)
		edgeList = new ArrayList<>();
		adList = new ArrayList[N + 1];
		dsu = new DSU(N);
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(n1, n2, dist, i));
		}
		
		Collections.sort(edgeList);
		
		int edgeCnt = 1;
		long edgeSum = 0;
		
		for(int i=0; i<edgeList.size() && edgeCnt != N; i++)
		{
			Edge now = edgeList.get(i);
			if(dsu.union(now.n1, now.n2))
			{
				edgeCnt += 1;
				edgeSum += now.dist;
				adList[now.n1].add(new Node(now.n2, now.dist));
				adList[now.n2].add(new Node(now.n1, now.dist));
			}
		}
		
		hld = new HLD(N, adList);
		
		long res[] = new long[M];
		
		for(int i=0; i<edgeList.size(); i++)
		{
			Edge now = edgeList.get(i);
			
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
			setChild(1, 0, new int[N + 1]);
			setHLD(1, 0);
		}
		int find(int n1 , int n2) {
			
			int max = 0;
			
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
		void setHLD(int nowNode, int level) {
			segIdx[nowNode] = ++idx;
			chainLevel[nowNode] = level;
			for(int i=0; i<adList[nowNode].size(); i++)
			{
				Node next = adList[nowNode].get(i);
				
				if(chainHeader[next.node] != 0)
					continue;
				
				if(i == 0)
				{
					chainHeader[next.node] = chainHeader[nowNode];
					chainParent[next.node] = chainParent[nowNode];
					setHLD(next.node, level);
				}
				else
				{
					chainHeader[next.node] = next.node;
					chainParent[next.node] = nowNode;
					setHLD(next.node, level + 1);
				}
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
				
				size[nowNode] += size[next.node];
				if(heavySize < size[next.node])
				{
					heavySize = size[next.node];
					heavyIdx = i;
				}
			}
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
			if(parent1 == parent2)
				return false;
			
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
}