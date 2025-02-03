//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19621
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[]args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	//회의 수(1<=25)
		int[]dp		= new int[N+1];
		int[]arr	= new int[N+1];

		for(int i=1; i<=N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			Integer.parseInt(st.nextToken());	// 0<=int최대값, 회의시간은 모두 다름
			Integer.parseInt(st.nextToken());	// 0<=int최대값, 회의시간은 모두 다름
			arr[i] = Integer.parseInt(st.nextToken());	// 회의 인원(1<=천)
		}
		
		dp[1] = arr[1];

		for(int i=2; i<=N; i++)
			dp[i] = Math.max(dp[i-1], dp[i-2] + arr[i]);

		System.out.print(dp[N]);
	}
}