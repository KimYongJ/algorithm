//https://www.acmicpc.net/problem/14382
//5초 512MB
//5 // 테스트 케이스 수 
//0
//1
//2
//11
//1692
//답
//Case #1: INSOMNIA
//Case #2: 10
//Case #3: 90
//Case #4: 110
//Case #5: 5076

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
			
			long N = Integer.parseInt(br.readLine());

			if(N == 0)
			{
				sb.append("INSOMNIA").append('\n');
				continue;
			}
			
			long res = 0;
			int cnt = 0;
			int m = 1;
			
			boolean visit[] = new boolean[10];
			
			while(true)
			{
				long n = N * m;
				
				while(n != 0)
				{
					int j = (int)(n % 10);
					if(!visit[j])
					{
						visit[j] = true;
						++cnt;
					}
					n /= 10;
				}
				if(cnt == 10)
				{
					res = N * m;
					break;
				}
				m++;
			}
			
			sb.append(res).append('\n');
		}
		System.out.print(sb);
	}
}