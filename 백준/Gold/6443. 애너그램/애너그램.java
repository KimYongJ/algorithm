// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
	
	static int 			len;
	static char[] 		a, b, c;
	static boolean[]	visit;
	static StringBuilder sb = new StringBuilder();
	
	public static void backtracking(int depth) {
		if(depth == len) 
		{
			sb.append(String.valueOf(b)).append('\n');
			return;
		}
		
		c[depth] = 0;
		
		for(int i=0; i<len; i++) 
		{
			if(!visit[i] && c[depth] < a[i]) 
			{
				c[depth] = a[i];
				visit[i] = true;
				b[depth] = a[i];
				backtracking(depth + 1);
				visit[i] = false;
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			a 		= br.readLine().toCharArray();
			len 	= a.length;
			b 		= new char[len];
			c		= new char[len];
			visit 	= new boolean[len];
			
			Arrays.sort(a);
			
			backtracking(0);
		}
		System.out.print(sb);
	}
}

