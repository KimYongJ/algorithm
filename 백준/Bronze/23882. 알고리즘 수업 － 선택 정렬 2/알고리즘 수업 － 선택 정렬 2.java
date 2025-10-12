//https://www.acmicpc.net/problem/23882
//1초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int arr[] = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int j = N - 1; j >= 1 && K > 0; j--)
		{
			int maxIdx = -1;
			int max = arr[j];
			
			for(int i = j - 1; i>=0; i--)
			{
				if(max < arr[i])
				{
					max = arr[i];
					maxIdx = i;
				}
			}
			
			if(maxIdx >= 0)
			{
				int tmp = arr[j];
				arr[j] = arr[maxIdx];
				arr[maxIdx] = tmp;
				
				if(--K == 0)
				{
					StringBuilder sb = new StringBuilder();
					
					for(int a : arr)sb.append(a).append(' ');
					
					System.out.print(sb);
					return;
				}
			}
			
		}
		
		System.out.print(-1);
	}
}