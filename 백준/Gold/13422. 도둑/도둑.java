//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13422
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N		= Integer.parseInt(st.nextToken());	// 집개수(1<=십만)
			int M		= Integer.parseInt(st.nextToken());	// 연속된집개수(1<=N)
			int K		= Integer.parseInt(st.nextToken());	// 가능한 최대 돈의양(1<=10억)
			int len		= N + M;
			int arr[]	= new int[len];
			int total	= 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++)
				total += arr[i] = Integer.parseInt(st.nextToken());
			for(int i=N+1; i<len; i++)
				arr[i] = arr[i-N];
			
			for(int i=1; i<len; i++)
				arr[i] += arr[i-1];
			
			if(N == M)
			{
				sb.append(total < K ? 1 : 0).append('\n');
				continue;
			}
			
			int cnt = 0;
			int r = M-1;
			while(++r < len)
				if(arr[r] - arr[r-M] < K)
					++cnt;

			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
}