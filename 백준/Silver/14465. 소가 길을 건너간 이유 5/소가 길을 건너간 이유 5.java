//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14465
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 횡단보도(1<=십만)
		int K		= Integer.parseInt(st.nextToken());	// 연속한 신호등개수
		int B		= Integer.parseInt(st.nextToken());	// 고장난 신호등 개수
		int arr[]	= new int[N+1];
		int ksum	= 0;
		
		
		for(int i=1; i<=B; i++)
			arr[Integer.parseInt(br.readLine())] = 1;
		
		for(int i=1; i<=K; i++)
			ksum += arr[i];
		
		int min = ksum;
		for(int i=K+1,j=1; i<=N; i++)
		{
			ksum += arr[i] - arr[j++];
			if(ksum < min)
				min = ksum;
		}
		
		System.out.print(min);
	}
}