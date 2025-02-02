//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14494
//2초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		long arr[][] = new long[Y+1][X+1];
		
		arr[0][0] = 1;
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
			{
				arr[y][x] = arr[y-1][x] + arr[y][x-1] + arr[y-1][x-1];
//				if(arr[y][x] >= 1_000_000_007)
					arr[y][x] %= 1_000_000_007;
			}
		
		System.out.print(arr[Y][X]);
	}
}