//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2435
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 전체 날짜(2<=100)
		int K		= Integer.parseInt(st.nextToken());	// 합을 구하기 위한 연속적 날자 수(1<=N)
		int arr[]	= new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken()) + arr[i-1];
		
		int max = ~(1<<30);
		for(int i=K; i<=N; i++)
			if(max < arr[i] - arr[i - K])
				max = arr[i] - arr[i - K];
		System.out.print(max);
	}
}