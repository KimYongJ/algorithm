//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/22839
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int dp[] = new int[301];
		dp[0] = 1;
		for(int i=1; i<=17; i++)
		{
			int n = i * i;
			for(int j=n; j<=300; j++)
				dp[j] += dp[j - n];
		}
		
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			sb.append(dp[n]).append('\n');
		}
		System.out.print(sb);
	}
}