//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4384
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 총인원 수 1<=100
		int W[]		= new int[N + 1];					// 몸무게 자연수 1<=450
		int half	= N / 2;
		int total	= 0;
		
		for(int i=1; i<=N; i++)
			total += W[i] = Integer.parseInt(br.readLine());
		
		boolean dp[][] = new boolean[N + 1][total + 1];// N명을 사용해서 정확히 total무게가 가능하냐
		
		dp[0][0] = true;

		for(int i=1; i<=N; i++)						// 인원 반복
			for(int j=half; j>=1; j--)				// N/2명을 골라서
				for(int t=total; t>=W[i]; t--)		// j-1명일때 t-W[i]를 만들 수 있다면 dp[j][t]를 만들 수 있으므로 
					dp[j][t] |= dp[j - 1][t-W[i]];
		
		int diff = 1<<30;
		int res1 = 0;
		int res2 = 0;
		
		for(int i=0; i<=total; i++)
			if(dp[half][i] && Math.abs(total - 2*i) < diff)
			{
				diff = Math.abs(total - 2*i);
				res1 = i;
			}

		res2 = total - res1;
		
		StringBuilder sb = new StringBuilder();
		
		if(res1 < res2)
			sb.append(res1).append(' ').append(res2);
		else
			sb.append(res2).append(' ').append(res1);
		
		System.out.print(sb);
	}
}