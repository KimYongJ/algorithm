//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13275

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(char c : br.readLine().toCharArray())
			sb.append(' ').append(c);
		sb.append(' ');
		
		System.out.print(manacher(sb.toString().toCharArray(), sb.length()));
	}
	public static int manacher(char[] str, int len) {
		int A[] = new int[len];
		int max	= 0;
		for(int i=0,r=0,p=0; i<len; i++)
		{
			if(i<=r)
				A[i] = Math.min(r-i, A[2*p - i]);
			
			while(0<=i-A[i]-1 && i+A[i]+1<len && str[i-A[i]-1] == str[i+A[i]+1])
				++A[i];
			
			if(r < i+A[i])
			{
				r = i+A[i];
				p = i;
			}
			max = Math.max(max, A[i]);
		}
		return max;
	}
}