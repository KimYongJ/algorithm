//https://www.acmicpc.net/problem/24578
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		char[][] s = new char[4][4];
		
		for(int i=0; i<4; i++) {
			int num = str.charAt(i) - '0';
			for(int j=3,z = 0; j>=0; j--,z++) {
				s[j][i] = '.';
				if(((1<<z) & num) != 0)
					s[j][i] = '*';
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				sb.append(s[i][j]);
				if(j != 3) sb.append(' ');
				if(j == 1) sb.append("  ");
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}