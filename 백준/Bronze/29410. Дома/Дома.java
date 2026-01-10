//https://www.acmicpc.net/problem/29410
//2초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int res = 0;
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken());
			
			while(j-->0)
			{
				int n = Integer.parseInt(st.nextToken());
				int ones = Integer.bitCount(n);
				res += ones * b;
				res += (32 - Integer.numberOfLeadingZeros(n) - ones) * a;
			}
			
		}
		System.out.print(res);
	}
}