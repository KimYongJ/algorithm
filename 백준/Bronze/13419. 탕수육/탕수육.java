//https://www.acmicpc.net/problem/13419
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder left = new StringBuilder();
		StringBuilder right = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			left.setLength(0);
			right.setLength(0);
			
			String str = br.readLine();
			
			if(str.length() == 1)
			{
				left.append(str);
				right.append(str);
			}
			else
			{
				for(int i=0; i<str.length(); i++)
					if(i % 2 == 0) left.append(str.charAt(i));
					else right.append(str.charAt(i));
				
				if(str.length() % 2 != 0)
				{
					for(int i=0; i<str.length(); i++)
						if(i % 2 == 0) right.append(str.charAt(i));
						else left.append(str.charAt(i));
				}
			}
			
			
			sb.append(left).append('\n').append(right).append('\n');
		}
		System.out.print(sb);
	}
}