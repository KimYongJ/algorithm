//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/17528
//0.5초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Node{
	int a, b;
	Node(int a, int b){
		this.a = a;
		this.b = b;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());// 작업개수(1<=250)
		int	sumA	= 0;
		Node node[] = new Node[N];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());// A머신이 작업을 완료하는데 걸리는 시간 1<=250
			int b = Integer.parseInt(st.nextToken());// A머신이 작업을 완료하는데 걸리는 시간 1<=250
			sumA += a;// a를 모두 선택했을 때를 기준으로 값을 구할 것이기 때문에 a를 모두 더한다.
			
			node[i] = new Node(a,b);
		}
		// dp의 인덱스를 j라 명명한다.
		// dp의 인덱스(j)의 의미 : A머신의 총 작업시간
		// dp[j] 안에 담긴 값의 의미 : A머신의 작업시간이 j일때, B머신의 총 작업시간의 최소
		int dp[] = new int[sumA + 1];

		Arrays.fill(dp, 1<<30);
		
		dp[0] = 0;

		for(Node now : node)
		{
			for(int j = sumA; j>= now.a; j--)
			{
				// dp[j]에서 j는 A머신의 작업시간의 합을 의미, dp[j]안에 담긴 값은, B머신의 총합시간 최소
				// dp[j]값의 의미가 B머신의 작업시간 총 합의 최소이기 때문에 아래 dp점화식을 다음과 같이 치환해 설명 가능하다 
				// 현재 B머신의 총합시간 = min(현재 B머신의 총합시간, 이전의 B머신의 총합시간 + 현재 B머신 선택시 걸리는시간)
				// 즉, A의 작업 시간의 총합을 통해 B의 작업시간의 총합의 최솟값을 구해나간다.
				dp[j] = Math.min(dp[j], dp[j - now.a] + now.b);
			}
		}

		int ans = 1<<30;
		
		for(int i=0; i<=sumA; i++)
		{
			// A머신이 i만큼 작업을 가져가면, B를 구하기 위해서는 A가 가져간 작업량을 뺀 작업량을 통해 B작업량을 구해야한다.
			// 그러므로 dp[sum - i]를 해야 B의 작업량이 나온다.
			ans = Math.min(ans, Math.max(i, dp[sumA - i]));
		}
		
		System.out.print(ans);
	}
}
//3		// 작업개수(1<=250)
//2 3	// 각A,B머신이 작업을 완료하는데 걸리는 시간 1<=250
//5 3
//2 7
////4