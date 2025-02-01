//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2491
//1초 / 128mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int res = 0;
		int inc = 0;
		int dec = 0;
		
		for(int i=1; i<N; i++)
		{
			if(arr[i-1] < arr[i])
			{
				inc++;
				dec = 0;
			}
			else if(arr[i-1] == arr[i])
			{
				++inc;
				++dec;
			}
			else
			{
				++dec;
				inc = 0;
			}
			res = Math.max(res, Math.max(inc, dec));
		}
		System.out.print(res+1);
	}
}