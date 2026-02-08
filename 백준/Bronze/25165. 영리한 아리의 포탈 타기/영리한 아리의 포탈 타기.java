//https://www.acmicpc.net/problem/25165
//1초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int sx = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int ey = Integer.parseInt(st.nextToken());
		int ex = Integer.parseInt(st.nextToken());
		
		System.out.print(ey == n && (n%2) != dir ? "YES!" : "NO...");
	}
}