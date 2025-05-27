//https://www.acmicpc.net/problem/20132
//1초 1024MB
//3 3 // 정점 개수(2<=100,000), 간선 개수(1<=300,000)
//1 2 3// 노드번호 두개(ui != vi), 간선의 비용 wi(1<=1,000,000,000)
//2 3 3
//3 1 2
//가능한 비용의 합 중 홀수인 최솟값, 짝수인 최솟값 출력(없으면-1출력) : 5 6

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static long ans1;
	static long ans2 = Long.MAX_VALUE;
	static int N, M;
	static boolean visitEdge[];
	static List<Edge> edgeList;
	static List<Node> adNode[];
	static UnionFind uf;
	static HLD hld;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 정점 개수(2<=100,000)
		M = Integer.parseInt(st.nextToken());// 간선 개수(1<=300,000)
		edgeList = new ArrayList<>();
		visitEdge = new boolean[M + 1];
		uf = new UnionFind(N);
		adNode = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adNode[i] = new ArrayList<>();
		
		for(int i=1; i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			long cost = Long.parseLong(st.nextToken());// 간선의 비용 wi(1<=1,000,000,000)
			edgeList.add(new Edge(n1, n2, i, cost));
		}
		// 크루스칼 알고리즘을 위해 간선을 가중치 기준 오름차순 정렬
		Collections.sort(edgeList);
		// MST를 구하고, MST의 트리 정보를 adNode 에 인접노드로 저장시킴
		int edgeCnt = 1;
		for(int i=0; i<edgeList.size() && edgeCnt < N; i++)
		{
			Edge edge = edgeList.get(i);
			if(uf.union(edge.n1, edge.n2))
			{
				ans1 += edge.cost; // MST 총 간선의 합을 구함
				edgeCnt += 1;// 연결된 간선이 N - 1개가 되면 바로 종료하기 위해 구한 간선 개수르 카운팅
				// 해당 노드를 기준으로 트리를 생성, 양방향 맵핑 진행
				adNode[edge.n1].add(new Node(edge.n2, edge.cost));
				adNode[edge.n2].add(new Node(edge.n1, edge.cost));
				visitEdge[edge.idx] = true; 
			}
		}
		if(edgeCnt != N )
		{
			System.out.println("-1 -1");
			return;
		}
		// hld 클래스 초기 세팅
		hld = new HLD(N, adNode);
		
		for(int i=0; i<edgeList.size(); i++)
		{
			Edge edge = edgeList.get(i);
			
			if(visitEdge[edge.idx])
				continue;
			
			long max = 0;
			
			if(edge.cost % 2 == 0)// 간선이 짝수면 해당 노드 사이의 홀수 max를 가져온다.
				max = hld.oddMaxQuery(edge.n1, edge.n2);
			else// 간선 가중치가 솔수면 노드 사이 짝수 max를 가져온다.
				max = hld.evenMaxQuery(edge.n1, edge.n2);
			
			if(max == 0)
				continue;
			
			long nextAns = ans1 - max + edge.cost;
			
			if(nextAns < ans2)
				ans2 = nextAns;
		}
		
		if(ans2 == Long.MAX_VALUE)
			ans2 = -1;
		
		if(ans1 % 2 == 0)
			System.out.print(ans2 + " " + ans1);
		else
			System.out.print(ans1 + " " + ans2);
	}
	static class HLD{
		int N;
		int idx;// 세그먼트 트리 인덱스
		int segIdx[];// 노드의 값 -> 세그먼트트리 인덱스로 치환해줄 배열
		int chainLevel[];// 체인의 레벨 저장
		int chainParent[];// 해당 체인 이전의 체인으로 바로 점프하기 위해 이전 체인에 속한 노드값 저장
		int chainHeader[];// 해당 체인의 head값
		long evenTree[];// 짝수 트리
		long oddTree[];// 홀수 트리
		List<Node> adNode[];// 인접 리스트
		
		HLD(int N, List<Node> adNode[]){
			this.N = N;
			this.adNode = adNode;
			this.segIdx = new int[N + 1];
			this.chainLevel = new int[N + 1];
			this.chainParent = new int[N + 1];
			this.chainHeader = new int[N + 1];
			this.evenTree = new long[N * 4];
			this.oddTree = new long[N * 4];
			this.chainHeader[1] = 1;// 노드 1은 헤더가 자기자신
			// HLD를 위해 무거운 자식노드를 인접 리스트에서 가장 앞으로 옮긴다.
			setChildSize(1, 0, new int[N + 1]);
			// HLD를 위해 실제 체인 정보 세팅과 세그먼트 트리의 값을 세팅한다.
			setHLD(1, 1);
		}

		long oddMaxQuery(int node1, int node2) {
			return getValue(node1, node2, oddTree);
		}
		long evenMaxQuery(int node1, int node2) {
			return getValue(node1, node2, evenTree);
		}
		long getValue(int node1, int node2, long[] tree) {
			
			long max = 0;
			
			if(segIdx[node1] > segIdx[node2])
			{
				int tmp = node1;
				node1 = node2;
				node2 = tmp;
			}
			// 같은 체인이 될 때 까지 레벨일 높은걸 낮추면서 같은 체인으로 만듦, 이 때 max값 계속 갱신
			while(chainHeader[node1] != chainHeader[node2])
			{
				if(chainLevel[node1] > chainLevel[node2])
				{
					max = Math.max(max, query(1, 1, N, segIdx[chainHeader[node1]], segIdx[node1], tree));
					node1 = chainParent[node1];
					continue;
				}
				max = Math.max(max, query(1, 1, N, segIdx[chainHeader[node2]], segIdx[node2], tree));
				node2 = chainParent[node2];
			}
			// 여기까지 오면 같은 체인에 있는 것, 같은 체인 내에서 올려야 함
			if(segIdx[node1] > segIdx[node2])
			{
				int tmp = node1;
				node1 = node2;
				node2 = tmp;
			}
			// 간선 가중치는 자식노드에 저장하였으므로 마지막 탐색은 위노드 + 1로 탐색해야함,
			return Math.max(max, query(1, 1, N, segIdx[node1] + 1, segIdx[node2], tree));
		}
		long query(int treeNode, int s, int e, int left, int right, long[] tree) {
			if(e < left || right < s)
				return 0;
			
			if(left <= s && e <= right)
				return tree[treeNode];
			
			int mid = (s + e) >> 1;
			
			return Math.max(
						query(treeNode << 1, s, mid, left, right, tree),
						query(treeNode << 1 | 1, mid + 1, e, left, right, tree)
					);
		}
		void setChildSize(int nowNode, int parentNode, int size[])
		{
			int heavyIdx = 0;
			int heavySize = 0;
			size[nowNode] = 1;
			for(int i=0; i<adNode[nowNode].size(); i++)
			{
				Node next = adNode[nowNode].get(i);
				
				if(next.node == parentNode)
					continue;
				
				setChildSize(next.node, nowNode, size);
				// 현재 노드에 자기 자식 노드 사이즈를 모두 합침
				size[nowNode] += size[next.node];
				// 가장 무거운 노드의 인덱스 수정
				if(heavySize < size[next.node])
				{
					heavySize = size[next.node];
					heavyIdx = i;
				}
			}
			// 가장 무거운 노드를 제일 앞으로 옮김
			if(adNode[nowNode].size() > 0)
				Collections.swap(adNode[nowNode], 0, heavyIdx);
		}
		void setHLD(int nowNode, int level) {
			segIdx[nowNode] = ++idx;
			chainLevel[nowNode] = level;
			for(int i=0; i<adNode[nowNode].size(); i++)
			{
				Node now = adNode[nowNode].get(i);
				// 이미 탐색한 적있다면 스킵
				if(chainHeader[now.node] != 0)
					continue;
				
				if(i == 0)// heavy child라 체인 유지시 그대로 내려감
				{
					chainHeader[now.node] = chainHeader[nowNode];
					chainParent[now.node] = chainParent[nowNode];
					setHLD(now.node, level);
				}
				else// light child는 항상 새로운 체인 생성
				{
					chainHeader[now.node] = now.node;//헤드는 자기자신
					chainParent[now.node] = nowNode;// 이전 체인 점프할 노드는 nowNode가됨
					setHLD(now.node, level + 1);// 레벨 증가
				}
				update(1, 1, N, segIdx[now.node], now.cost);
			}
		}
		void update(int treeNode, int s, int e, int targetIdx, long cost) {
			if(e < targetIdx || targetIdx < s)
				return;
			if(s == e)
			{
				if(cost % 2 == 0)// 저장 값이 짝수이면 짝수 트리에 저장
					evenTree[treeNode] = cost;
				else// 저장 값이 홀수이면 홀수 트리에 저장
					oddTree[treeNode] = cost;
				
				return;
			}
			
			int mid = (s + e) >> 1;
			
			if(targetIdx <= mid)// 타겟이 중간보다 작거나 같으면 왼쪽으로 내려감
				update(treeNode << 1, s, mid, targetIdx, cost);
			else// 타겟이 중간보다 크면 오른쪽으로 내려감
				update(treeNode << 1 | 1, mid + 1, e, targetIdx, cost);
			// 짝수 홀수 세그먼트 트리 모두 업데이트
			oddTree[treeNode] = Math.max(oddTree[treeNode << 1], oddTree[treeNode << 1 | 1]);
			evenTree[treeNode] = Math.max(evenTree[treeNode << 1], evenTree[treeNode << 1 | 1]);
		}
	}
	static class UnionFind{
		int N;
		int parent[];
		UnionFind(int N){
			this.N = N;
			this.parent = new int[N + 1];
			for(int i=1; i<=N; i++)
				parent[i] = i;
		}
		public boolean union(int n1, int n2) {
			int parent1 = getParent(n1);
			int parent2 = getParent(n2);
			// 이미 같다면 연결 false
			if(parent1 == parent2)
				return false;
			
			if(parent1 < parent2)
				parent[parent2] = parent1;
			else
				parent[parent1] = parent2;
			
			return true;
		}
		public int getParent(int node) {
			if(parent[node] == node) return node;
			return getParent(parent[node]);
		}
	}
	static class Node {
		int node;
		long cost;
		Node(int node, long cost){
			this.node = node;
			this.cost = cost;
		}
	}
	static class Edge implements Comparable<Edge>{
		int n1, n2, idx;
		long cost;
		Edge(int n1, int n2, int idx, long cost){
			this.n1 = n1;
			this.n2 = n2;
			this.idx = idx;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(cost,o.cost);
		}
	}
}