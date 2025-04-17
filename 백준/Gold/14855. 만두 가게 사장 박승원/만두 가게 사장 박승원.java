//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14855
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());		// 밀가루그램N(1<=1,000)
		int M		= Integer.parseInt(st.nextToken())+2;	// 만두종류M(1<=10), 연산 편의를 위해 +2 진행
		int G		= Integer.parseInt(st.nextToken());		// 만두 속 없는 만두를 만드는데 드는 밀가루 그램(<=100)
		int R		= Integer.parseInt(st.nextToken());		// 만두속없는 만두 판매 비(<=100)
		int dp[][]	= new int[M][N + 1];// 밀가루 그램에 따른 비용
		Node[] node = new Node[M];
		
		node[1] = new Node(N/G, G, R);
		
		for(int i=2; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			// (모두 1<=100)
			int a = Integer.parseInt(st.nextToken());// 만두 속 총량
			int b = Integer.parseInt(st.nextToken());// 만두 하나에 필요한 만두속
			int c = Integer.parseInt(st.nextToken());// 해당 만두에 들어가는 밀가루양
			int d = Integer.parseInt(st.nextToken());// 해당 만두 판매비용
			// 만들 수 있는 만두 개수, 드는 밀가루양, 얻을 수 있는 판매 이익
			node[i] = new Node(a/b, c, d);
		}

		for(int m=1; m<M; m++)
		{
			for(int j=0; j<=N; j++)		// 새로운 행일 때 이전 값을 복사 
				dp[m][j] = dp[m - 1][j];
			
			for(int i=0; i<=N; i++)		// 밀가루 양을 돌면서 만들 수 있는 개수에 따른 최대 값 갱신
			{
				for(int c=1; c<=node[m].cnt; c++)// 만들 수 있는 개수
				{
					int nextGaru = i + node[m].garu * c;// 개수에 따라 산출되는 가루 양
					int cost = node[m].cost * c;	// 개수에 따라 얻을 수 있는 비용
					// 가루 양이 최대 값보다 클 때 스킵
					if(nextGaru > N)
						break;

					dp[m][nextGaru] = Math.max(dp[m][nextGaru], dp[m-1][i] + cost);
				}
			}
		}
		
		int ans = 0;
		// 마지막 행에 큰 값들이 다 들어가있으므로 가장 큰 것을 찾아 출력
		for(int i=0; i<=N; i++)
			ans = Math.max(ans, dp[M - 1][i]);
		
		System.out.print(ans);
	}
}
class Node{
	int cnt, garu, cost;
	Node(int cnt, int garu, int cost){
		this.cnt	= cnt;	// 만들 수 있는 만두 개수
		this.garu	= garu;	// 하나 만들 때 들어가는 밀가루 양
		this.cost	= cost;	// 만두 하나당 얻을 수 있는 비용
	}
}
//10 2 2 1		// 밀가루그램N(1<=1,000), 만두종류M(1<=10), 만두 속 없는 만두를 만드는데 드는 밀가루 그램(<=100), 만두속없는 만두 판매 비(<=100)
//7 3 2 100		// M개 줄에 해당 만두 속(A), 만두 하나에 필요한 만두속(B), 해당 만두에 들어가는 밀가루양(C), 해당 만두 판매비용 D(모두 1<=100)
//12 3 1 10
////만두 금액 합의 최댓값 : 241