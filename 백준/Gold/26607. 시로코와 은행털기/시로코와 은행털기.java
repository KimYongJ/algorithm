//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/26607
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//사람수(1<=80)
		int K = Integer.parseInt(st.nextToken());//뽑을 인원(1<=N)
		int T = Integer.parseInt(st.nextToken());//힘+스피드합(1<=200)
		int L = K * T;// 한쪽에서 나올 수 있는 최대 합의 max
		int S[] = new int[N];
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean dp[][] = new boolean[K + 1][L + 1];
		
		dp[0][0] = true;
		
		for(int s : S)
			for(int k=K-1; k>=0; k--)
				for(int l=L; l>=s; l--)
					// k명을 선택한 상태에서, l-s를 만들 수 있다면,
		            // 현재 사람 s를 추가하여 k+1명을 선택한 상태에서 l을 만들 수 있다.
					dp[k+1][l] |= dp[k][l-s];

		
		int result = 0;
		for(int l=0; l<=L; l++)
		{
			if(dp[K][l])
			{
				int A = l;
				int B = L - A;
				result = Math.max(result, A*B);
			}
		}
		System.out.print(result);
	}
}