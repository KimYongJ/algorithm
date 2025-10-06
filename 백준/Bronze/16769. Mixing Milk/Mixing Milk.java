//https://www.acmicpc.net/problem/16769
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c[] = new int[3];
		int n[] = new int[3];
		
		for(int i=0; i<3; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			c[i] = Integer.parseInt(st.nextToken());
			n[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		int now = 0;
		
		while(++cnt <= 100)
		{
			int next = (now + 1) % 3;
			int val = Math.min(c[next] - n[next], n[now]);
			
			n[next] += val;
			n[now] -= val;
			
			now = next;
		}
		StringBuilder sb = new StringBuilder();
		
		for(int n1 : n) sb.append(n1).append(' ');
		
		System.out.print(sb);
	}
}