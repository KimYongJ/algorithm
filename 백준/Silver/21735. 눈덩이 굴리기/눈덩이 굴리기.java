// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int LEN, TIME, MAX;
	static int map[];
	
	public static void DFS(int time, int idx, int sum) {
		if(idx >= LEN || time == TIME) 
		{
			MAX = Math.max(MAX, sum);
			return;
		}
		
		DFS(time+1, idx+1, sum + map[idx]);
		
		if(idx+1 < LEN)
			DFS(time+1, idx+2, sum/2 + map[idx+1]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		LEN		= Integer.parseInt(st.nextToken());
		TIME 	= Integer.parseInt(st.nextToken());
		map 	= new int[LEN];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<LEN; i++)
			map[i] = Integer.parseInt(st.nextToken());
		
		DFS(0, 0, 1);
		
		System.out.print(MAX);
	}
}