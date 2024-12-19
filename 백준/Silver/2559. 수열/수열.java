//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2559
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 2<=십만
		int K		= Integer.parseInt(st.nextToken());	// 일자
		int arr[]	= new int[N];
		int sum		= 0;
		int max		= 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<K; i++)
			sum += arr[i];
		
		max = sum;
		
		for(int i=0,j=K; j<N; i++,j++)
		{
			sum += arr[j] - arr[i];
			max = Math.max(sum, max);
		}
		System.out.print(max);
	}
}
