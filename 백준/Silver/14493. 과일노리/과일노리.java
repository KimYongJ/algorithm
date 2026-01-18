//https://www.acmicpc.net/problem/14493
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int t = 0;
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int mod = t % (a+b);
			
			if(mod < b)
				t += b - mod;
			
			t++;
		}
		System.out.print(t);
	}
}