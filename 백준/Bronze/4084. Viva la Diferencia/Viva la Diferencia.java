//https://www.acmicpc.net/problem/4084
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int cnt = 0;
			if(a == 0)
				break;
			
			while(!(a == b && b == c && c == d && d == a)) {
				++cnt;
				
				int a1 = Math.abs(a - b);
				int b1 = Math.abs(b - c);
				int c1 = Math.abs(c - d);
				int d1 = Math.abs(d - a);
				a = a1;
				b = b1;
				c = c1;
				d = d1;
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
}