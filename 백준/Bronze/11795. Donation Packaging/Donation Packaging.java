//https://www.acmicpc.net/problem/11795
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int p[] = new int[3];
		for(int i=0; i<N; i++)
		{
			int min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++)
				min = Math.min(min,p[j] += Integer.parseInt(st.nextToken()));
			
			if(min >= 30)
			{
				sb.append(min);
				for(int j=0; j<3; j++)
					p[j] -= min;
			}
			else
				sb.append("NO");
			
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}