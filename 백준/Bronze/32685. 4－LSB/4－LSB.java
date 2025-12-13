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
		
		System.out.print(String.format("%04d", A));
	}
}