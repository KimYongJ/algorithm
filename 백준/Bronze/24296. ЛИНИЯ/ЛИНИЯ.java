//https://www.acmicpc.net/problem/24296
//0.5초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		while(N % 2 != 0)
			N = (N + 1) / 2;
		
		System.out.print(N);
	}
}