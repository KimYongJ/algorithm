//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11585

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static char[] text, pattern;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N		= Integer.parseInt(br.readLine());
		text	= new char[N];
		pattern = new char[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			text[i] = st.nextToken().charAt(0);
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			pattern[i] = st.nextToken().charAt(0);
		
		int p = N;
		int c = KMP();
		int gcd = GCD(p, c);
		int a = c/gcd;
		int b = p/gcd;
		
		if(b < a)
			a = b = 1;
		
		StringBuilder sb = new StringBuilder();
		sb.append(a).append('/').append(b);
		System.out.print(sb);
	}
	public static int KMP() {
		int fail[]	= getFail();
		int cnt		= 0;
		int len		= (N<<1) - 1;
		
		for(int k=0, j=0; k<len; k++)
		{
			int i = k % N;
			while(0<j && text[i] != pattern[j])
				j = fail[j - 1];
			
			if(text[i] == pattern[j])
			{
				if(j == N-1)
				{
					++cnt;
					j = fail[j];
				}
				else ++j;
			}
		}
		return cnt;
	}
	public static int[] getFail() {
		int [] fail = new int[N];
		for(int i=1,j=0; i<N; i++)
		{
			while(0<j && pattern[i] != pattern[j])
				j = fail[j - 1];
				
			if(pattern[i] == pattern[j])
				fail[i] = ++j;
		}
		return fail;
	}
	public static int GCD(int a, int b) {
		return b==0 ? a : GCD(b, a%b);
	}
}