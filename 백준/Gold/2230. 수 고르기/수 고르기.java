//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2230

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());
		int M		= Integer.parseInt(st.nextToken());
		int arr[]	= new int[N];

		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		int l = 0;
		int r = 1;
		int min = ~(1<<31);
		while(r<N) {
			int diff = arr[r] - arr[l];
			
			if(diff == M)
			{
				min = M; break;
			}
			if(diff < M)
				++r;
			else
			{
				++l;
				min = Math.min(min, diff);
			}
		}
		
		System.out.print(min);
	}
}