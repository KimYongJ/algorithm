//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5618

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int n		= Integer.parseInt(br.readLine());
		int num[]	= new int[n];
		int max		= 0;
		boolean flag= false;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			max = Math.max(max,num[i] = Integer.parseInt(st.nextToken()));
		
		for(int i=1; i<=max; i++, flag = false)
		{
			for(int m : num)
				flag |= m % i != 0;

			if(!flag)
				sb.append(i).append('\n');
		}
		
		System.out.print(sb);
	}
}