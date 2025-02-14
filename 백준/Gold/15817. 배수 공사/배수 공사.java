//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15817
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 파이프 종류 1<=100
		int X = Integer.parseInt(st.nextToken());// 파이프 길이 1<=만
		int len[] = new int[N + 1];// 파이프 길이, 길이가 짧은 순서대로 입력됨
		int cnt[] = new int[N + 1];// 파이프 수량
		int dp[][] = new int[N + 1][X + 1];
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			len[i] = Integer.parseInt(st.nextToken());// 길이가 짧은 순으로 입력됨 0<=X
			cnt[i] = Integer.parseInt(st.nextToken());// 파이프 수량 0<=100
			
			int maxLen = Math.min(X,cnt[i] * len[i]);
			int l = len[i];
			while(l <= maxLen)
			{
				dp[i][l]++;
				l += len[i];
			}
		}
		for(int i=1; i<=N; i++)
		{
			for(int x=1; x<=X; x++)
			{
				dp[i][x] += dp[i-1][x];
				
				for(int j=x - len[i], c = cnt[i];j>=0 && c>0;c--,j-=len[i])
					dp[i][x] += dp[i-1][j];
			}
		}
		System.out.print(dp[N][X]);
	}
}