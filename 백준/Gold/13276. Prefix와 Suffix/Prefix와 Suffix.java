//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13276

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text = br.readLine();
		String ptn1 = br.readLine();
		String ptn2 = br.readLine();
		int tlen	= text.length();
		int plen1	= ptn1.length();
		int plen2	= ptn2.length();
		int fail1[] = getFail(ptn1, plen1);
		int fail2[] = getFail(ptn2, plen2);
		boolean start1[] = KMP(text, ptn1, fail1);
		boolean start2[] = KMP(text, ptn2, fail2);

		HashSet<String> set = new HashSet<>();
		
		for(int i=0; i<tlen; i++)
		{
			if(start1[i])
			{
				for(int j=i; j<tlen; j++)
					if(start2[j])
					{
						int end1 = i + plen1;
						int end2 = j + plen2;
						if(end1 <= end2) {
							String res = text.substring(i, j + plen2);
							set.add(res);
						}
					}
			}
		}
		System.out.print(set.size());
	}
	public static boolean[] KMP(String text, String pattern, int[] fail) {
		int len			= text.length();
		int plen		= pattern.length();
		boolean start[] = new boolean[len];
		
		for(int i=0,j=0; i<len; i++)
		{
			while(0<j && text.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
			
			if(text.charAt(i) == pattern.charAt(j))
			{
				if(j + 1 == plen)
				{
					j = fail[j];
					start[i - plen + 1] = true;
				}
				else ++j;
			}
		}
		return start;
	}
	public static int[] getFail(String pattern, int len) {
		int fail[] = new int[len];
		for(int i=1, j=0; i<len; i++)
		{
			while(0<j && pattern.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
			
			if(pattern.charAt(i) == pattern.charAt(j))
				fail[i] = ++j;
		}
		return fail;
	}
}