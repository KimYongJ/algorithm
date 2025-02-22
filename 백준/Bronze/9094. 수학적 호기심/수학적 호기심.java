//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9094
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int cnt = 0;
			 for(int i=1; i<n - 1; i++)
				 for(int j=i+1; j<n; j++)
					 if((i*i + j*j + m)%(i*j) == 0)
						 ++cnt;

			sb.append(cnt).append('\n');
		}
		
		System.out.print(sb);
	}
}