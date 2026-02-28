//https://www.acmicpc.net/problem/34455
//1초 2048MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
			N += ("+".equals(br.readLine()) ? 1 : -1)
					* Integer.parseInt(br.readLine());
		
		System.out.print(N);
	}
}