//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/5909
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 건초 더미 개수 1<=20
		int arr[]	= new int[N + 1];
		int sum		= 0;
		
		for(int i=1; i<=N; i++)
			sum += arr[i] = Integer.parseInt(br.readLine());	// 건초 더미 크기 1<=100
		// DP 배열: dp[i][j] = 일부 건초 더미를 그룹1과 그룹2로 나누어, 그룹1의 합이 i, 그룹2의 합이 j가 가능한지 여부
		boolean dp[][] = new boolean[sum + 1][sum + 1];
		
		dp[0][0] = true;// 아무것도 선택하지 않으면 그룹 합은 (0,0)
		// 건초더미 반복
		for(int now : arr)
		{
			for(int i=sum; i>=0; i--)				// 첫 번째 그룹, 중복 선택 방지를 위해 역순 탐색
			{
				for(int j=sum-i; j>=0; j--)			// 두 번째 그룹,	중복 선택 방지를 위해 역순 탐색
				{
					if(i>=now)
						dp[i][j] |= dp[i-now][j];	// i-now , j값이 참이면 i,j도 참임
					
					if(j>=now)
						dp[i][j] |= dp[i][j-now];	// i, j-now값이 참이면 i,j도 참임
				}
			}
		}
		
		int ans = sum + 1;
		
		for(int i=0; i<=sum / 2; i++)	// 그룹 1에 해당하는 값, 건초 그룹 3개중 두번째로 크다.
		{
			for(int j=0; j<=i; j++)		// 그룹 2에 해당하는 값, 건초 그룹 3개중 세번째로 크다.
			{
				int group3 = sum - i - j;// 그룹 3에 해당하는 값, 건초 그룹 3개중 가장 크다.
				// i와 j로 만들 수 있고, 그룹 3이 가장 클 때 결과 갱신
				if(dp[i][j] && group3>=i)
					ans = Math.min(ans, group3);
			}
		}
		
		System.out.print(ans);
	}
}
//8		// 건초더미 개수 1<=20
//14	// 건초더미 크기 1<=100
//2
//5
//15
//8
//9
//20
//4
////26
