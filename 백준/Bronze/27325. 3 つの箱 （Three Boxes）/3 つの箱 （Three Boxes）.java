//https://www.acmicpc.net/problem/27325
//2초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer.parseInt(br.readLine());
		int I = 1;
		int R = 0;
		for(char c : br.readLine().toCharArray())
		{
			if(c == 'L' && I != 1)
				I--;
			if(c == 'R' && ++I >= 3)
			{
				R++;
				I = 3;
			}
		}
		System.out.print(R);
	}
}