//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15489
//1초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());	// y 좌표
		int C = Integer.parseInt(st.nextToken());	// x 좌표
		int W = Integer.parseInt(st.nextToken());	// 변길이
		int len = R + W;
		int arr[][] = new int[len][len];
		
		arr[0][0] = 1;
		
		for(int y=1; y<len; y++)
			for(int x=1; x<=y; x++)
				arr[y][x] = arr[y-1][x] + arr[y-1][x-1];

		
		long sum = 0;
		for(int y=R, limit = 0; y<len; y++, limit++)
			for(int x=C; x<=C + limit; x++)
				sum += arr[y][x];

		System.out.print(sum);
	}
}