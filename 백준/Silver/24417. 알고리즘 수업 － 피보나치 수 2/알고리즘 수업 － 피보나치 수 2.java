//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24417
//1초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int a = 1;
		int b = 1;
		int c = 2;
		
		for(int i=3; i<N; i++)
		{
			a = b + c;
			b = c;
			c = a % 1_000_000_007;
		}
		
		System.out.print(new StringBuilder().append(c).append(' ').append(N-2));
		
	}
}