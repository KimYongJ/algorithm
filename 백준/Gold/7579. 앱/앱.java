//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7579
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());//실행앱개수(1<=100)
		int M		= Integer.parseInt(st.nextToken());// 확보할 메모리 양(1<=천만)
		int byt[]	= new int[N + 1];
		int cost[]	= new int[N + 1];
		int total	= 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			byt[i] = Integer.parseInt(st.nextToken());// 사용중인 메모리의 바이트 수(1<=천만)
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			total += cost[i] = Integer.parseInt(st.nextToken());// 비활성화시 드는 비용(0<=100)
		
		int dp[] = new int[total + 1];
		
		for(int i=1; i<=N; i++)
			for(int j=total; j>=cost[i]; j--)
				dp[j] = Math.max(dp[j], dp[j-cost[i]] + byt[i]);

		for(int i=0; i<=total; i++)
			if(dp[i] >= M)
			{
				System.out.print(i);
				return;
			}
		
	}
}
