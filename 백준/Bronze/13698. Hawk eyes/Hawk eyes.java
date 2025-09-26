//https://www.acmicpc.net/problem/13698
//1초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static int min, max;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		min = 1;
		max = 4;
		
		for(char c : br.readLine().toCharArray())
		{
			switch(c)
			{
				case 'A': check(1, 2);break;
				case 'B': check(1, 3);break;
				case 'C': check(1, 4);break;
				case 'D': check(2, 3);break;
				case 'E': check(2, 4);break;
				case 'F': check(3, 4);break;
			}
		}
		
		System.out.println(min);
		System.out.println(max);
	}
	static void check(int v1, int v2) {
		if(min == v1 || min == v2)min = min == v1 ? v2 : v1;
		if(max == v1 || max == v2)max = max == v1 ? v2 : v1;
	}
}