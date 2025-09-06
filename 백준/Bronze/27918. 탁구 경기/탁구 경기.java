//https://www.acmicpc.net/problem/27918
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int X = 0;
		int Y = 0;
		
		while(N-->0 && Math.abs(X - Y) < 2)
		{
			char c = br.readLine().charAt(0);
			
			if(c == 'D')
				++X;
			else
				++Y;
		}
		
		System.out.print(X + ":" + Y);
		
	}
}