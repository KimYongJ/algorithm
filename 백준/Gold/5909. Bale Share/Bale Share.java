
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 건초 더미 개수 1<=20
		int arr[]	= new int[N + 1];
		int sum		= 0;
		
		for(int i=0; i<N; i++)
			sum += arr[i] = Integer.parseInt(br.readLine());
		
		boolean dp[][] = new boolean[sum + 1][sum + 1];
		
		dp[0][0] = true;
		
		for(int now : arr)
		{
			for(int i=sum; i>=0; i--)
			{
				for(int j=sum - i; j>=0; j--)
				{
					if(i>=now)
						dp[i][j] |= dp[i-now][j];
					if(j>=now)
						dp[i][j] |= dp[i][j-now];
				}
			}
		}
		
		int ans = sum + 1;
		
		for(int i=0; i<=sum; i++)
		{
			for(int j=0; j<=i; j++)
			{
				int group3 = sum - i - j;
				
				if(dp[i][j] && group3 >= i)
					ans = Math.min(ans, group3);
			}
		}
		System.out.print(ans);
	}
}