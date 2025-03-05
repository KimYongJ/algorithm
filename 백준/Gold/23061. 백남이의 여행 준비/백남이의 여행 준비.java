//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/23061
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 물품수N(1<=100)
		int M		= Integer.parseInt(st.nextToken());// 가방 수 M(1<=100)
		int W[]		= new int[N + 1];
		int V[]		= new int[N + 1];
		int bag[]	= new int[M + 1];
		int maxWeight = 0;
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());// N줄에 걸쳐 각 물건의 무게(1<=십만)
			V[i] = Integer.parseInt(st.nextToken());// N줄에 걸쳐 각 물건의 가치(0<=1000)
		}
		// M개의 줄에 걸쳐 가방의 최대무게(1<=백만)
		for(int m=1; m<=M; m++)
			maxWeight = Math.max(maxWeight, bag[m] = Integer.parseInt(br.readLine()));
		
		int dp[] = new int[maxWeight + 1];
		
		for(int i=1; i<=N; i++)	// 최대 100번
			for(int j=maxWeight; j>=W[i]; j--)// 최대 백만
				dp[j] = Math.max(dp[j], dp[j-W[i]] + V[i]);

		int res = 0;
		double rate = -1;
		for(int i=1; i<=M; i++)// 최대백번
		{
			double r = (double)dp[bag[i]] / bag[i];
			if(rate < r)
			{
				rate = r;
				res = i;
			}
		}
		System.out.print(res);
	}
}