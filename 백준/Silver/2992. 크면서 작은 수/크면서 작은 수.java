// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
	
	static int len;
	static char base[], dummy[], des[];
	static boolean visit[];
	public static boolean check() {
		for(int i=0; i<len; i++) {
			if(base[i] > des[i]) return false;
			else if(base[i] < des[i]) return true;
		}
		return false;
	}
	public static boolean backtracking(int depth) {
		if(depth == len) 
		{
			if(check())
				return true;
			return false;
		}
		for(int i=0; i<len; i++)
			if(!visit[i]) 
			{
				visit[i] 	= true;
				des[depth] 	= dummy[i];
				if(backtracking(depth + 1)) return true;
				visit[i] 	= false;
			}

		return false;
	}
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		base	= br.readLine().toCharArray();
		len 	= base.length;
		dummy 	= base.clone();
		des		= new char[len];
		visit 	= new boolean[len];
		
		Arrays.sort(dummy);
		
		if(backtracking(0))
			System.out.print(String.valueOf(des));
		else
			System.out.print("0");
	}
}