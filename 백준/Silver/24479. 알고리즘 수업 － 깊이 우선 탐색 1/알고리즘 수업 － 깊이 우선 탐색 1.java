// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{

	static int N, M, R, a, b, cnt, order[];
	static boolean visit[];
	static ArrayList<Integer> list[];
	
	public static void DFS(int node) {
		if(!visit[node]) 
		{
			visit[node] = true;
			order[node] = ++cnt;
			for(int nextNode : list[node]) 
				DFS(nextNode);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		N 					= Integer.parseInt(st.nextToken());
		M 					= Integer.parseInt(st.nextToken());
		R 					= Integer.parseInt(st.nextToken());
		order 				= new int[N+1];
		list 				= new ArrayList[N+1];
		visit				= new boolean[N+1];
		for(int i=1; i<=N; i++) 
			list[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		// 노드 오름차순 정렬
		for(int i=1; i<=N; i++) 
			Collections.sort(list[i]);
		
		DFS(R);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(order[i]).append('\n');
		System.out.println(sb);
	}
}