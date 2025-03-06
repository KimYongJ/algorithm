//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/23257
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N	= Integer.parseInt(st.nextToken());// 월봉 개수 N(1<=100)
		int M	= Integer.parseInt(st.nextToken());// 선택할 개수 M(1<=N)
		int arr[]= new int[N];
		boolean dp[][] = new boolean[M+1][1025];	// M개를 고르는것, 숫자 1024까지
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			arr[i] = Math.abs(Integer.parseInt(st.nextToken()));
			dp[1][arr[i]] = true; // 1개를 골랐을 때 arr[i]는 가능하므로 체크
		}
		// 시간 복잡도 : 100 * 100 * 1024 = m*n*1024
		for(int m=1; m<M; m++)			// 1개부터 M-1개까지 고르는 것을 반복
			for(int i=0; i<=1024; i++)	// 0부터 1024까지 가능한지 체크
				if(dp[m][i])			// m개를 골랐을 때 i숫자일때 가능하다면, 대입할 모든숫자(arr)을 다대입해서 true로만든다.
					for(int a : arr)
						dp[m + 1][i ^ a] = true;

		for(int i=1024; i>=0; i--)
			if(dp[M][i])
			{
				System.out.print(i);
				return;
			}
	}
}