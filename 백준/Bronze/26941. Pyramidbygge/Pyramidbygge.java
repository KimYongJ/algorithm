//https://www.acmicpc.net/problem/26941
//1초 1024MB
//83 // 사용가능 블록 수(1<=100,000,000)
//피라미드 높이 : 3

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()) - 1;
		int len = 1;
		int cnt = 1;
		
		while(N>0)
		{
			++cnt;
			len+=2;
			N -= len * len;
		}
		
		if(N < 0)
			--cnt;
		
		System.out.print(cnt);
	}
}