//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4354
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	static String pattern;
	static int len;
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		pattern = br.readLine();
		while(!".".equals(pattern))
		{
			len = pattern.length();
			int fail[] = getFail();
			int ans = 1;
			
			if(len%(len - fail[len - 1])==0)
				ans = len / (len - fail[len-1]);
			
			sb.append(ans).append('\n');
			
			pattern = br.readLine();
		}
		System.out.print(sb);
	}
	public static int[] getFail() {
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