// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Main{
	
	static int 			len;
	static char[] 		a, b;
	static boolean[]	visit;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void backtracking(int depth) throws Exception {
		if(depth == len) 
		{
			bw.write(b);
			bw.newLine();
			return;
		}
		
		b[depth] = 0;
		
		for(int i=0; i<len; i++) 
		{
			if(!visit[i] && b[depth] < a[i]) 
			{
				visit[i] = true;
				b[depth] = a[i];
				backtracking(depth + 1);
				visit[i] = false;
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			a 		= br.readLine().toCharArray();
			len 	= a.length;
			b 		= new char[len];
			visit 	= new boolean[len];
			
			Arrays.sort(a);
			
			backtracking(0);
		}
		bw.flush();
		bw.close();
		br.close();
	}
}

