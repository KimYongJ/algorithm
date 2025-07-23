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
		int B = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		long sum = (Integer.parseInt(st.nextToken())+B - 1) / B;

		long limit = (sum - 1) * B;
		
		for(int i=1; i<N; i++)
		{
			long now = Math.min(Integer.parseInt(st.nextToken()), limit);

			sum += (now + B - 1)/B;
		}
		
		System.out.print(sum);
	}
}