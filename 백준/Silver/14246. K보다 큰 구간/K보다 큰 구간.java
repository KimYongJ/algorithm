//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14246
//2초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 자연수 개수(1≤100,000)
		long arr[]	= new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());//1<= 100,000
		
		long K = Integer.parseInt(br.readLine());	//(1<=십억)
		
		int s	= 0;
		int e	= -1;
		long sum= 0;
		
		while(e+1 < N && sum < K)
			sum += arr[++e];
		
		long cnt = 0;
		
		while(e<N)
		{
			if(K < sum)
			{
				cnt += N - e;
				sum -= arr[s++];
			}
			else if(e + 1 == N)
				break;
			else
				sum += arr[++e];
		}
		System.out.print(cnt);
	}
}