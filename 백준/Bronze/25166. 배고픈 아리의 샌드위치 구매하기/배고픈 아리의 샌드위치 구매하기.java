//https://www.acmicpc.net/problem/25166
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String str = "Thanks";
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int N = S - ((1<<10)-1);
		
		if(N <= 0)
			str = "No thanks";
		else if(!((N & M) == N))
			str = "Impossible";
			
		System.out.print(str);
	}
}