//https://www.acmicpc.net/problem/20436
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	static final int pos[][] = {{1,0},{2,4},{2,2},{1,2},{0,2},{1,3},{1,4},{1,5},{0,7},{1,6},{1,7},{1,8},{2,6},{2,5},{0,8},{0,9},{0,0},{0,3},{1,1},{0,4},{0,6},{2,3},{0,1},{2,1},{0,5},{2,0}};
	
	public static void  main(String[] args)throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int L = st.nextToken().charAt(0) - 'a';
		int R = st.nextToken().charAt(0) - 'a';
		String str = br.readLine();
		int result = str.length();

		for(char c : str.toCharArray())
		{
			int now = c - 'a';
			int y = pos[now][0];
			int x = pos[now][1];
			
			if(((y == 0 || y == 1) && x <= 4) || (y == 2 && x <= 3))
			{
				result += get(L, now);
				L = now;
			}
			else {
				result += get(R, now);
				R = now;
			}
		}
		
		System.out.print(result);
	}
	static int get(int a, int b) {
		return Math.abs(pos[a][0] - pos[b][0]) + Math.abs(pos[a][1] - pos[b][1]);
	}
}