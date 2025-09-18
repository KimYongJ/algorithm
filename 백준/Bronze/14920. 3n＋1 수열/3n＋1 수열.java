//https://www.acmicpc.net/problem/14920
//1초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int now = Integer.parseInt(br.readLine());
		int cnt = 1;
		
		while(now != 1)
		{
			if((now & 1) == 0) now >>= 1;
			else now = 3 * now  + 1;
			++cnt;
		}
		System.out.print(cnt);
	}
}