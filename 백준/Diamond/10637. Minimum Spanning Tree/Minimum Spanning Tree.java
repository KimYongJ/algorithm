//https://www.acmicpc.net/problem/10637
//3초 256MB
//4 6// 노드 수(2≤100,000), 간선 수(1≤ 200,000)
//1 2 2// 노드 번호 2개와 가중치 (0<=1,000,000)가 주어집니다.
//1 3 6
//1 4 3
//2 3 1
//2 4 4
//3 4 5
//순서대로 각 간선이 없어질 때 MST의 가중치의 합 출력
//8
//6
//7
//10
//6
//6

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main{
	
	static final int INF = 1<<30;
	static int N, M;
	static boolean isMst[];
	static List<Node> adList[];
	static List<Edge> edgeList;
	static DSU dsu;
	static HLD hld;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		Reader in = new Reader();
		
		N = in.nextInt();// 노드 수(2≤100,000)
		M = in.nextInt();// 간선 수(1≤200,000)
		isMst = new boolean[M];
		edgeList = new ArrayList<>();
		dsu = new DSU(N);
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		// 간선 정보 입력 받기
		for(int i=0; i<M; i++)
		{
			int n1 = in.nextInt();
			int n2 = in.nextInt();
			int weight = in.nextInt();
			edgeList.add(new Edge(n1, n2, weight, i));
		}
		// MST를 구하기 위한 정렬
		Collections.sort(edgeList);
		
		long weightSum = 0;
		int edgeCnt = 1;
		// MST 간선을 연결하며 , 가중치의 총합 및 간선연결 개수를 체크 
		for(int i=0; i<edgeList.size(); i++)
		{
			Edge now = edgeList.get(i);
			if(dsu.union(now.n1, now.n2))
			{
				weightSum += now.weight;
				edgeCnt += 1;
				isMst[now.idx] = true;
				adList[now.n1].add(new Node(now.n2, now.weight));
				adList[now.n2].add(new Node(now.n1, now.weight));
			}
		}
		
		// 초기 간선 자체가 mst가 아닌 경우 -1 출력후 바로 종료
		if(edgeCnt != N)
		{
			for(int i=0; i<M; i++)
				sb.append(-1).append('\n');
			System.out.print(sb);
			return;
		}
		// HLD알고리즘을 위한 클래스 초기화 
		hld = new HLD(N, INF, adList);
		
		// MST에 포함되지 않은 간선들을 순회하며 그 간선의 가중치를, MST경로 상에서 업데이트 처리
		for(int i=0; i<edgeList.size(); i++)
		{
			Edge now = edgeList.get(i);
			if(isMst[now.idx])
				continue;
			// 두 노드 사이를 mst바깥 간선의 가중치로 업데이트(최솟값 세그먼트 트리)
			hld.updateRange(now.n1, now.n2, now.weight);
		}
		
		long res[] = new long[M];
		
		for(int i=0; i<edgeList.size(); i++)
		{
			Edge now = edgeList.get(i);
			// MST에 포함되지 않은 간선이면 원래 MST값 출력
			if(!isMst[now.idx])
			{
				res[now.idx] = weightSum; 
				continue;
			}
			// MST에 포함된 간선이 없어지는거면, 해당 두 노드 사이에 전에 저장한 최솟값을 하나 가져옴
			int min = hld.find(now.n1, now.n2);
			// 경로가 없어서 INF면 -1을 출력하고 있다면, 현재 간선을 지우고 가져온 간선을 넣음
			res[now.idx] =  min == INF ? -1 : weightSum - now.weight + min;
		}
		// 결과 출력
		for(long r: res)
			sb.append(r).append('\n');
		
		System.out.print(sb);
	}
	static class HLD{
		
		int INF;
		int N;
		int idx;
		int segIdx[];
		int chainLevel[];
		int chainHeader[];
		int chainParent[];
		int minTree[];
		int lazy[];
		List<Node> adList[];
		
		HLD(int N, int inf, List<Node> adList[]){
			this.N = N;
			this.INF = inf;
			this.adList = adList;
			this.segIdx = new int[N + 1];
			this.chainLevel = new int[N + 1];
			this.chainHeader = new int[N + 1];
			this.chainParent = new int[N + 1];
			this.minTree = new int[N * 4];
			this.lazy = new int[N * 4];
			this.chainHeader[1] = 1;
			this.chainParent[1] = 1;
			// 최솟값 세그먼트 트리를 위해 INF로 모두 초기화
			for(int i=0; i<minTree.length; i++)
				lazy[i] = minTree[i] = INF;
			// HLD를 위한 무거운 자식노드의 인접위치 변경
			setChild(1, 0, new int[N + 1]);
			// HLD를 위한 체인 정보 세팅
			setHLD(1, 0);
		}
		int find(int n1, int n2)
		{
			int min = INF;
			while(chainHeader[n1] != chainHeader[n2])
			{
				if(chainLevel[n1] > chainLevel[n2])
				{
					int tmp = n1;
					n1 = n2;
					n2 = tmp;
				}
				min = Math.min(min, query(1, 1, N, segIdx[chainHeader[n2]], segIdx[n2]));
				n2 = chainParent[n2];
			}
			if(segIdx[n1] > segIdx[n2])
			{
				int tmp = n1;
				n1 = n2;
				n2 = tmp;
			}
			// 간선 정보는 자식노드에 저장되어있으므로 LCA에서 + 1한 범위부터 query 시작
			return Math.min(min, query(1, 1, N, segIdx[n1] + 1, segIdx[n2]));
		}
		int query(int treeNode, int s, int e, int left, int right)
		{
			
			propagate(treeNode, s, e);
			
			if(e < left || right < s)
				return INF;
			
			if(left <= s && e <= right)
				return minTree[treeNode];
			
			int mid = (s + e) >> 1;
			return Math.min(
					query(treeNode << 1, s, mid, left, right),
					query(treeNode << 1 | 1, mid + 1, e, left, right)
					);
		}
		void updateRange(int n1, int n2, int weight) {
			while(chainHeader[n1] != chainHeader[n2])
			{
				if(chainLevel[n1] > chainLevel[n2])
				{
					int tmp = n1;
					n1 = n2;
					n2 = tmp;
				}
				update(1, 1, N, segIdx[chainHeader[n2]], segIdx[n2], weight);
				n2 = chainParent[n2];
			}
			if(segIdx[n1] > segIdx[n2])
			{
				int tmp = n1;
				n1 = n2;
				n2 = tmp;
			}
			// 간선 정보는 자식노드에 저장되어있으므로 LCA에서 + 1한 범위부터 update 시작
			update(1, 1, N, segIdx[n1] + 1, segIdx[n2], weight);
		}
		void update(int treeNode, int s, int e, int left, int right, int weight) {
			propagate(treeNode, s, e);
			
			if(e < left || right < s)
				return;
			
			if(left <= s && e <= right)
			{
				lazy[treeNode] = weight;
				propagate(treeNode, s, e);
				return;
			}
			
			int mid = (s + e) >> 1;
			
			update(treeNode << 1, s, mid, left, right, weight);
			update(treeNode << 1 | 1, mid + 1, e, left, right, weight);
			
			minTree[treeNode] = Math.min(minTree[treeNode << 1], minTree[treeNode << 1 | 1]);
		}
		void propagate(int treeNode, int s, int e) {
			if(lazy[treeNode] == INF)
				return;
			
			minTree[treeNode] = Math.min(minTree[treeNode], lazy[treeNode]);
			if(s != e)
			{
				lazy[treeNode<<1] = Math.min(lazy[treeNode<<1], lazy[treeNode]);
				lazy[treeNode<<1|1] = Math.min(lazy[treeNode<<1|1], lazy[treeNode]);
			}
			lazy[treeNode] = INF;
		}
		void setHLD(int nowNode, int level) {
			
			segIdx[nowNode] = ++idx;
			chainLevel[nowNode] = level;
			
			for(int i=0; i<adList[nowNode].size(); i++)
			{
				Node next = adList[nowNode].get(i);
				
				if(chainHeader[next.node] != 0)
					continue;
				
				if(i == 0)// 무거운 자식 노드인 경우 체인 유지
				{
					chainHeader[next.node] = chainHeader[nowNode];
					chainParent[next.node] = chainParent[nowNode];
					setHLD(next.node, level);
					continue;
				}
				chainHeader[next.node] = next.node;
				chainParent[next.node] = nowNode;
				setHLD(next.node, level + 1);
			}
		}
		void setChild(int nowNode, int parentNode, int size[])
		{
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
				Collections.swap(adList[nowNode], heavyIdx, 0);
		}
	}
	static class DSU{
		int N;
		int parent[];
		DSU(int N)
		{
			this.N = N;
			this.parent = new int[N + 1];
			for(int i=1; i<=N; i++)
				parent[i] = i;
		}
		int find(int node)
		{
			if(parent[node] == node)
				return node;
			
			return parent[node] = find(parent[node]);
		}
		boolean union(int n1, int n2)
		{
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
	}
	static class Node{
		int node, weight;
		Node(int n, int w){
			this.node = n;
			this.weight = w;
		}
	}
	static class Edge implements Comparable<Edge>{
		int n1, n2, weight, idx;
		Edge(int n1, int n2, int weight, int idx){
			this.n1 = n1;
			this.n2 = n2;
			this.weight = weight;
			this.idx = idx;
		}
		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
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