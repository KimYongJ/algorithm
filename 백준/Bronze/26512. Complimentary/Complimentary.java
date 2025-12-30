//https://www.acmicpc.net/problem/26512
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			
			if(X == 0 && Y == 0)
				break;
			
			
			sb.append(change(sb,X)).append('\n')
			.append(change(sb,Y)).append('\n')
			.append(change(sb,-X)).append('\n')
			.append(change(sb,-Y)).append('\n')
			.append(change(sb, X-Y)).append('\n')
			.append('\n');
		}
		
		System.out.print(sb);
	}
	static String change(StringBuilder sb, int n)
	{
		sb.append(n).append(" = ");
		String str = Integer.toBinaryString(n);
		if(str.length() > 8)
			return str.substring(str.length() - 8, str.length());
		else if(str.length() < 8)
			return "0".repeat(8 - str.length()) + str;
		return str;
	}
}