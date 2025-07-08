//https://www.acmicpc.net/problem/15508
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, K;
	static int cnt[];
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cnt = new int[N + 1];
		parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			parent[i] = i;
			cnt[i] = 1;
		}
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int p1 = find(Integer.parseInt(st.nextToken())); 
			int p2 = find(Integer.parseInt(st.nextToken()));
			
			if(p1 == p2)
				continue;
			
			if(parent[p1] < parent[p2]) {
				parent[p2] = p1;
				cnt[p1] += cnt[p2];
			}else {
				parent[p1] = p2;
				cnt[p2] += cnt[p1];
			}
		}
		
		boolean dp[] = new boolean[N + 1];
		
		dp[0] = true;
		
		for(int i=1; i<=N; i++)
		{
			if(parent[i] != i)
				continue;
			 
			for(int j = N; j>=0; j--)
				if(dp[j] && j + cnt[i] <= N)
					dp[j + cnt[i]] = true;
		}
		
		System.out.print(dp[K] || dp[N - K] ? "SAFE" : "DOOMED");
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}