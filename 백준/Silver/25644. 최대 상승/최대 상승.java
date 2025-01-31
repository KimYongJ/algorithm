//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25644
//1초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N];
		int min[]	= new int[N];
		int max[]	= new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		min[0] = arr[0];
		for(int i=1; i<N; i++)
			min[i] = Math.min(arr[i], min[i-1]);
		max[N-1] = arr[N-1];
		for(int i=N-2; i>=0; i--)
			max[i] = Math.max(max[i+1], arr[i]);
		
		int result = 0;
		for(int i=0; i<N; i++)
			result = Math.max(result, max[i] - min[i]);

		System.out.print(result);
	}
}
