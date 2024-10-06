//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1994
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N = read();
		if(N == 1)
		{
			System.out.print(1);
			return;
		}
		
		int max		= 1;
		int arr[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();

		Arrays.sort(arr);
		
		// dp[i][j]는  arr[i]와 arr[j]를 사용해 만들 수 있는 등차수열의 최대 길이를 저장함
		int dp[][] = new int[N+1][N+1];
		
		// 이중 for문으로 모든 숫자쌍(i,j)(i < j)에 대해 검사한다.
		for(int i=1; i<N; i++)
			for(int j=i+1; j<=N; j++)
			{
				// 두개를 확인하는 것이기 때문에 어떤 등차수열도 2는 최소이기 때문에 기본값
				dp[i][j]	= 2;
				// arr[i]의 이전 값을 구함 ex) 두 숫자 4, 6이 있을 때 4와 6이전 값은 2이다. 이렇게 2를 찾는것
				int before	= (arr[i]<<1) - arr[j];

				int s		= 0;
				int e		= i - 1;
				// 기본 값은 0으로하여 추후 before와 같은 숫자를 못찾은 경우 dp[0][i]가 되어 무조건0이 되도록한다.i는 1부터시작함으로 무조건 dp[i][?]는 0임
				int k		= 0;

				while(s <= e)
				{
					int mid = (s + e) >> 1;
				// before와 같을 때만 mid를 k에 대입, before와 같은 가장 큰 인덱스를 k에 대입하도록 s를 높여줌
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