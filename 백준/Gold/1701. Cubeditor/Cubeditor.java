//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1701

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text	= br.readLine();
		int len		= text.length();
		int max		= 0;
		for(int idx=0; idx<len; idx++)
		{
			
			String pattern = text.substring(idx, len);
			int fail[] = new int[pattern.length()];
			
			for(int i=1, j=0; i<pattern.length(); i++)
			{
				while(0<j && pattern.charAt(j) != pattern.charAt(i))
					j = fail[j - 1];
				
				if(pattern.charAt(j) == pattern.charAt(i))
					max = Math.max(max,fail[i] = ++j);
			}
		}
		
		System.out.print(max);
	}
}