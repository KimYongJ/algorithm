//https://www.acmicpc.net/problem/12589
//5초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = 1 << Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			sb.append("Case #").append(i).append(": ")
				.append(K % N == N - 1 ? "ON" : "OFF")
				.append('\n');
		}
		
		System.out.print(sb);
	}
}