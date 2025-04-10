//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/10982
//5초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Node{
	int a, b;
	Node(int a, int b){
		this.a=a;
		this.b=b;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int N		= Integer.parseInt(br.readLine());	//반죽의개수(1<=1,000)
			Node node[] = new Node[N];
			int sumA	= 0;
			
			for(int i=0; i<N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());// 오븐 1에서 구워지는 걸리는 시간(1<=100)
				int b = Integer.parseInt(st.nextToken());// 오븐 2에서 구워지는 걸리는 시간(1<=100)
				node[i] = new Node(a,b);
				sumA += a;
			}

			// dp의 인덱스의 의미 : 오븐 1에서 구울 때의 시간의 합
			// dp[x]안에 담긴 값의 의미 : x시간 동안 오븐 2에서 구울 때의 시간의 합 중 최소
			int dp[] = new int[sumA + 1];
			
			Arrays.fill(dp, 1<<30);
			
			dp[0] = 0;
			
			for(Node now : node)
			{
				// 오븐 1에서 구워지는 총시간을 역순으로 내려가면서 탐색
				for(int j=sumA; j>=now.a; j--)
				{
					// dp[j]안에는 j시간동안 오븐 2에서 구울 때의 시간의 합 중 최소 값이 들어가야 한다.
					dp[j] = Math.min(dp[j], dp[j-now.a] + now.b);
				}
			}
			int ans = 1<<30;
			
			for(int j=0; j<=sumA; j++)
				// 오븐 1의 시간의 총합이 j일 때, 오븐 2의 시간의 총합은 sumA - j 인덱스 안에 담긴 값이다.
				ans = Math.min(ans, Math.max(j, dp[sumA - j]));
			
			sb.append(ans).append('\n');
		}
		System.out.print(sb);
	}
}