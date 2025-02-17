//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27114
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		final int MAX = 1_000_001;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int l		= Integer.parseInt(st.nextToken());// 좌1<=백만
		int r		= Integer.parseInt(st.nextToken());// 우1<=백만
		int t		= Integer.parseInt(st.nextToken());// 턴1<=백만
		int k		= Integer.parseInt(st.nextToken());// 총에너지1<=백만
		int energy[]= {0,l+l+l+l, r+r+r+r, r+l, l+l+t, r+r+t, t+t};// 좌4번, 우4번, 좌우(우좌), 좌좌뒤, 우우뒤, 뒤뒤
		int cnt[]	= {0,4,4,2,3,3,2};
		int dp[]	= new int[k + 1];
		
		Arrays.fill(dp, MAX);
		
		dp[0] = 0;
		
		for(int i=1; i<7; i++)
			for(int j=energy[i]; j<=k; j++)
				dp[j] = Math.min(dp[j], dp[j-energy[i]] + cnt[i]);
		
		System.out.print(dp[k] == MAX ? -1 : dp[k]);
	}
}
