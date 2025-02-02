//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15624
//1초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N+2];
		arr[1] = 1;
		
		for(int i=2; i<=N; i++)
			arr[i] = (arr[i-1] + arr[i-2]) % 1_000_000_007;
		
		System.out.print(arr[N]);
	}
}