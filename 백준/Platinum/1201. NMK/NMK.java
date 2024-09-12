//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1201
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder 	sb = new StringBuilder();
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 1~N까지 범위(1<=500)
		int M = Integer.parseInt(st.nextToken()); // 가장 긴 증가하는 부분수열
		int K = Integer.parseInt(st.nextToken()); // 가장 긴 감소하는 부분수열
		
		if(N < M+K-1 || M*K <N)
		{
			System.out.print(-1);
			return;
		} 
		for(int i=K; i>=1; i--)
		{
			sb.append(i).append(' ');
			N--;
		}
		
		M--; // 남은 그룹 수
		
		if(M > 0)
		{
			int arr[]	= new int[M];
			int num[]	= new int[M];
			int remain	= N / M;
			int mod		= N % M;
			for(int i=0; i<M; i++)
			{
				arr[i] = remain;
				if(mod > 0)
				{
					mod--;
					arr[i]++;
				}
				num[i] = K += arr[i];
			}
			for(int i=0; i<M; i++)
				while(arr[i]-->0)
					sb.append(num[i]--).append(' ');
		}
		
		System.out.print(sb.toString());
	}
}