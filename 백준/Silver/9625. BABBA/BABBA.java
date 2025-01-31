//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9625
//1초 / 128mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());	// 1<=45
		int A[] = new int[46];
		int B[] = new int[46];
		A[0] = 1;
		B[1] = 1;
		
		for(int i=1; i<=K; i++)
		{
			A[i] = B[i-1];
			B[i] = B[i-1] + A[i-1];
		}
		System.out.print(new StringBuilder().append(A[K]).append(' ').append(B[K]));
		
	}
}
