//https://www.acmicpc.net/problem/17273
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int front[] = new int[N];
		int back[] = new int[N];
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			front[i] = Integer.parseInt(st.nextToken());
			back[i] = Integer.parseInt(st.nextToken());
		}
		
		while(M-->0)
		{
			int k = Integer.parseInt(br.readLine());
			
			for(int i=0; i<N; i++)
				if(front[i] <= k)
				{
					int tmp = front[i];
					front[i] = back[i];
					back[i] = tmp;
				}
		}
		
		int sum = 0;
		
		for(int f : front)
			sum += f;
		
		System.out.print(sum);
	}
}