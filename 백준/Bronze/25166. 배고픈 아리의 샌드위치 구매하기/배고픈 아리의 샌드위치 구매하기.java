//https://www.acmicpc.net/problem/25166
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()) - ((1<<10)-1);
		int M = Integer.parseInt(st.nextToken());
		
		String str = "Impossible";
		
		if(S <= 0)
			str = "No thanks";
		else if((S & M) == S)
			str = "Thanks";
			
		System.out.print(str);
	}
}