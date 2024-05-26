// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int answer[] = new int[10];
	static int cnt;
	
	public static void DFS(int depth, int prev, int pprev, int sum) {
		if(depth < 0) 
		{
			if(sum >= 5) 
				cnt++;
			return;
		}
		
		for(int i=1; i<=5; i++)
			if(i != prev || i != pprev)
				DFS(depth - 1, i, prev, sum + (answer[depth] == i ? 1 : 0));	
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<10; i++)
			answer[i] = Integer.parseInt(st.nextToken());
		
		DFS(9,0,0,0);
		
		System.out.print(cnt);
	}
}