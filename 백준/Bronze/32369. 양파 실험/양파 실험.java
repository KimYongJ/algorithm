//https://www.acmicpc.net/problem/32369
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int l = 1;
		int r = 1;
		
		while(N-->0)
		{
			l += A;
			r += B;
			
			if(l < r)
			{
				int tmp = l;
				l = r;
				r = tmp;
			}
			
			if(l == r)
				r--;
		}
		
		System.out.print(l + " " + r);
	}
}