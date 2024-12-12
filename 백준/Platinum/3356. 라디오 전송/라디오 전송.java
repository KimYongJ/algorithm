//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3356
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int fail[]	= getFail(N, br.readLine());
		System.out.print(N - fail[N-1]);
	}
	public static int[] getFail(int len, String pattern) {
		int fail[]	= new int[len];
		
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