// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int dp[][] = new int[3][N];
			int pass1[] = new int[N];
			int pass2[] = new int[N];
			int dribble1[] = new int[N];
			int dribble2[] = new int[N];
			dp[1][0] = Integer.parseInt(st.nextToken());
			dp[2][0] = Integer.parseInt(st.nextToken());
			int g1 = Integer.parseInt(st.nextToken());
			int g2 = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N - 1; i++) {
				pass1[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N - 1; i++) {
				dribble1[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N - 1; i++) {
				pass2[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N - 1 ; i++) {
				dribble2[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1; i<N; i++) {
				dp[1][i] = Math.min(dp[2][i-1] + pass2[i-1] , dp[1][i-1] + dribble1[i-1]);
				dp[2][i] = Math.min(dp[1][i-1] + pass1[i-1] , dp[2][i-1] + dribble2[i-1]);
			}
			sb.append(Math.min(dp[1][N-1] + g1, dp[2][N-1] + g2)).append('\n');
		}
		System.out.print(sb.toString());
	}
}