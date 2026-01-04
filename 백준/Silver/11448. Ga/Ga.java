//https://www.acmicpc.net/problem/11448
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static int N;
	static int res;
	static char arr[][];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{1,-1},{1,1},{-1,1}};
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			res = 0;
			N = Integer.parseInt(br.readLine());
			arr = new char[N][N];
			
			for(int i=0; i<N; i++)
			{
				String str = br.readLine();
				for(int j=0; j<N; j++)
					arr[i][j] = str.charAt(j);
			}
			
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					if(arr[i][j] == 'w')
						dfs(i, j);
			
			sb.append(res).append('\n');
		}
		
		System.out.print(sb);
	}
	static void dfs(int i,int j) {
		if(arr[i][j] == '-')
			++res;
		
		arr[i][j] = 'b';
		
		for(int xy[] : dxy)
		{
			int nextI = i + xy[0];
			int nextJ = j + xy[1];
			
			if(nextI<0 || nextJ <0 || nextI>=N || nextJ>=N)
				continue;
			
			if(arr[nextI][nextJ] != 'b')
				dfs(nextI, nextJ);
		}
	}
}