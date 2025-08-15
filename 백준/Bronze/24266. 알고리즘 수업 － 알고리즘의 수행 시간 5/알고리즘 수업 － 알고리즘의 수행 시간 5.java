//https://www.acmicpc.net/problem/24266

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());
		System.out.print(new StringBuilder().append(N*N*N).append('\n').append(3));
	}
}