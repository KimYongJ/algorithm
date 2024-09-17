//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18114

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 물건의 개수(1<=오천)
		int C		= Integer.parseInt(st.nextToken());	// 무게(1<=억)
		int arr[]	= new int[N];						// 무게(1<=억)
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] == C)
			{
				System.out.print(1);
				return;
			}
		}
		
		Arrays.sort(arr);
		
		int s = 0;
		int e = N - 1;
		while(s < e)
		{
			int sum = arr[s] + arr[e];
			if(sum == C)
			{
				System.out.print(1);
				return;
			}
			
			for(int i=s+1; i<e; i++) {
				if(arr[i] + sum == C) {
					System.out.print(1);
					return;
				}
				if(arr[i] + sum > C) break;
			}
			
			if(sum < C) s++;
			else e--;
		}
		System.out.print(0);
	}
}