//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6159

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 소의 수(2<=이만)
		int S		= Integer.parseInt(st.nextToken());	// 매칭값(1<=백만)
		int arr[]	= new int[N];
		int res		= 0;
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());// 매칭값(1<=백만)
		
		Arrays.sort(arr);
		
		for(int s=0; s<N-1; s++)
		{
			for(int e=s+1; e<N; e++)
			{
				int sum = arr[s] + arr[e];
				if(sum <= S)
				{
					++res;
				}else break;
			}
		}
		System.out.print(res);
	}
}