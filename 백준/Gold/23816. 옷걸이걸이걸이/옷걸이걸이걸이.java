//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/23816
//0.5초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 옷장개수(1<=오천)
		int M = Integer.parseInt(st.nextToken());// 걸이수(1<=만)
		int cnt[]	= {1,3,7,15};//웃걸이수
		int value[]	= {1,2,4,0};//가치
		// 옷장 : 옷걸이 사용시 최대 가치를 저장하는 dp
		int dp[][] = new int[N + 1][M + 1];
		// 초기 값을 유효한 숫자로 바꿔야 하기 때문에 1로 설정, 추후 정답 구할 때 -1로 보정처리
		dp[0][0] = 1;
		
		for(int i=0; i<N; i++)		// 옷장 반복
		{
			for(int j=0; j<M; j++)	// 옷걸이 반복
			{
				if(dp[i][j] == 0)	// 유효하지 않은 숫자면 스킵
					continue;
				// 가능한 경우 모두 탐색
				for(int k=0; k<4; k++)
				{
					int nextJ = j + cnt[k];	// 다음 옷걸이
					if(nextJ > M)// 다음 옷걸이가 최대 옷걸이보다 많으면 스킵
						continue;
					// 현재를 통해 다음을 구하는 방식으로 식을 구함
					dp[i + 1][nextJ] = 
							Math.max(dp[i + 1][nextJ], dp[i][j] + value[k]);
				}
			}
		}
		
		int ans = -1;
		for(int i=1; i<=N; i++)
			if(dp[i][M] != 0)
				ans = Math.max(ans, dp[i][M] - 1);
		
		System.out.print(ans);
	}
}