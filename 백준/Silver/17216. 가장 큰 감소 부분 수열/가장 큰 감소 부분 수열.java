//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17216
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	public static void main(String[]args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 1<=천
		int[] arr	= new int[N+1];
		int[] dp	= new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			dp[i] = arr[i] = Integer.parseInt(st.nextToken());	// 1<=천
		
		int max = 0;
		for(int i=1; i<=N; i++)
		{
			for(int j=1; j<i; j++)
			{
				if(arr[j] > arr[i])
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.print(max);
	}
}