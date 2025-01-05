//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27210

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N+1];
		int brr[]	= new int[N+1];
		int max		= 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			brr[i] = arr[i] = Integer.parseInt(st.nextToken());
			
			if(arr[i] == 2)
			{
				arr[i] = -1;
				brr[i] = 1;
			}
			else
				brr[i] = -1;
			
			arr[i] += arr[i-1];
			brr[i] += brr[i-1];
			max = Math.max(max, arr[i]);
			max = Math.max(max, brr[i]);
			if(arr[i] < 0)
				arr[i] = 0;
			if(brr[i] < 0)
				brr[i] = 0;
		}
		System.out.print(max);
	}
}