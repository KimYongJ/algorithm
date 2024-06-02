// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static final int	LEN = 10;
	static int			N, i;
	static long 		result = -1;
	
	public static boolean DFS(int depth,int before, long num) {
		if(depth == i) 
		{
			if(--N == 0) 
			{
				result = num;
				return true;
			}
			return false;
		}
		
		for(int i=0; i<=9 && before > i; i++) 
			if(DFS(depth+1, i, num*10 + (long)i))
				return true;

		return false;
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		if(N<=10) 
		{
			result = N-1;
		}
		else 
		{
			N -= 10;
			Loop : 
			for(i=2; i<=LEN; i++)
				for(int j=1; j<=9; j++) 
					if(DFS(1,j, (long)j))
						break Loop;
		}
		System.out.print(result);
	}
}