//https://www.acmicpc.net/problem/2947
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int arr[] = new int[5];
		
		for(int i=0; i<5; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Loop : 
		while(true)
		{
			for(int i=1, prev, cnt; i<5; i++)
			{
				prev = 0;
				cnt = 0;
				if(arr[i - 1] > arr[i])
				{
					int tmp = arr[i-1];
					arr[i-1] = arr[i];
					arr[i] = tmp;
					
					cnt = 0;
					
					for(int a : arr)
					{
						sb.append(a).append(' ');
						
						if(prev + 1 == a)
							++cnt;
						
						prev = a;
					}
					sb.append('\n');
					
					if(cnt == 5)
						break Loop;
				}
			}
		}
		System.out.print(sb);
	}
}