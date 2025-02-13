//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2073
//2초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());//목표 거리(7<=십만)
		int P = Integer.parseInt(st.nextToken());//파이프개수(1<=350)
		int dp[] = new int[D + 1];
		int l[] = new int[P + 1];
		int c[] = new int[P + 1];
		
		for(int i=1; i<=P; i++) {
			st = new StringTokenizer(br.readLine());
			l[i] = Integer.parseInt(st.nextToken());
			c[i] = Integer.parseInt(st.nextToken());
		}
		dp[0] = 1<<30;
		
		for(int i=1; i<=P; i++)
			for(int j=D; j>=l[i]; j --)
				dp[j] = Math.max(dp[j], Math.min(dp[j-l[i]], c[i]));
		
		System.out.print(dp[D]);
	}
}