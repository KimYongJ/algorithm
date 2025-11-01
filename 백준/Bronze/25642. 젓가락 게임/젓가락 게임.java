//https://www.acmicpc.net/problem/25642
//1초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		boolean isA = true;
		while(A<5 && B<5)
		{
			if(isA) B += A;
			else A += B;
			isA = !isA;
		}
		System.out.print(A>=5 ? "yj" : "yt");
	}
}