//https://www.acmicpc.net/problem/34848
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			int cnt = 0;
			int N = Integer.parseInt(br.readLine());
			while(N != 1)
			{
				if(N % 2 == 1)
					++cnt;
				N = (N + 1) / 2;
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
}