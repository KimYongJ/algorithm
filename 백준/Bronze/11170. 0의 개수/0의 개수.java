//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11170

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		final int MAX	= 1_000_001;
		int dp[]		= new int[MAX];
		dp[0]			= 1;
		for(int i=1, c = 0; i<MAX; i++, c = 0)
		{
			int num = i;
			while(num != 0)
			{
				if(num % 10 == 0)
					++c;
				num /= 10;
			}
			dp[i] = c + dp[i-1];
		}
		
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int res = 0;
			if(n<0)
			{
				n = 0;
				res = 1;
			}
			res += dp[m] - dp[n];
			sb.append(res).append('\n');
		}
		System.out.print(sb);
	}
}