// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int man[] = new int[M];
		int woman[] = new int[W];
		int dp[][] = new int[M+1][W+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) 
			man[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) 
			woman[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(man);
		Arrays.sort(woman);
		
		for(int i=1; i<=M; i++) {
			for(int j=1; j<=W; j++) {				
				dp[i][j] = dp[i-1][j-1] + Math.abs(man[i-1] - woman[j-1]);
				if(i > j) {
					dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
				}
				else if(i < j) {
					dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.print(dp[M][W]);
	}
}