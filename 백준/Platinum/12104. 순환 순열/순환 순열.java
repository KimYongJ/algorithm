//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12104

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pattern	= br.readLine();
		String text		= br.readLine();

		int fail[]	= new int[pattern.length()];
		int cnt		= 0;
		
		for(int i=1, j=0; i<pattern.length(); i++)
		{
			while(0<j && pattern.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
			
			if(pattern.charAt(i) == pattern.charAt(j))
				fail[i] = ++j;
		}
		
		
		text += text;
		
		int len	= text.length();
		for(int i=0,j=0; i<len-1; i++)
		{
			while(0<j && text.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
			
			if(text.charAt(i) == pattern.charAt(j))
			{
				if(j == pattern.length() - 1)
				{
					j = fail[j];
					++cnt;
				}
				else ++j;
			}
		}
		
		
		System.out.print(cnt);
	}
}