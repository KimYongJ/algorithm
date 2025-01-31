//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25644
//1초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int min		= 1<<30;
		int result	= 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			int n = Integer.parseInt(st.nextToken());
			result = Math.max(result, n - min);
			min = Math.min(min, n);
		}

		System.out.print(result);
	}
}
