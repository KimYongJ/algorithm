//https://www.acmicpc.net/problem/20361
//2초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//컵개수
		int X = Integer.parseInt(st.nextToken());//공위치
		int K = Integer.parseInt(st.nextToken());//반복횟수
		
		while(K-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(X == a || b == X) {
				if(X == a)
					X = b;
				else
					X = a;
			}
		}
		System.out.print(X);
	}
}