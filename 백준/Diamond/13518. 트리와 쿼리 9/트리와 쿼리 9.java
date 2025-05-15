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
import java.util.StringTokenizer;

class Main{
	
	static int N, Q;
	static int idx;		// ETT진행시 순차 증가할 인덱스
	static int sqrt;	// Mo's알고리즘을 위한 변수
	static int in[];	// ETT진행시 진입 인덱스	 (idx : 노드번호) (value : all배열의 인덱스)
	static int out[];	// ETT진행시 나가는 인덱스(idx : 노드번호) (value : all배열의 인덱스)
	static int all[];	// 트리를 1차원으로 표현, ETT in과 out을 모두 담음( idx : ETT변환 인덱스) (value : 노드번호)
	static int arr[];	// 각 노드마다의 가중치를 담음
	static int depth[];	// LCA구할 때 사용
	static int dp[][];	// LCA를 빠르게 구하기 위한 dp배열
	static int cnt[];	// (idx : 가중치) (value : 가중치의 당장횟수)
	static int nodeCnt[]; // 해당 노드의 등장 횟수
	static int ans;		// 최종적으로 구한 서로 다른 가중치의 개수
	static int result[];
	static ArrayList<Integer> adList[];// 인접리스트
	static ArrayList<Query> query;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sqrt = (int)Math.sqrt(N<<1);
		in = new int[N + 1];
		out = new int[N + 1];
		all = new int[(N << 1) + 1];
		arr = new int[N + 1];
		adList = new ArrayList[N + 1];
		depth = new int[N + 1];
		dp = new int[N + 1][18];
		query = new ArrayList<>();
		cnt = new int[1_000_001];
		nodeCnt = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());// 각 노드당 가중치를 입력받음
			adList[i] = new ArrayList<>();// 인접리스트도 같이 초기화
		}
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		dfs(1, 1);			// 트리 1차원화 + LCA를 위한 기본 세팅
		buildLcaTable();	// LCA를 위한 dp테이블 세팅
		
		Q = Integer.parseInt(br.readLine());// 쿼리 수
		result = new int[Q + 1];
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int lca = getLCA(a,b);
			
			if(in[a] > in[b])
			{
				int tmp = a;
				a = b;
				b = tmp;
			}
			
			if(lca == a)
			{
				query.add(new Query(in[a], in[b], 0, in[a] / sqrt, i));
				continue;
			}
			
			query.add(new Query(out[a], in[b], lca, out[a] / sqrt, i));
		}
		
		Collections.sort(query);
		
		int L = 0;
		int R = 0;
		for(Query q : query)
		{
			while(R < q.right) toggle(all[++R],1);
			while(q.left < L) toggle(all[--L],1);
			while(q.right < R) toggle(all[R--],-1);
			while(L < q.left) toggle(all[L++],-1);
			
			if(q.lca != 0) toggle(q.lca,1);
			
			result[q.idx] = ans; 
			
			if(q.lca != 0) toggle(q.lca,-1);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(result[i]).append('\n');
		
		System.out.print(sb);
	}
    static void toggle(int node, int add) {
        
        nodeCnt[node] += add;
        if (nodeCnt[node] == 1)
        {
            if (++cnt[arr[node]] == 1)
            	ans++;
        }
        if (nodeCnt[node] == (add > 0 ? 2 : 0))
        {
            if (--cnt[arr[node]] == 0)
            	ans--;
        }
    }
    static int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u] - depth[v];
        for (int i = 0; diff > 0; i++) {
            if ((diff & 1) == 1) u = dp[u][i];
            diff >>= 1;
        }
        if (u != v) {
            for (int i = 17; i >= 0; i--) {
                if (dp[u][i] != dp[v][i]) {
                    u = dp[u][i];
                    v = dp[v][i];
                }
            }
            u = dp[u][0];
        }
        return u;
    }
	static void buildLcaTable()
	{
		for(int j=1; j<18; j++)
			for(int i=1; i<=N; i++)
				dp[i][j] = dp[dp[i][j-1]][j-1];
	}
	static void dfs(int now, int dep) {
		depth[now] = dep;	// LCA를 위한 깊이 저장
		in[now] = ++idx;	// 트리의 진입 정보를 담음
		all[idx] = now;	// 트리 순회를 1차원 배열로 저장
		for(int next : adList[now])
		{
			if(depth[next] != 0)// 방문한 적이 있다면 스킵
				continue;
			
			dp[next][0] = now;	// LCA를 위해 부모노드 저장
			
			dfs(next, dep + 1);
		}
		out[now] = ++idx;
		all[idx] = now;		
	}
	static class Query implements Comparable<Query>{
		int left, right, lca, fac, idx;
		Query(int left, int right, int lca, int fac, int idx){
			this.left = left;
			this.right = right;
			this.lca = lca;
			this.fac = fac;
			this.idx = idx;
		}
		@Override
		public int compareTo(Query o) {
			if(fac != o.fac)
				return fac - o.fac;
			
			if((fac&1) == 0)
				return right - o.right;
			
			return o.right - right;
		}
	}
}