//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2616
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 객차수 3?<=오만
		int psum[]	= new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			psum[i] += Integer.parseInt(st.nextToken()) + psum[i-1];// 손님의수 0<=백
		
		int max = Integer.parseInt(br.readLine());	// 최대로 끌 수 있는 객차 수
		
		int dp[][] = new int[4][N+1];
		
		for(int i=1; i<=3; i++)
			for(int j=i*max; j<=N; j++)
				dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-max] + psum[j] - psum[j-max]);
		
		System.out.print(dp[3][N]);
	}
}