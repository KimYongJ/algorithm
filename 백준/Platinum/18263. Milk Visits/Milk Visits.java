//https://www.acmicpc.net/problem/18263
//2초 512MB
//5 5// 노드 수(1<=1,000,000), 친구 수(1<=1,000,000)
//1 1 2 1 2// 소의 유형(1<=노드 수)
//1 2// N-1개 줄에 간선 정보가 주어짐
//2 3
//2 4
//1 5
//1 4 1// M개 줄에 양 노드A,B와 원하는 소의 유형 C(1<=노드수)가 주어짐
//1 4 2
//1 3 2
//1 3 1
//5 5 1
//방문 경로에 원하는 소의 유형이 있으면 1, 없으면 0 출력
//답 : 10110

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
	static int idx;// 오일러 투어시 증가할 증가값
	static int ans[];// 최종 결과를 담을 배열, 방문 중 해당 소 유형이 있다면 1, 없다면 0을 담음
	static int cow[];// 각 소의 유형을 담을 배열
	static int cnt[];// 각 소의 유형을 방문했는지 알기 위한 카운팅 배열 
	static int in[];// 트리 1차원화를 위한 노드 진입값, (idx = 노드번호) (value = ett배열의 인덱스)
	static int out[];// 트리 1차원화를 위해 노드 진출 값(idx = 노드번호) (value = ett배열의 인덱스)
	static int ett[];// Mo's알고리즘을 트리에 적용할 수 있도록 트리를 1차원화 시킬 배열(idx = ett배열의 인덱스) (value = 노드번호)
	static int nodeCnt[];// 트리를 1차원 배열화 하면, 노드가 들어갔다 나온경우 없는 것이 되는데 이 것을 알기 위해 추가함
	static int chainLevel[];// LCA를 구하기 위해 사용, 체인의 레벨 저장
	static int chainHeader[];// LCA를 구하기 위해 사용, 체인의 헤더 값 저장
	static int chainParent[];// LCA를 구하기 위해 사용, 이전 체인으로 점프하기 위한 노드 정보 저장
	static List<Integer> adList[];// 인접 노드를 담을 배열 리스트
	static List<Query> query;// Mo's를 위한 쿼리 리스트


	public static void main(String[] args)throws Exception{
		inputAndInit();// 기본 값 입력 및 인접리스트 생성
		setChild(1, 0, new int[N + 1]);// HLD 알고리즘을 위한 heavy노드 위치 교체만 진행
		ettDfs(1, 1);// 오일러 투어 + HLD 체인분할 진행
		inputQuery();// 쿼리를 입력 받아 1차원 배열에 대응 시키고 Mo's정렬
		solve();// 투포인터 형식으로 문제 해결 및 출력
	}
	static void inputAndInit()throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 노드 수(1<=1,000,000)
		Q = Integer.parseInt(st.nextToken());// 친구 수(1<=1,000,000)
		ans = new int[Q + 1];
		cow = new int[N + 1];
		cnt = new int[N + 1];
		in = new int[N + 1];
		out = new int[N + 1];
		ett = new int[N * 2 + 1];
		nodeCnt = new int[N + 1];
		chainLevel = new int[N + 1];
		chainHeader = new int[N + 1];
		chainParent = new int[N + 1];
		query = new ArrayList<>();
		adList = new ArrayList[N + 1];
		chainHeader[1] = 1;// 1의 헤더는 자기자신
		chainParent[1] = 1;// 1의 다음 점프도 자기자신
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			adList[i] = new ArrayList<>();
			cow[i] = Integer.parseInt(st.nextToken());// 소의 유형(1<=노드 수)
		}
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
	}
	static void setChild(int nowNode, int parentNode, int size[]) {
		int heavySize = 0;
		int heavyIdx = 0;
		size[nowNode] = 1;
		for(int i=0; i<adList[nowNode].size(); i++)
		{
			int nextNode = adList[nowNode].get(i);
			
			if(nextNode == parentNode)
				continue;
			
			setChild(nextNode, nowNode, size);
			
			size[nowNode] += size[nextNode];
			
			if(heavySize < size[nextNode])
			{
				heavySize = size[nextNode];
				heavyIdx = i;
			}
		}
		
		Collections.swap(adList[nowNode], 0, heavyIdx);
	}
	static void ettDfs(int nowNode, int level) {
		in[nowNode] = ++idx;
		ett[idx] = nowNode;
		chainLevel[nowNode] = level;
		
		for(int i=0; i<adList[nowNode].size(); i++)
		{
			int nextNode = adList[nowNode].get(i);
			
			if(chainHeader[nextNode] != 0)// 이미 방문한 노드면 스킵
				continue;
				
			if(i == 0)// heavy child이면 체인 유지
			{
				chainHeader[nextNode] = chainHeader[nowNode];
				chainParent[nextNode] = chainParent[nowNode];
				ettDfs(nextNode, level);
				continue;
			}
			// light child이면 새로운 체인 생성
			chainHeader[nextNode] = nextNode;
			chainParent[nextNode] = nowNode;
			ettDfs(nextNode, level + 1);// 새로운 체인은 레벨 증가
		}
		
		out[nowNode] = ++idx;
		ett[idx] = nowNode;
	}
	static void inputQuery()throws Exception{
		for(int i=1, sqrt = (int)Math.sqrt(idx); i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());// 원하는 소의 유형
			int lca = getLCA(s,e);
			if(in[s] > in[e])
			{
				int tmp = s;
				s = e;
				e = tmp;
			}

			if(lca == s)// 두 노드중 하나가 서로의 LCA일 경우는 LCA값을 저장하지 않음
				query.add(new Query(in[s], in[e], i, in[s] / sqrt, 0, c));
			else
				query.add(new Query(out[s], in[e], i, in[s] / sqrt, lca, c));
		}
		
		Collections.sort(query);
	}
	static int getLCA(int node1, int node2) {
		if(in[node1] > in[node2])
		{
			int tmp = node1;
			node1 = node2;
			node2 = tmp;
		}
		// 같은 체인이 될 때 까지 레벨이 높은 것을 낮게 하여 위로 올린다.
		while(chainHeader[node1] != chainHeader[node2])
		{
			if(chainLevel[node1] > chainLevel[node2])
				node1 = chainParent[node1];
			else
				node2 = chainParent[node2];
		}
		// 같은 체인이면 오일러 투어시 먼저 진입한 노드를 반환
		return in[node1] > in[node2] ? node2 : node1;
	}
	static void solve() {
		int s = 1;
		int e = 0;
		
		for(Query q : query)
		{
			while(e < q.e) plus(ett[++e]);
			while(q.s < s) plus(ett[--s]);
			while(q.e < e) minus(ett[e--]);
			while(s < q.s) minus(ett[s++]);
			
			if(q.lca != 0) plus(q.lca);
			
			if(cnt[q.c] > 0)
				ans[q.idx]= 1;
			
			if(q.lca != 0) minus(q.lca);

		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]);
		
		System.out.print(sb);
	}
	static void plus(int node) {
		++nodeCnt[node];
		
		if(nodeCnt[node] == 1)
			++cnt[cow[node]];
		else
			--cnt[cow[node]];
	}
	static void minus(int node) {
		--nodeCnt[node];
		
		if(nodeCnt[node] == 1)
			++cnt[cow[node]];
		else
			--cnt[cow[node]];
	}
	static class Query implements Comparable<Query>{
		int s, e, idx, fac, lca, c;
		Query(int s, int e,int idx, int fac, int lca, int c){
			this.s = s;
			this.e = e;
			this.idx = idx;
			this.fac = fac;
			this.lca = lca;
			this.c = c;
		}
		@Override
		public int compareTo(Query o) {
			if(fac != o.fac)
				return fac - o.fac;
			
			if((fac & 1) == 0)
				return e - o.e;
			
			return o.e - e;
		}
	}
}