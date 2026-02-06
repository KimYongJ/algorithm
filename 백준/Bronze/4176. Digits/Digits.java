//https://www.acmicpc.net/problem/4176
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			String str = br.readLine().trim();
			
			if(str == null || str.length() == 0 || "END".equals(str))
				break;
			
			if("1".equals(str))
			{
				sb.append(1).append('\n');
				continue;
			}
			
			int len = str.length();
			int prev = 0;
			int i = 1;
			while(len != prev)
			{
				prev = len;
				len = String.valueOf(len).length();
				i++;
			}
			sb.append(i).append('\n');
		}
		System.out.print(sb);
	}
}