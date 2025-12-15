//https://www.acmicpc.net/problem/28239
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			long m = Long.parseLong(br.readLine());
			int x = Long.numberOfTrailingZeros(m);
			
			if(Long.bitCount(m) == 1) {
				sb.append(x-1).append(' ').append(x-1).append('\n');
			}
			else {
				m &= (m - 1);
				int y = Long.numberOfTrailingZeros(m);
				sb.append(x).append(' ').append(y).append('\n');
			}
			
		}
		System.out.print(sb);
	}
}

