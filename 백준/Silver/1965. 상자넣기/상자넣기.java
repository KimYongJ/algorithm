//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1965
//2초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());// 1<=천
		int arr[]	= new int[N + 1];
		int dp[]	= new int[N + 1];
		int result	= 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++)
		{
			dp[i] = 1;
			for(int j=0; j<i; j++)
			{
				if(arr[i] > arr[j])
					dp[i] = Math.max(dp[i], dp[j] + 1); 
			}
			result = Math.max(result, dp[i]);
		}
		System.out.print(result);
	}
}
