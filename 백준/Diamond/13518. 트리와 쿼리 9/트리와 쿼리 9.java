//https://www.acmicpc.net/problem/13518
//2초 512MB
//8// 노드 수 2<=100,000
//105 2 9 3 8 5 7 7 // 가중치 1,000,000
//1 2	// 노드수 -1 개줄에 연결 간선 주어짐
//1 3
//1 4
//3 5
//3 6
//3 7
//4 8
//2	// 쿼리 수 1<=100,000
//2 5	// 두 노드가 주어짐
//7 8
////두 노드 사이 서로다른 정점의 가중치의 개수 답 : 
//4
//4

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
class Main{
	
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, Q;
	static int sqrt;		// mo's알고리즘으로 정렬시 사용할 제곱근, N * 2의 제곱근이 된다.
	static int time;		// 오일러투어 탐색시 진입 진출시간을 표시할 변수
	static int weight[];		// 각노드의 가중치를 담을 배열
	static int weightCount[];	// 가중치가 등장한 횟수 (idx : 가중치 값) (value : 가중치 등장 횟수)
	static int nodeCount[];		// 노드가 몇번 등장했는지 카운팅 하기 위한 배열
	static int in[];		// 진입 시간을 담을 배열 (index : 노드번호) (value : 진입시간)
	static int out[];		// 진출 시간을 담을 배열 (index : 노드번호) (value : 진입시간)
	static int ett[];		// 진입+진출 시간을 1차원으로 평탄화 한것(index : 진입시간) (value : 노드번호)
	static int chainLevel[];	// LCA를 구할 때만 사용, HLD분할시 체인의 레벨 저장
	static int chainHeader[];	// LCA를 구할 때만 사용, HLD분할시 체인의 가장 첫번째 값
	static int chainParent[];	// LCA를 구할 때만 사용, HLD분할시 이전 체인으로 바로 점프할 수 있도록 이전 노드저장
	static int result[];		// 쿼리 질의 최종 결과를 담을 배열
	static List<Integer>[] adList;	// 트리 정보를 담을 인접 리스트
	static List<Query> query;	// 주어지는 쿼리 정보를 담을 리스트
	
	
	public static void main(String[] args)throws Exception{
		init();// 해당 함수에서 배열들 초기화 및 간선의 가중치를 입력 받음 + 간선 정보도 입력 받아 인접리스트 생성
		setHLD(1, 0, new int[N + 1]);// HLD로 트리를 분할하기 위해 전처리 과정, 단순 adList의 위치만 바꾸는 용도
		dfs(1, 1);// 해당 함수에서 트리를 평탄화 시키며 동시에 HLD로 트리를 분할 한다.
		inputQuery();// 해당 함수에서 쿼리를 입력 받고, 입력된 노드들을 1차원 배열에 대응토록 변환 및 LCA구하고 mo's 정렬까지함
		solve();// 투포인터로 정답을 구한 후 최종 출력 까지 진행
	}
	static void init()throws Exception {
		N = Integer.parseInt(br.readLine());
		sqrt = (int)Math.sqrt((N * 2));
		weight = new int[N + 1];
		weightCount = new int[1_000_001];
		nodeCount = new int[N + 1];
		in = new int[N + 1];
		out = new int[N + 1];
		ett = new int[(N * 2) + 1];
		chainLevel = new int[N + 1];
		chainHeader = new int[N + 1];
		chainParent = new int[N + 1];
		adList = new ArrayList[N + 1];
		chainHeader[1] = 1;	// 체인 헤더 기본 값 세팅
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			weight[i] = Integer.parseInt(st.nextToken());
			adList[i] = new ArrayList<>();
		}
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adList[u].add(v);
			adList[v].add(u);
		}
	}
	static void setHLD(int nowNode, int parentNode, int[] size) {
		int heavyIdx = 0;
		int heavySize = 0;
		size[nowNode] = 1;
		for(int i=0; i<adList[nowNode].size(); i++)
		{
			int nextNode = adList[nowNode].get(i);
			if(nextNode == parentNode) // 이미 방문한 노드는 스킵
				continue;
			
			setHLD(nextNode, nowNode, size);
			
			size[nowNode] += size[nextNode]; // nowNode의 자식 노드 사이즈를 저장
			
			// 가장 무거운 자식 노드의 인덱스를 저장
			if(heavySize < size[nextNode])
			{
				heavySize = size[nextNode];
				heavyIdx = i;
			}
		}
		// 가장 무거운 노드를 리스트의 가장 앞으로 옮겨 추후 연산을 간편하게 한다.
		if(adList[nowNode].size() > 0)
			Collections.swap(adList[nowNode], 0, heavyIdx);
	}
	static void dfs(int nowNode, int level) {
		in[nowNode] = ++time;// 진입 시간 마킹
		ett[time] = nowNode;// 1차원화
		chainLevel[nowNode] = level;	// HLD를 위한 레벨 입력
		
		for(int i=0; i<adList[nowNode].size(); i++)
		{
			int nextNode = adList[nowNode].get(i);
			
			if(chainHeader[nextNode] != 0)// chainHeader가 있다면 이미 방문한 것
				continue;
			
			if(i == 0)// 무거운 자식일 때(setHLD 함수에서 무거운 자식노드를 앞으로 옮겼으므로)
			{
				chainHeader[nextNode] = chainHeader[nowNode];// 무거우면 헤더값 유지
				chainParent[nextNode] = chainParent[nowNode];// 무거우면 점프할 노드 유지
				dfs(nextNode, level);// 무거운 자식 탐색시 level 유지
				continue;
			}
			
			// 가벼운 자식 노드들은 새로운 체인을 시작하므로, header 값과 parent, level모두 변경
			chainHeader[nextNode] = nextNode;
			chainParent[nextNode] = nowNode;
			dfs(nextNode, level + 1);
		}
		
		out[nowNode] = ++time;// 진출 시간 마킹
		ett[time] = nowNode;// 1차원화
	}
	static void inputQuery()throws Exception {
		Q = Integer.parseInt(br.readLine());
		result = new int[Q + 1];
		query = new ArrayList<>();
		
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int lca = getLCA(s,e);
			// s가 더 자식이라면 s를 부모로 만듬
			if(in[s] > in[e])
			{
				int tmp = s;
				s = e;
				e = tmp;
			}
			// 두 노드 중 하나가 lca라면
			if(lca == s)
			{
				query.add(new Query(in[s], in[e], i, 0, in[s] / sqrt));
				continue;
			}
			// 두 노드 모두 lca가 아니라면
			query.add(new Query(out[s], in[e], i, lca, in[s] / sqrt));
		}
		Collections.sort(query);
	}
	static void solve() {
		int s = 1;
		int e = 0;
		int cnt = 0;
		
		for(Query q : query)
		{
			while(e < q.e) cnt += plus(ett[++e]);
			while(q.s < s) cnt += plus(ett[--s]);
			while(q.e < e) cnt += minus(ett[e--]);
			while(s < q.s) cnt += minus(ett[s++]);
			// lca값은 노드값 그대로 저장했으므로 그냥 넘겨줌
			if(q.lca != 0) cnt += plus(q.lca);
			
			result[q.idx] = cnt;
			// lca값은 노드값 그대로 저장했으므로 그냥 넘겨줌
			if(q.lca != 0) cnt += minus(q.lca);
		}
		
		// 최종적인 값 출력
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(result[i]).append('\n');
		
		System.out.print(sb);
	}
	static int minus(int node) {
		int w = weight[node];
		int cnt = 0;
		--nodeCount[node];
		
		if(nodeCount[node] == 1)
		{
			if(++weightCount[w] == 1)
				++cnt;
		}
		else if(nodeCount[node] == 0)
		{
			if(--weightCount[w] == 0)
				--cnt;
		}
		return cnt;
	}
	static int plus(int node) {
		int w = weight[node];
		
		int cnt = 0;
		
		++nodeCount[node];
		
		if(nodeCount[node] == 1)
		{
			if(++weightCount[w] == 1)
				++cnt;
		}
		else if(nodeCount[node] == 2) {
			if(--weightCount[w] == 0)
				--cnt;
		}
		return cnt;
	}
	static int getLCA(int node1, int node2) {
		if(in[node1] > in[node2]) // node1이 더 자식이라면 부모로 올림
		{
			int tmp = node1;
			node1 = node2;
			node2 = tmp;
		}
		// 같은 체인이 될 때 까지 높은 레벨에 있는 체인을 낮은 레벨로 올림
		while(chainHeader[node1] != chainHeader[node2])
		{
			// node1의 레벨이 더 크다면 더 작게 올려야하기 때문에 node1을 위로 올림
			if(chainLevel[node1] > chainLevel[node2])
			{
				node1 = chainParent[node1];
				continue;
			}
			// node2가 레벨이 더크면 node2를 위로 올려 level을 작게 만듬
			node2 = chainParent[node2];
		}
		// 진입 시간이 더 작은(더 상위노드) 노드 번호를 반환하면 그게 LCA가 됨
		return in[node1] > in[node2] ? node2 : node1;
	}
	static class Query implements Comparable<Query>{
		int s, e, idx, lca, fac;
		Query(int s, int e, int idx, int lca, int fac){
			this.s=s;
			this.e=e;
			this.idx=idx;
			this.lca=lca;
			this.fac=fac;
		}
		@Override
		public int compareTo(Query o) {
			if(fac != o.fac)
				return fac - o.fac;
			// 구간이 짝수면 e기준 오름차순 정렬
			if((fac&1) == 0)
				return e - o.e;
			// 구간이 홀수면 e기준 내림차순 정렬
			return o.e - e;
		}
	}
}
