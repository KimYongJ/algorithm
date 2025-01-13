//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1940

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 1<=만오천
		int M		= Integer.parseInt(br.readLine());	// 1<=천만
		int arr[]	= new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		int s = 0;
		int e = N-1;
		int cnt = 0;
		while(s<e)
		{
			int sum = arr[e] + arr[s];
			if(sum == M) {
				++cnt;
				--e;
			}
			else if(sum < M)
				++s;
			else
				--e;
		}
		System.out.print(cnt);
	}
}