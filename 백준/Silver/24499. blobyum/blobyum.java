//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24499
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 배열 개수
		int K		= Integer.parseInt(st.nextToken());	// 연속된 수
		int len		= N + K;
		int arr[]	= new int[(N+1)<<2];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1,j=N+1; i<=N; i++,j++)
			arr[i] = arr[j] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<len; i++)
			arr[i] += arr[i-1];
		
		int max = arr[K];
		for(int i=K+1,j=1; i<len; i++,j++)
			max = Math.max(max, arr[i] - arr[j]);
		
		System.out.print(max);
	}
}