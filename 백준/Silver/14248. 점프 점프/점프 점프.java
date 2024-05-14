// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int     cnt, N, map[];
	static boolean visit[];
	public static void DFS(int now) {
		if(now<1 || now>N || visit[now]) 
			return;
		visit[now] = true;
		cnt++;
        
		DFS(now + map[now]);
        
		DFS(now - map[now]);
        
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		map 	= new int[N+1];
		visit 	= new boolean[N+1];
		st 		= new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			map[i] = Integer.parseInt(st.nextToken());

		DFS(Integer.parseInt(br.readLine()));
		
		System.out.print(cnt);
	}
}