//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14628
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 스킬 개수 N(1<=100)
		int M		= Integer.parseInt(st.nextToken());// 적의 체력M(1<=100)
		int K		= Integer.parseInt(st.nextToken());// 같은 스킬 사용시 추가되는 마나 포인트 K(1<=100)
		int[] mana	= new int[N];
		int[] damage= new int[N];
		int[] dp	= new int[M+1];
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			mana[i]		= Integer.parseInt(st.nextToken());// 스킬에 필요한 마나 포인트 X(1<=100)
			damage[i]	= Integer.parseInt(st.nextToken());// 상대 체력을 깎는 수치 Y(1<=100)
		}
		
		Arrays.fill(dp,1<<30);
		
		dp[0] = 0;
		
		for(int i=0; i<N; i++)
		{
			int dam = damage[i];
			for(int j=1; j<=102; j++)
			{
				int man = mana[i] + (j-1)*K;
				for(int x=M; x>=0; x--)
					if(x>=dam)
						dp[x] = Math.min(dp[x], dp[x-dam] + man);
			}
		}
		
		System.out.print(dp[M]);
	}
}