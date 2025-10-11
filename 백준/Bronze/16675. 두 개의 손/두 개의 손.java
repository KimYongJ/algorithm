//https://www.acmicpc.net/problem/3060
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		char l1 = str.charAt(0);
		char l2 = str.charAt(2);
		char r1 = str.charAt(4);
		char r2 = str.charAt(6);
		int leftWin = 0;
		if(isWin(l1, r1)) leftWin++;
		if(isWin(l1, r2)) leftWin++;
		if(leftWin > 1)
		{
			System.out.print("MS");
			return;
		}
		
		leftWin = 0;
		if(isWin(l2, r1)) leftWin++;
		if(isWin(l2, r2)) leftWin++;
		
		if(leftWin > 1)
		{
			System.out.print("MS");
			return;
		}
		
		int rightWin = 0;
		if(isWin(r1, l1)) rightWin++;
		if(isWin(r1, l2)) rightWin++;
		if(rightWin > 1) {
			System.out.print("TK");
			return;
		}
		rightWin = 0;
		if(isWin(r2, l1)) rightWin++;
		if(isWin(r2, l2)) rightWin++;
		if(rightWin > 1) {
			System.out.print("TK");
			return;
		}
		
		System.out.print("?");
		
	}
	static boolean isWin(char a, char b) {
		if(a == 'R')return b == 'S';
		if(a == 'S')return b == 'P';
		return b == 'R';
	}
}