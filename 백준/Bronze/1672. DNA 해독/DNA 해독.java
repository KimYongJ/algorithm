//https://www.acmicpc.net/problem/1672
//5초 16MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		char[][] val = get();
		char str[] = br.readLine().toCharArray();
		
		for(int j = N - 1; j>= 1 ; j--)
		{
			int y = getIdx(str[j]);
			int x = getIdx(str[j-1]);
			str[j - 1] = val[y][x];
		}
		System.out.print(str[0]);
	}
	public static char[][] get(){
		return new char[][] {
			{'A','C','A','G'},
			{'C','G','T','A'},
			{'A','T','C','G'},
			{'G','A','G','T'}
		};
	}
	public static int getIdx(char c) {
		switch(c) {
		case 'A' : return 0;
		case 'G' : return 1;
		case 'C' : return 2;
		default : return 3;
		}
	}
}