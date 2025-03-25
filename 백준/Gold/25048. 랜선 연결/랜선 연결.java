//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25048
//1.5초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Switch{
	int port, cost;
	Switch(int p, int c){
		port = p;
		cost = c;
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

		// 스위치 당 컴퓨터 연결시 드는 최소비용을 저장할 dp
		long dp[] = new long[M + 1];
		
		Arrays.fill(dp, MAX);
		
		dp[1] = 0;// 스위치 0개를 사용해 컴퓨터 1개를 사용하는데 드는 비용 0
		
		for(int i=1; i<=N; i++)				// 스위치 반복
		{
			Switch now = sch[i - 1];		// 현재 스위치를 꺼냄
			for(int j=M; j>=0; j--)			// 연결할 컴퓨터 대수
			{
				// 현재 스위치를 사용하는 경우
				// - 현재 스위치를 사용하면 now.port-1개 포트가 추가됨,
				// - j대의 컴퓨터 연결을 위해서는 실제로 j + 1개의 포트가 필요함, 모든 포트 1개는 인터넷에 연결되야 하므로.
				int prvPort = j + 1 - (now.port - 1);
				
				if(0<=prvPort && prvPort <= M)
					dp[j] = Math.min(dp[j], dp[prvPort] + now.cost);
			}
		}
		System.out.print(dp[M] == MAX ? -1 : dp[M]);
	}
}