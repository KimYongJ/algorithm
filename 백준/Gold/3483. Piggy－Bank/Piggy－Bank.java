//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3483
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int diff	= Integer.parseInt(st.nextToken());
			int weight	= Integer.parseInt(st.nextToken()) - diff;
			int N		= Integer.parseInt(br.readLine());
			int dp[]	= new int[10_001];
			
			Arrays.fill(dp, 1<<30);
			
			dp[0] = 0;
			for(int i=1; i<=N; i++)
			{
				st = new StringTokenizer(br.readLine());
				int m = Integer.parseInt(st.nextToken());// 금액(1<=50,000)
				int w = Integer.parseInt(st.nextToken());// 해당금액의 무게(1<=10,000)
				
				for(int j=w; j<=10_000; j++)
					dp[j] = Math.min(dp[j], dp[j - w] + m);
			}
			
			if(dp[weight] == 1<<30)
				sb.append("This is impossible.").append('\n');
			else
				sb.append("The minimum amount of money in the piggy-bank is ")
					.append(dp[weight]).append(".\n");
		}
		System.out.print(sb);
	}
}