//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1356

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int len = str.length();
		for(int i=1; i<len; i++)
		{
			int left = 1;
			for(int s=0; s<i; s++)
				left *= str.charAt(s)-'0';
			
			int right= 1;
			for(int s=i; s<len; s++)
				right *= str.charAt(s)-'0';
			
			if(left == right)
			{
				System.out.print("YES");
				return;
			}
		}
		System.out.print("NO");
	}
}