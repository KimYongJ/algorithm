//https://www.acmicpc.net/problem/31282
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double N = Integer.parseInt(st.nextToken());// 사냥개와 토끼의 걸음 걸이차
		int M = Integer.parseInt(st.nextToken());// 토끼의 점프 거리
		int K = Integer.parseInt(st.nextToken());// 사냥개의 점프 거리
		
		System.out.print((int)Math.ceil(N / (K-M)));
		
	}
}