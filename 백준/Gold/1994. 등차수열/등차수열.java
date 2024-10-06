//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1994
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N;
		if((N=Integer.parseInt(br.readLine())) == 1)
		{
			System.out.print(1);
			return;
		}
		
		int max		= 1;
		int arr[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		Arrays.sort(arr);
		
		int dp[][] = new int[N+1][N+1];
		
		for(int i=1; i<N; i++)
			for(int j=i+1; j<=N; j++)
			{
				dp[i][j]	= 2;
				int next	= (arr[i]<<1) - arr[j];
				int k		= i;
				
				while(--k >=1)
					if(arr[k] == next)
						break;
				
				dp[i][j] = Math.max(dp[i][j],  dp[k][i] + 1);
				
				if(max < dp[i][j])
					max = dp[i][j];
				
			}

		System.out.print(max);
	}
}