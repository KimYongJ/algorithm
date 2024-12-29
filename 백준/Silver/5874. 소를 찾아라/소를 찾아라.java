//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5874

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str	= br.readLine().toCharArray();
		int len		= str.length;
		int sum		= 0;
		int cnt		= 0;
		for(int i=0; i<len - 1; i++)
		{
			if(str[i] == str[i+1] && str[i] == '(')
				++cnt;
			if(str[i] == str[i+1] && str[i] == ')')
				sum += cnt;
		}
		System.out.print(sum);
	}
}