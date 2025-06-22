//https://www.acmicpc.net/problem/23324
//5 4 2// 정점수(2<=100,000), 간선수(1<=200,000), 제외 정수 (1<=M)
//1 2
//2 3
//3 4
//4 5
// 답 : 6

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
		N = Integer.parseInt(st.nextToken());// 정점수(2<=100,000)
		M = Integer.parseInt(st.nextToken());// 간선수(1<=200,000)
		K = Integer.parseInt(st.nextToken());// 제외 정수 (1<=M)
		cnt = new int[N + 1];
		parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			cnt[i] = 1;
			parent[i] = i;
		}
		
		for(int i=1; i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			if(K == i) continue;
			
			int p1 = find(Integer.parseInt(st.nextToken()));
			int p2 = find(Integer.parseInt(st.nextToken()));
			
			if(p1 == p2)
				continue;
			
			if(parent[p1] < parent[p2]) {
				parent[p2] = p1;
				cnt[p1] += cnt[p2];
			}
			else {
				parent[p1] = p2;
				cnt[p2] += cnt[p1];
			}
		}
		
		long res = 1;
		int groupCnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			if(parent[i] == i)
			{
				res *= cnt[i];
				groupCnt++;
			}
		}
		
		System.out.print(groupCnt == 1 ? 0 : res);
	}
	static int find(int node)
	{
		if(parent[node] == node)
			return node;
		
		return parent[node] = find(parent[node]);
	}
}