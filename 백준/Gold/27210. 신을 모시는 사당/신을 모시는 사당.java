//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27210

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int max		= 0;
		int sum1	= 0;
		int sum2	= 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			int value = Integer.parseInt(st.nextToken());
			
			if(value == 1)
			{
				sum1++;
				sum2--;
			}
			else
			{
				sum1--;
				sum2++;
			}
			
			max = Math.max(max, sum1);
			max = Math.max(max, sum2);
			
			if(sum1 < 0)
				sum1 = 0;
			if(sum2 < 0)
				sum2 = 0;
		}
		System.out.print(max);
	}
}