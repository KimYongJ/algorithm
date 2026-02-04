//https://www.acmicpc.net/problem/34437
//1초 2048MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		while(N != 1) {
			if(N % 2 == 0) N /= 2;
			else N = 3*N + 1;
			++cnt;
		}
		System.out.print(cnt);
	}
}