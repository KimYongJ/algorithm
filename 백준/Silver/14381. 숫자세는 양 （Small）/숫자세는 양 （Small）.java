//https://www.acmicpc.net/problem/14381
//5초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++)
		{
			sb.append("Case #").append(i).append(": ");
			int N = Integer.parseInt(br.readLine());
			if(N == 0)
			{
				sb.append("INSOMNIA").append('\n');
				continue;
			}
			
			int cnt = 0;
			boolean visit[] = new boolean[10];
			int j = 1;
			
			while(cnt != 10)
			{
				int next = N * j++;
				
				for(char c : String.valueOf(next).toCharArray())
				{
					int n = c - '0';
					if(!visit[n])
					{
						++cnt;
						visit[n] = true;
					}
				}
				
				if(cnt == 10)
				{
					sb.append(next).append('\n');
					break;
				}
			}
			
		}
		System.out.print(sb);
	}
}
