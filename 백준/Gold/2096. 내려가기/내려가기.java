//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2096

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int a,b,c;
		int a1,b1,c1;
		int a2,b2,c2;
		int a3,b3,c3;
		int x,y,z;
		int x1,y1,z1;
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = a1 = Integer.parseInt(st.nextToken());
		b = b1 = Integer.parseInt(st.nextToken());
		c = c1 = Integer.parseInt(st.nextToken());
		
		for(int j=1; j<N; j++)
		{
			st = new StringTokenizer(br.readLine());
			x = x1 = Integer.parseInt(st.nextToken());
			y = y1 = Integer.parseInt(st.nextToken());
			z = z1 = Integer.parseInt(st.nextToken());
			
			a2 = Math.max(a, b) + x;
			b2 = Math.max(a, Math.max(b, c)) + y;
			c2 = Math.max(b, c) + z;
			
			a3 = Math.min(a1, b1) + x1;
			b3 = Math.min(a1, Math.min(b1, c1)) + y1;
			c3 = Math.min(b1, c1) + z1;
			
			a = a2;
			b = b2;
			c = c2;
			a1= a3;
			b1= b3;
			c1= c3;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(Math.max(a, Math.max(b, c))).append(' ')
			.append(Math.min(a1, Math.min(b1, c1)));
		System.out.print(sb);
	}
}