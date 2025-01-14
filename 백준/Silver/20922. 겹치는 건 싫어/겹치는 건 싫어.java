//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20922
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 수열개수(1<=이십만)
		int K		= Integer.parseInt(st.nextToken());	// 기준값(1<=백)
		
		if(N==K)
		{
			System.out.print(N);
			return;
		}
		
		int arr[]	= new int[N];						// 1<=십만
		int dp[]	= new int[100_001];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int s = 0;
		int e = 0;
		int M = 1;
		while(e < N)
		{
			int cnt = dp[arr[e]] + 1;
			if(K < cnt)
			{
				// arr[s]를 arr[e]와 같은 값일 때 까지 앞으로 땡긴다.
				while(arr[s] != arr[e])
					dp[arr[s++]]--;
				
				dp[arr[s++]]--;
			}
			else
			{
				M = Math.max(M, e - s + 1);
				dp[arr[e++]] = cnt;
			}
		}
		System.out.print(M);
	}
}