//https://www.acmicpc.net/problem/10169
//1초 64MB
//5 9// 마을의 수(2<=100,000), 도로의 수 (2<=300,000)
//1 2 1// 도로의 수만큼 주어지며 A B C 형태로 A,B는 노드, C는 간선비용(1<=10억)
//3 1 4
//4 3 6
//2 4 7
//2 5 2
//5 3 5
//1 5 3
//5 4 7
//2 4 8
//// M개줄에 각 간선이 없을 때 비상연락망의 최소비용 출력, 없다면 -1 출력
//// 이하 답
//15
//14
//14
//13
//14
//13
//13
//13
//13

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main{
	
	static final int INF = 1_000_000_001;
	static int N, M;
	static boolean mstEdge[];
	static DSU dsu;
	static HLD hld;
	static List<Edge> edgeList;
	static List<Node> adList[];
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		Reader in = new Reader();
		
		N = in.nextInt();// 마을의 수(2<=100,000)
		M = in.nextInt();// 도로의 수 (2<=300,000)
		mstEdge = new boolean[M];
		edgeList = new ArrayList<>();
		dsu = new DSU(N);
		adList = new ArrayList[N + 1];
		
		for(int i=1; i<=N; i++)
			adList[i] = new ArrayList<>();
		// 간선 정보 입력 받기
		for(int i=0; i<M; i++)
		{
			int n1 = in.nextInt();
			int n2 = in.nextInt();
			int dist = in.nextInt();
			edgeList.add(new Edge(n1, n2, dist, i));
		}
		// MST를 구하기 위한 정렬
		Collections.sort(edgeList);
		
		int edgeCnt = 1;
		long edgeSum = 0;
		// MST 간선을 연결하며 , 가중치의 총합 및 간선연결 개수를 체크 
		for(int i=0; i<edgeList.size() && edgeCnt != N; i++)
		{
			Edge now = edgeList.get(i);
			
			if(dsu.union(now.n1, now.n2))
			{
				mstEdge[i] = true;
				adList[now.n1].add(new Node(now.n2, now.dist));
				adList[now.n2].add(new Node(now.n1, now.dist));
				edgeSum += now.dist;
				edgeCnt += 1;
			}
		}
		
		if(edgeCnt != N)//mst를 못만든 경우 -1출력 후 종료
		{
			for(int i=0; i<M; i++)
				sb.append(-1).append('\n');
			
			System.out.print(sb);
			return;
		}
		// HLD알고리즘을 위한 클래스 초기화 
		hld = new HLD(N, INF, adList);
		
		// 연결되지 않은 간선들에 대해 마킹
		for(int i=0; i<edgeList.size(); i++)
		{
			if(mstEdge[i])// mst간선이면 연산 스킵
				continue;
			// 현재 간선 가져오기
			Edge now = edgeList.get(i);
			// 현재 간선 가중치를 두 노드 사이의 mst경로에 업데이트 처리함
			// 이렇게 해야만 결과를 구할 때 두 노드 사이에 최소 가중치를 빠르게 알 수 있음
			hld.updateRange(now.n1, now.n2, now.dist);
		}
		
		// 최종 결과 출력
		long res[] = new long[M];
		
		for(int i=0; i<edgeList.size(); i++)
		{
			// 현재 간선 정보 가져오기
			Edge now = edgeList.get(i);
			// mst간선이 아니라면, 상관없기 때문에 값 그대로 출력
			if(!mstEdge[i])
			{
				res[now.idx] = edgeSum;
				continue;
			}
			// mst간선이라면, 해당 간선 사이에 대체 가능한 최소 간선 가져오기
			int min = hld.find(now.n1, now.n2);
			// 최소간선이 INF면 경로가 없으므로 -1 출력, 있다면 총 값에서 현재 삭제하는  간선 제거 및 추가된 간선 플러스
			res[now.idx] = min == INF ? -1 : edgeSum - now.dist + min;
		}
		// 결과 출력
		for(long r : res)
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
		HLD(int N, int inf, List<Node> adList[])
		{
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
			
			for(int i=0; i<minTree.length; i++)
				lazy[i] = minTree[i] = INF;
			// HLD를 위한 자식노드 크기에따른 인접 리스트 위치 변경
			setChild(1, 0, new int[N + 1]);
			// HLD를 위한 체인 정보 및 값 세팅
			setHLD(1, 0);
		}
		void updateRange(int n1, int n2, int dist) {
			while(chainHeader[n1] != chainHeader[n2])
			{
				if(chainLevel[n1] > chainLevel[n2])
				{
					int tmp = n1;
					n1 = n2;
					n2 = tmp;
				}
				// 체인 헤더와 현재 노드까지 업데이트
				update(1, 1, N, segIdx[chainHeader[n2]], segIdx[n2], dist);
				n2 = chainParent[n2];
			}
			
			if(segIdx[n1] > segIdx[n2])
			{
				int tmp = n1;
				n1 = n2;
				n2 = tmp;
			}
			// 간선 정보는 자식노드에 저장되어있으므로 LCA에서 + 1한 범위부터 update 시작
			update(1, 1, N, segIdx[n1] + 1, segIdx[n2], dist);
		}
		int find(int n1, int n2) {
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
			// 간선 정보는 자식노드에 저장되어있으므로 LCA에서 + 1한 범위부터 쿼리 시작
			return Math.min(min, query(1, 1, N, segIdx[n1] + 1, segIdx[n2]));
		}
		int query(int treeNode, int s, int e, int left, int right) {
			propagate(treeNode, s, e);
			
			if(e < left || right < s)
				return INF;
			
			if(left <= s && e <= right)
				return minTree[treeNode];
			
			int mid = (s + e) >> 1;
			
			int l = query(treeNode << 1, s, mid, left, right);
			int r = query(treeNode << 1 | 1, mid + 1, e, left, right);
			
			return Math.min(l, r);
		}
		void update(int treeNode, int s, int e, int left, int right, int value)
		{
			propagate(treeNode, s, e);
			
			if(e < left || right < s)
				return;
			
			if(left <= s && e <= right)
			{
				lazy[treeNode] = value;
				propagate(treeNode, s, e);
				return;
			}
			
			int mid = (s + e) >> 1;
			
			update(treeNode << 1, s, mid, left, right, value);
			update(treeNode << 1 | 1, mid + 1, e, left, right, value);
			
			minTree[treeNode] = Math.min(minTree[treeNode << 1], minTree[treeNode << 1 | 1]);
		}
		void propagate(int treeNode, int s, int e)
		{
			if(lazy[treeNode] == INF)
				return;
			
			minTree[treeNode] = Math.min(minTree[treeNode], lazy[treeNode]);
			
			if(s != e)
			{
				lazy[treeNode << 1] = Math.min(lazy[treeNode << 1], lazy[treeNode]);
				lazy[treeNode << 1 | 1] = Math.min(lazy[treeNode << 1 | 1], lazy[treeNode]);
			}
			
			lazy[treeNode] = INF;
		}
		void setHLD(int nowNode, int level)
		{
			segIdx[nowNode] = ++idx;
			chainLevel[nowNode] = level;
			
			for(int i=0; i<adList[nowNode].size(); i++)
			{
				Node next = adList[nowNode].get(i);
				// 이미 방문한 노드면 스킵
				if(chainHeader[next.node] != 0)
					continue;
				if(i == 0)// 무거운 자식노드면 체인 유지
				{
					chainHeader[next.node] = chainHeader[nowNode];
					chainParent[next.node] = chainParent[nowNode];
					setHLD(next.node, level);// 무거운 노드면 레벨 유지
				}
				else
				{
					// 가벼운 자식노드인 경우
					chainHeader[next.node] = next.node;// 헤더는 자기 자신
					chainParent[next.node] = nowNode;// 이전 체인으로 점프할 수 있도록 이전 체인 노드 저장
					setHLD(next.node, level + 1);// 가벼운 자식노드인 경우 레벨 +1처리
				}
			}
		}
		void setChild(int nowNode, int parentNode, int size[])
		{
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
		DSU(int N)
		{
			this.N = N;
			this.parent = new int[N + 1];
			for(int i=1; i<=N; i++)
				parent[i] = i;
		}
		int find(int node)
		{
			if(parent[node] == node) return node;
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
		int node;
		int dist;
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