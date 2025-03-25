//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25048
//1.5초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Switch implements Comparable<Switch>{
	int port, cost;
	Switch(int p, int c){
		port = p;
		cost = c;
	}
	@Override
	public int compareTo(Switch o) {
		if(cost != o.cost)
			return cost - o.cost;
		return port - o.port;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final long MAX = 1L<<59;
		int N		= Integer.parseInt(br.readLine());	// 스위치 개수(1<=300)
		Switch sch[]= new Switch[N];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int port	= Integer.parseInt(st.nextToken());// 포트개수(2<=십만)
			int cost	= Integer.parseInt(st.nextToken());// 연결 비용(0<=십억)
			sch[i]		= new Switch(port, cost);
		}

		int M = Integer.parseInt(br.readLine());	// 연결할 컴퓨터 개수(1<=십만)

		//Arrays.sort(sch);
		// 스위치 당 컴퓨터 연결시 드는 최소비용을 저장할 dp
		long dp[][] = new long[N + 1][M + 1];
		
		for(int i=0; i<=N; i++)
			Arrays.fill(dp[i], MAX);
		
		dp[0][1] = 0;
		
		for(int i=1; i<=N; i++)
		{
			Switch now = sch[i - 1];
			for(int j=1; j<=M; j++)
			{
				dp[i][j] = dp[i-1][j];
				
				int prvPort = j - now.port + 2;
				
				if(0<=prvPort && prvPort <= M)
					dp[i][j] = Math.min(dp[i][j], dp[i-1][prvPort] + now.cost);
			}
		}
		System.out.print(dp[N][M] == MAX ? -1 : dp[N][M]);
	}
}