// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int cnt;
	static int N, L, R, limit;
	static int arr[];

	public static void comb(int depth, int idx, int sum, int bit) {
		if(depth == 0) 
		{
			if(L<=sum && sum<=R && 
					arr[31-Integer.numberOfLeadingZeros(bit)] - arr[Integer.numberOfTrailingZeros(bit)] >= limit)
			{
				cnt++;
			}
			return;
		}
		
		for(int i=idx; i<N; i++)
			comb(depth - 1, i+1, sum + arr[i], bit | 1<<i);
			
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
		for(int i=2; i<=N; i++)
			comb(i,0,0,0);
		
		System.out.print(cnt);
	}
}