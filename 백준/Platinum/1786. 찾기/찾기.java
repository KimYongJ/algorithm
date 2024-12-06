//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1786
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		int tlen = text.length;
		int plen = pattern.length;
		int fail[] = new int[plen];
		
		for(int i=1, j=0; i<plen; i++)
		{
			while(0<j && pattern[i] != pattern[j])
				j = fail[j - 1];
			if(pattern[i] == pattern[j])
				fail[i] = ++j;
		}
		
		ArrayList<Integer> ans = new ArrayList<>();
		
		for(int i=0, j=0; i<tlen; i++)
		{
			while(0<j && text[i] != pattern[j])
				j = fail[j - 1];
			
			if(text[i] == pattern[j])
			{
				if(j == plen - 1)
				{
					ans.add(i - j + 1);
					j = fail[j];
				}
				else ++j;
			}
		}
		
		Collections.sort(ans);
		
		StringBuilder sb = new StringBuilder();
		sb.append(ans.size()).append('\n');
		for(int a : ans)
			sb.append(a).append(' ');
		System.out.print(sb);
	}
}