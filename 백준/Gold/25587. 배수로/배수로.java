//https://www.acmicpc.net/problem/25587
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int result;
	static int N, M;
	static int parent[];
	static int c[];
	static int p[];
	static int cnt[];
	static boolean isOver[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 도시 수 3 <= 100,000
		M = Integer.parseInt(st.nextToken());// 쿼리 수 1 <= 100,000
		p = new int[N + 1];// 강수량
		c = new int[N + 1];// 배수로 용량
		cnt = new int[N + 1];
		parent = new int[N + 1];
		isOver = new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			c[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			p[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++)
		{
			parent[i] = i;
			cnt[i] = 1;
			if(c[i] < p[i])
			{// 배수로 용량보다 강수량이 큰 경우 result 추가
				result++;
				isOver[i] = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if(cmd == 2)
			{
				sb.append(result).append('\n');
				continue;
			}
			
			int p1 = find(Integer.parseInt(st.nextToken()));
			int p2 = find(Integer.parseInt(st.nextToken()));

			if(p1 == p2)
				continue;
			
			if(parent[p1] > parent[p2]) // 항상 p1이 더작게 세팅
			{
				int tmp = p1;
				p1 = p2;
				p2 = tmp;
			}
			
			if(isOver[p1]) result -= cnt[p1];
			if(isOver[p2]) result -= cnt[p2];
			
			parent[p2] = p1;

			p[p1] += p[p2];
			c[p1] += c[p2];
			cnt[p1] += cnt[p2];

			isOver[p1] = c[p1] < p[p1];
			
			if(isOver[p1])
				result += cnt[p1];
		}
		System.out.print(sb);
	}
	static int find(int node)
	{
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}