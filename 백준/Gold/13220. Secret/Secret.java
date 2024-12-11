//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13220

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());	// 1<=100000
		int len			= N<<1;
		int text[]		= new int[len];
		int pattern[]	= new int[N];
		int fail[]	 	= new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0,j=N; i<N; i++,j++)
			text[j] = text[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			pattern[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1, j=0; i<N; i++)
		{
			while(0<j && pattern[i] != pattern[j])
				j = fail[j - 1];
			if(pattern[i] == pattern[j])
				fail[i] = ++j;
		}
		for(int i=0, j=0; i<len; i++)
		{
			while(0<j && text[i] != pattern[j])
				j = fail[j - 1];
			
			if(text[i] == pattern[j])
			{
				if(j + 1 == N)
				{
					System.out.print("YES");
					return;
				}else ++j;
			}
		}
		System.out.print("NO");
	}
}