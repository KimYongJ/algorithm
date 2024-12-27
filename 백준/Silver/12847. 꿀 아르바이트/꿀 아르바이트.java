//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12847
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 근무 가능한 일(1<=십만)
		int M		= Integer.parseInt(st.nextToken());	// 일할날(0<=n)
		int arr[]	= new int[N];
		long sum	= 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++)
			sum += arr[i];
		
		long max = sum;
		
		for(int i=M,j = 0; i<N; i++)
		{
			sum += arr[i] - arr[j++];
			if(max < sum)
				max = sum;
		}
		System.out.print(max);
	}
}