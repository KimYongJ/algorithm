//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/29792
//1초 / 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<>((a,b)->Long.compare(b, a));
		int N = Integer.parseInt(st.nextToken());//보유 케릭 수 1<=49
		int M = Integer.parseInt(st.nextToken());//하루에 사용할 케릭 수 1<=N
		int K = Integer.parseInt(st.nextToken());//보스 가지 수 1<=13
		
		double damage[] = new double[N + 1];// 캐릭당 1초에 가하는 데미지
		long energy[] = new long[K + 1];// 보스의 체력
		long money[]  = new long[K + 1];// 보스 잡을 시 메소
		
		for(int i=1; i<=N; i++)
			damage[i] = Double.parseDouble(br.readLine());
		
		for(int i=1; i<=K; i++)
		{
			st = new StringTokenizer(br.readLine());
			energy[i]	= Long.parseLong(st.nextToken());
			money[i]	= Long.parseLong(st.nextToken());
		}
		
		for(int i=1; i<=N; i++)
		{
			double dam = damage[i];
			long dp[][] = new long[K + 1][901];
			
			for(int k=1; k<=K; k++)
			{
				int time = (int)Math.ceil(energy[k] / dam);
				for(int t=1; t<=900; t++)
				{
					dp[k][t] = dp[k-1][t];
					if(0<=t-time)
						dp[k][t] = Math.max(dp[k][t], dp[k-1][t-time] + money[k]);
				}
			}
			
			pq.add(dp[K][900]);
		}
		
		
		long res = 0;
		
		while(M-->0)
			res += pq.poll();
		
		System.out.print(res);
	}
}