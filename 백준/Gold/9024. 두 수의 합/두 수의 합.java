//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9024

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-- >0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N		= Integer.parseInt(st.nextToken());	// 2<=백만
			int K		= Integer.parseInt(st.nextToken());	// -억<=억
			int arr[]	= new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(arr);
			
			int diff	= Integer.MAX_VALUE;
			int cnt		= 0;
			int s		= 0;
			int e		= N-1;

			while(s < e)
			{
				int sum = arr[s] + arr[e];
				int abs = Math.abs(K - sum);
				if(diff >= abs)
				{
					if(diff == abs)
						cnt++;
					else
					{
						cnt = 1;
						diff = abs;
					}
				}
				if(sum < K) s++;
				else e--;
			}
			
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}