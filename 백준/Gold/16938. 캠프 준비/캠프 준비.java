// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int cnt;
	static int start, first, last;
	static int N, L, R, limit;
	static int arr[];
	public static void comb(int depth, int idx,int sum) {
		if(depth == 0) 
		{
			if(L<=sum && sum<=R && limit<=last-first) 
			{
				cnt++;
			}
			return;
		}
		for(int i=idx; i<N; i++) 
		{
			if(depth == start)
				first = arr[i];
			if(depth == 1)
				last = arr[i];
			
			comb(depth-1, i+1, sum + arr[i]);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N 		= Integer.parseInt(st.nextToken());
		L 		= Integer.parseInt(st.nextToken());
		R 		= Integer.parseInt(st.nextToken());
		limit 	= Integer.parseInt(st.nextToken());
		arr		= new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		// 조합을 구한다. 2개부터 N개 까지 고르는 것
		for(start=2; start<=N; start++)
			comb(start,0,0);
		
		System.out.print(cnt);
	}
}