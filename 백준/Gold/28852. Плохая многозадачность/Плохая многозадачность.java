//https://www.acmicpc.net/problem/28852
//2초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		double B = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		long sum = (long)Math.ceil(Integer.parseInt(st.nextToken()) / B);

		int limit = (int)((sum - 1) * B);
		
		for(int i=1; i<N; i++)
		{
			int now = Math.min(Integer.parseInt(st.nextToken()), limit);

			sum += ((int)Math.ceil(now/B));
		}
		
		System.out.print(sum);
	}
}