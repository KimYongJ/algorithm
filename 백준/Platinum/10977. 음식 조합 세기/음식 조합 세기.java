//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10977

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());	// 음식 가지수(1<=백만)
			int N = Integer.parseInt(st.nextToken());	// 끼니마다 제공되는 음식가지수(1<=백만)
			int food[] = new int[M];
			int fail[] = new int[M];
			
			for(int i=0; i<N; i++)
				food[Integer.parseInt(br.readLine()) - 1] = 1;
			
			for(int i=0; i<M; i++)
			{
				int j = i;
				while(0< j && food[fail[j - 1]] != food[i])
					j = fail[j - 1];
				
				if(j <= 0)
					fail[i] = 0;
				else
					fail[i] = fail[j - 1] + 1;
			}
			
			int tmp = M - fail[M - 1];
			
			sb.append(M % tmp == 0 ? tmp : M).append('\n');
		}
		System.out.print(sb);
	}
}