//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11585

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static String text, pattern;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			sb.append(st.nextToken());
		
		text = sb.toString();
		text += text;
		sb.setLength(0);
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			sb.append(st.nextToken());
		pattern = sb.toString();
		
		int p = N;
		int c = KMP();
		int gcd = GCD(p, c);
		int a = c/gcd;
		int b = p/gcd;
		
		if(b < a)
			a = b = 1;
		
		sb.setLength(0);
		sb.append(a).append('/').append(b);
		System.out.print(sb);
	}
	public static int KMP() {
		int fail[]	= getFail();
		int cnt		= 0;
		int len		= (N<<1) - 1;
		
		for(int i=0, j=0; i<len; i++)
		{
			while(0<j && text.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
			
			if(text.charAt(i) == pattern.charAt(j))
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
			while(0<j && pattern.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
				
			if(pattern.charAt(i) == pattern.charAt(j))
				fail[i] = ++j;
		}
		return fail;
	}
	public static int GCD(int a, int b) {
		return b==0 ? a : GCD(b, a%b);
	}
}