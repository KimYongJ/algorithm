//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18171

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int len = Integer.parseInt(br.readLine());
		for(char c : br.readLine().toCharArray())
			sb.append(' ').append(c);
		sb.append(' ');
		
		len = sb.length();
		char[] str	= sb.toString().toCharArray();
		int A[]		= new int[len];
		
		for(int i=0,r=0,p=0; i<len; i++)
		{
			if(i<=r)
				A[i] = Math.min(r-i, A[2*p-i]);
			
			while(0<=i-A[i]-1 && i+A[i]+1<len && str[i-A[i]-1] == str[i+A[i]+1])
				++A[i];
			
			if(r < i + A[i])
			{
				r = i + A[i];
				p = i;
			}
		}
		int maxNum = 0;
		int maxIdx = len-1;
		for(int idx=len-1, i=0; idx>=0; idx--,i++) {
			if(A[idx] == i && maxNum < A[idx]) {
				maxNum = A[idx];
				maxIdx = idx;
			}
		}
		System.out.print((maxIdx - maxNum) / 2);
	}
}