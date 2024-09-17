//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2467

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		int s = 0;
		int e = N-1;
		int diff = Integer.MAX_VALUE;
		int res1 = 0, res2 = 0;
		while(s<e)
		{
			int value = arr[s] + arr[e];
			if(Math.abs(value) < diff) {
				diff = Math.abs(value);
				res1 = arr[s];
				res2 = arr[e];
			}
			if(value < 0) s++;
			else e--;
		}
		System.out.print( new StringBuilder().append(res1).append(' ').append(res2) );
	}
}