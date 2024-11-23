//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1356

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		for(int i=1; i<str.length(); i++)
		{
			String left = str.substring(0, i);
			String right= str.substring(i, str.length());
			if(cal(left) == cal(right)) {
				System.out.print("YES");
				return;
			}
		}
		System.out.print("NO");
	}
	public static long cal(String str) {
		long res = 1;
		for(char c : str.toCharArray())
			res *= c-'0';
		return res;
	}
}