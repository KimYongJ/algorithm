//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6144
//1초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 팔찌개수1<=3402
		int M		= Integer.parseInt(st.nextToken());// 착용가능최대무게 1<=12880
		int W[]		= new int[N + 1];
		int V[]		= new int[N + 1];
		int dp[][]	= new int[N + 1][M + 1];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());// 무게
			V[i] = Integer.parseInt(st.nextToken());// 매력값
		}
		
		for(int i=1; i<=N; i++) {
			for(int m=1; m<=M; m++) {
				dp[i][m] = dp[i-1][m];
				if(0<=m-W[i])
					dp[i][m] = Math.max(dp[i][m], dp[i-1][m-W[i]] + V[i]);
			}
		}
		System.out.print(dp[N][M]);
	}
}
