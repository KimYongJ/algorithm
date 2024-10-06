//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1994
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N == 1)
		{
			System.out.print(1);
			return;
		}
		
		int max		= 1;
		int arr[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		Arrays.sort(arr);
		
		// dp[i][j]는  arr[i]와 arr[j]를 사용해 만들 수 있는 등차수열의 최대 길이를 저장함
		int dp[][] = new int[N+1][N+1];
		
		// 이중 for문으로 모든 숫자쌍(i,j)(i < j)에 대해 검사한다.
		for(int i=1; i<N; i++)
			for(int j=i+1; j<=N; j++)
			{
				dp[i][j]	= 2;					// 두개를 확인하는 것이기 때문에 어떤 등차수열도 2는 최소이기 때문에 기본값
				int before	= (arr[i]<<1) - arr[j];	// arr[i]의 이전 값을 구함 ex) 두 숫자 4, 6이 있을 때 4와 6이전 값은 2이다. 이렇게 2를 찾는것

				int s		= 0;
				int e		= i - 1;
				int k		= 0;

				while(s <= e)
				{
					int mid = (s + e) >> 1;
					if(arr[mid] == before)
					{
						k = mid;
						s = mid + 1;
					}
					else if(arr[mid] < before)
						s = mid + 1;
					else
						e = mid - 1;
				}

				// 위에서 구한 arr[k]와 arr[i]의 등차수열의 최대길이에 + 1을 해서,, 기존 arr[i]와 arr[j]를 통해 구한 등차수열의 최대길 중 큰것을 대입한다.
				dp[i][j] = Math.max(dp[i][j],  dp[k][i] + 1);

				if(max < dp[i][j])
					max = dp[i][j];
			}

		System.out.print(max);
	}
}