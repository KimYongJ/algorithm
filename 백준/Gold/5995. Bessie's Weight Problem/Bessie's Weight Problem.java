//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5995
//1초/128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H		= Integer.parseInt(st.nextToken());
		int N		= Integer.parseInt(st.nextToken());
		int dp[]	= new int[H + 1];
		
		dp[0] = 1;
		
		for(int i=1; i<=N; i++)
		{
			int w = Integer.parseInt(br.readLine());
			
			for(int j=H; j>=w; j--)
				dp[j] += dp[j-w];
		}
		
		for(int i=H; i>=0; i--)
		{
			if(dp[i] != 0)
			{
				System.out.print(i);
				return;
			}
		}
	}
}