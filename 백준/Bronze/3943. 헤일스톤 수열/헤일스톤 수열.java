//https://www.acmicpc.net/problem/3943
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());
			int M = N;
			
			while(N != 1)
			{
				if(N % 2 == 0)
					N /= 2;
				else
					N = N * 3 + 1;
				
				if(M < N)
                    M = N;
			}
			
			sb.append(M).append('\n');
		}
		System.out.print(sb);
	}
}