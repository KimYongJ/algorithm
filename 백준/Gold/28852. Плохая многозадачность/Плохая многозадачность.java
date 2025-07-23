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
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		
		arr[0] = Integer.parseInt(st.nextToken());
		
		int limit = (int) ((Math.ceil(arr[0]/B) - 1) * B);
		
		for(int i=1; i<N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(arr[i] > limit)
				arr[i] = limit;
		}
		
		long sum = 0;
		
		for(int i=0; i<N; i++)
			sum += ((int)Math.ceil(arr[i]/B));
		
		System.out.print(sum);
	}
}