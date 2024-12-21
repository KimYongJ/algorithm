//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13900

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		long arr[]	= new long[N+1];
		long prev[]	= new long[N+1];
		long res	= 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			prev[i] = (arr[i] = Integer.parseInt(st.nextToken())) + prev[i-1];
		
		for(int i=1; i<N; i++)
			res += arr[i] * (prev[N] - prev[i]);

		System.out.print(res);
	}
}