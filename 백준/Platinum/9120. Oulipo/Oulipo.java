//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9120

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			char[] pattern	= br.readLine().toCharArray();
			char[] text		= br.readLine().toCharArray();
			int fail[]		= new int[pattern.length];
			int res			= 0;
			
			for(int i=1,j=0; i<pattern.length; i++)
			{
				while(0<j && pattern[i] != pattern[j])
					j = fail[j - 1];
				if(pattern[i] == pattern[j])
					fail[i] = ++j;
			}
			
			for(int i=0,j=0; i<text.length; i++)
			{
				while(0<j && text[i] != pattern[j])
					j = fail[j - 1];
				
				if(text[i] == pattern[j])
				{
					if(j == pattern.length - 1)
					{
						j = fail[j];
						++res;
					}
					else ++j;
				}
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb);
	}
}