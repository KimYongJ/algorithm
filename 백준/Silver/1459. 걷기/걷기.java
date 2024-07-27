//https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long X = Integer.parseInt(st.nextToken());
		long Y = Integer.parseInt(st.nextToken());
		long W = Integer.parseInt(st.nextToken()); // 가로세로
		long S = Integer.parseInt(st.nextToken()); // 대각선
		long result1 = (X+Y)*(long)W; // 가로세로로만 
		long result2 = 0; 
		if((X+Y) % 2 == 0) // 대각선으로만 갈 수 있다
		{
			result2 = Math.max(X, Y) * S;
		}
		else 
		{
			result2 = (Math.max(X, Y)-1) * S + W;
		}
		
		long result3 = Math.min(X, Y) *S + Math.abs(X-Y)*W;
		
		
		System.out.print(Math.min(result3,Math.min(result1, result2)));
	}
}