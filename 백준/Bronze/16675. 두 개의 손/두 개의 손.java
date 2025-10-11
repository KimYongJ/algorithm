//https://www.acmicpc.net/problem/3060
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		char L[] = new char[] {str.charAt(0),str.charAt(2)};
		char R[] = new char[] {str.charAt(4),str.charAt(6)};
		
		for(char left : L)
		{
			int leftWin = 0;
			
			if(isWin(left, R[0])) leftWin++;
			if(isWin(left, R[1])) leftWin++;
			
			if(leftWin > 1)
			{
				System.out.print("MS");
				return;
			}
		}
		
		for(char right : R)
		{
			int rightWin = 0;
			if(isWin(right, L[0])) rightWin++;
			if(isWin(right, L[1])) rightWin++;
			if(rightWin > 1)
			{
				System.out.print("TK");
				return;
			}
		}
		
		System.out.print("?");
		
	}
	static boolean isWin(char a, char b) {
		if(a == 'R')return b == 'S';
		if(a == 'S')return b == 'P';
		return b == 'R';
	}
}