//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9655
//1초 / 128mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		System.out.print(N % 2 == 1 ? "SK" : "CY");
	}
}
