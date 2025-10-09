//https://www.acmicpc.net/problem/2840
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		char arr[] = new char[N];
		boolean used[] = new boolean[26];
		
		Arrays.fill(arr, '?');
		
		int idx = 0;
		
		while(K-->0)
		{
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			
			idx = (idx + j) % N;
			
			if(arr[idx] == '?')
			{
				if(used[c - 'A'])
				{
					System.out.print('!');
					return;
				}
			}
			else if(arr[idx] != c)
			{
				System.out.print('!');
				return;
			}
			
			arr[idx] = c;
			used[c - 'A'] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=idx; i>=0; i--)	sb.append(arr[i]);
		for(int i=N - 1; i>idx; i--)	sb.append(arr[i]);
		
		System.out.print(sb);
	}
}