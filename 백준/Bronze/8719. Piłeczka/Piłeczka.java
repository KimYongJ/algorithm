//https://www.acmicpc.net/problem/8719
//1초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cnt = 0;
			
			while(s<e)
			{
				s <<= 1;
				
				cnt++;
			}
			sb.append(cnt).append('\n');
		}
		
		System.out.print(sb);
	}
}