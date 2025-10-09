//https://www.acmicpc.net/problem/14954
//0.2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<Integer> set = new HashSet<>();
		int base = Integer.parseInt(br.readLine());
		int a = base;
		
		while(true)
		{
			int b = getNext(a);
			
			if(set.contains(b))
			{
				System.out.print(b ==1 ? "HAPPY" : "UNHAPPY");
				return;
			}
			set.add(b);
			a = b;
		}
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