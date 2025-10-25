//https://www.acmicpc.net/problem/23969
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
		int A[] = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		
		for(int i = N; i>=2; i--)
		{
			for(int j=1; j<i; j++)
			{
				if(A[j] > A[j + 1])
				{
					int tmp = A[j];
					A[j] = A[j + 1];
					A[j + 1] = tmp;
					if(--K == 0)
					{
						StringBuilder sb = new StringBuilder();
						
						for(int z = 1; z<=N; z++)
							sb.append(A[z]).append(' ');
						
						System.out.print(sb);
						return;
					}
				}
			}
		}
		
		
		System.out.print(-1);
	}
}