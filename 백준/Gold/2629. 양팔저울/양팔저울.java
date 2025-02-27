//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2629
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int MAX	= 40_000;
		int n			= Integer.parseInt(br.readLine());// 추의 개수가 주어짐(1<=30)
		int c[]			= new int[n+1];// 추의 무게들이 자연수로 가벼운 것부터 차례로 주어짐, 같은 무게가 여러개일 수도 있음(1<=500)
		boolean dp[][]	= new boolean[n+1][MAX+1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++)
			c[i] = Integer.parseInt(st.nextToken());
		
		dp[0][0] = true;
		
		for(int i=1; i<=n; i++)
		{
			for(int j=0; j<=MAX; j++)
			{
				dp[i][j] = dp[i-1][j] | dp[i-1][Math.abs(j-c[i])];
				
				if(j+c[i] <= MAX)
					dp[i][j] |= dp[i-1][j+c[i]];
			}
		}
		
		int Q = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while(Q-->0)
		{
			int v = Integer.parseInt(st.nextToken());
			sb.append(dp[n][v] ? 'Y' : 'N').append(' ');
		}
		System.out.print(sb);
	}
}