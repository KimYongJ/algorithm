//https://www.acmicpc.net/problem/18267
//5 5//농장 수(1<=1,000,000), 친구수(1<=1,000,000)
//HHGHG// 각 농장의 소 종류가 주어짐
//1 2// 농장수 - 1개 만큼의 간선 X, Y가 주어지며 둘이 연결됨을 의미
//2 3
//2 4
//1 5
//1 4 H// 친구 수만큼 Ai, Bi, Ci가 주어짐, Ai,Bi는 두 농장, Ci는 G, H중 하나로 선호 소 종류
//1 4 G
//1 3 G
//1 3 H
//5 5 H
//길이가 M인 문자열을 출력하며 i번째 친구가 행복하면 1, 아니면 0 
//답 : 10110

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static char arr[];
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//농장 수(1<=1,000,000)
		M = Integer.parseInt(st.nextToken());//친구 수(1<=1,000,000)
		arr = new char[N + 1];
		parent = new int[N + 1];
		
		String str = br.readLine();
		for(int i=1; i<=N; i++)
		{
			parent[i] = i;
			arr[i] = str.charAt(i - 1);
		}
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(arr[a] != arr[b])
				continue;
			
			int p1 = find(a);
			int p2 = find(b);
			
			if(parent[p1] < parent[p2])
				parent[p2] = p1;
			else
				parent[p1] = p2;
		}
		
		StringBuilder sb = new StringBuilder();
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p1 = find(a);
			int p2 = find(b);
			
			char c = st.nextToken().charAt(0);
			int res = 0;
			
			if(p1 == p2 && arr[a] == c)
				res = 1;
			else if(p1 != p2)
				res = 1;

			sb.append(res);
		}
		System.out.print(sb);
	}
	static int find(int node)
	{
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}