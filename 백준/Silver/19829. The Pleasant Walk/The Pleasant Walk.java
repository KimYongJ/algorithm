//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19829
//1초 / 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 집의 수(1<=십만)
		int K		= Integer.parseInt(st.nextToken());	// 색상 수(1<=십만)
		int max		= 1;
		int arr[]	= new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int s = 0;
		int e = 1;
		while(e<N)
		{
			if(arr[e] != arr[e-1])
			{
				max = Math.max(max, e-s+1);
				++e;
			}
			else
			{
				s = e ++;
			}
		}
		
		System.out.print(max);
	}
}