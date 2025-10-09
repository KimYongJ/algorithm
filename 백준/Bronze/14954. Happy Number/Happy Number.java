//https://www.acmicpc.net/problem/14954
//0.2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int base = Integer.parseInt(br.readLine());
		
		while(base != 1 && base != 4)
			base = getNext(base);
		
		System.out.println(base == 1 ? "HAPPY" : "UNHAPPY");
	}
	static int getNext(int a) {
		int res = 0;
		while(a != 0)
		{
			int mod = a % 10;
			res += mod * mod;
			a /= 10;
		}
		return res;
	}
}