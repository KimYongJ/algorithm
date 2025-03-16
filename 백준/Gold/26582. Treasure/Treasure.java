//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/26582
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N		= Integer.parseInt(st.nextToken());
			int W		= Integer.parseInt(st.nextToken());
			int dp[]	= new int[W + 1];
			
			for(int i=1; i<=N; i++)
			{
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				for(int j=W; j>=w; j--)
					dp[j] = Math.max(dp[j], dp[j-w] + v);
			}
			
			sb.append(dp[W]).append('\n');
		}
		System.out.print(sb);
	}
}