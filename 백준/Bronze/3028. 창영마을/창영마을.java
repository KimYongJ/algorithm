//https://www.acmicpc.net/problem/3028
//1초 128MB
//AB / A(1,2섞기), B(2,3섞기), C(1,3섞기)
//답 : 3

import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int now = 1;
		
		for(int i=0; i<str.length(); i++)
		{
			char c = str.charAt(i);
			if(c == 'A' && (now == 1 || now == 2)) {
				now = now == 1 ? 2 : 1;
			}
			else if(c == 'B' && (now == 2 || now == 3)) {
				now = now == 2 ? 3 : 2;
			}
			else if(c == 'C' && (now == 1 || now == 3)) {
				now = now == 3 ? 1 : 3;
			}
		}
		System.out.print(now);
	}
}