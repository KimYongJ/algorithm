//https://www.acmicpc.net/problem/25495
//1초 1024

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int e = 0;
		int m = 2;
		int prev = -1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
		{
			int now = Integer.parseInt(st.nextToken());
			
			if(now == prev)
				m <<= 1;
			else
				m = 2;
			
			if(e + m >= 100)
			{
				e = 0;
				m = 2;
				prev = -1;
				continue;
			}
			
			e += m;
			
			prev = now;
		}
		
		System.out.print(e);
	}
}