//https://www.acmicpc.net/problem/27960
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		System.out.print(Integer.parseInt(st.nextToken()) ^ Integer.parseInt(st.nextToken()));
	}
}