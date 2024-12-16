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
			String pattern	= br.readLine();
			String text		= br.readLine();
			int plen		= pattern.length();
			int tlen		= text.length();
			int fail[]		= new int[plen];
			int res			= 0;
			for(int i=1,j=0; i<plen; i++)
			{
				while(0<j && pattern.charAt(i) != pattern.charAt(j))
					j = fail[j - 1];
				if(pattern.charAt(i) == pattern.charAt(j))
					fail[i] = ++j;
			}
			
			for(int i=0,j=0; i<tlen; i++)
			{
				while(0<j && text.charAt(i) != pattern.charAt(j))
					j = fail[j - 1];
				
				if(text.charAt(i) == pattern.charAt(j))
				{
					if(j == plen - 1)
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