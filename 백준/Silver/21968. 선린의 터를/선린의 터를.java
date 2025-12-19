//https://www.acmicpc.net/problem/21968
//1초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			long N = Long.parseLong(br.readLine());
			long res = 0;
			for(long i=0, plus = 1; i<64; i++)
			{
				if(((1L<<i) & N) != 0)
					res += plus;

				plus *= 3;
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb);
	}
}