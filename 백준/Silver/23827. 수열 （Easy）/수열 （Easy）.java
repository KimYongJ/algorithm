//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/23827

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long arr[]	= new long[N+1];
		long psum[] = new long[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			psum[i] += psum[i-1] + (arr[i] = Integer.parseInt(st.nextToken()));
		
		long res = 0;
		for(int i=1; i<N; i++) {
			res += (psum[N] - psum[i]) * arr[i];
			res = res % 1_000_000_007;
		}
		System.out.print(res);
	}
}