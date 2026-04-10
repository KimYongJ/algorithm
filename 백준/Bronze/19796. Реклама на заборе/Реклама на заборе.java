//https://www.acmicpc.net/problem/19796
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int block[] = new int[M + 2];
		
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			block[s]++;
			block[e + 1]--;
		}
		
		boolean flag = true;
		
		for(int i=1; i<=M; i++)
		{
			block[i] += block[i-1];
			if(block[i] <= 0)
			{
				flag = false;
				break;
			}
		}
		
		System.out.print(flag ? "YES" : "NO");
	}
}