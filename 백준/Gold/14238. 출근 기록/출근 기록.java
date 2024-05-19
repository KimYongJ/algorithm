// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static final int 	A = 0;
	static final int 	B = 1;
	static final int 	C = 2;
	static int 			len, A_MAX, B_MAX, C_MAX;
	static char 		make[];
	static String 		base, result;
	static boolean 		visit[][][][][];
	
	public static boolean DFS(int a, int b, int c, int before1, int before2) {
		if(a+b+c == len) 
			return true;
		
		if(visit[a][b][c][before1][before2])
			return false;
		
		visit[a][b][c][before1][before2] = true;
		
		if(a < A_MAX) 
		{
			make[a+b+c] = 'A';
			if(DFS(a+1,b,c, A, before1))
				return true;
		}
		if(b < B_MAX && before1 != B) 
		{
			make[a+b+c] = 'B';
			if(DFS(a,b+1,c, B, before1))
				return true;
		}
		if(c < C_MAX && before1 != C && before2 != C) 
		{
			make[a+b+c] = 'C';
			if(DFS(a,b,c+1, C, before1))
				return true;
		}
		
		return false;
	}
	
	public static void main(String[] args)throws Exception{
		base	= new BufferedReader(new InputStreamReader(System.in)).readLine();
		len		= base.length();
		make	= new char[len];
		visit	= new boolean[len+1][len+1][len+1][3][3];
		result	= "-1";
		
		for(char c : base.toCharArray()) 
		{
			if(c=='A') 		A_MAX++;
			else if(c=='B') B_MAX++;
			else			C_MAX++;
		}
		
		if(DFS(0,0,0,0,0))
			result = String.valueOf(make);
		
		System.out.print(result);
	}
}