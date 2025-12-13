//https://www.acmicpc.net/problem/32685
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 16 - 1;
		
		int A = ((Integer.parseInt(br.readLine()) & T) << 8) |
				((Integer.parseInt(br.readLine()) & T) << 4) |
				(Integer.parseInt(br.readLine()) & T);
		
		String a = String.valueOf(A);
		
		int need = 4 - a.length();
		
		StringBuilder sb = new StringBuilder();
		
		while(need-->0)
			sb.append('0');
		sb.append(a);
		
		System.out.print(sb);
	}
}