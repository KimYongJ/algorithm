//https://www.acmicpc.net/problem/31860
//1초 512MB
//2 5 3 // 일 개수(2<=2000), 감소 중요도(1<=5), 완료 중요도(1<=3)
//10 // 중요도(<1000)
//18
//답, 일이 끝날 때 까지 일수 및 각 날의 만족감 출력
//5
//18
//22
//21
//18
//14

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());// 일 개수(2<=2000)
		int M = Integer.parseInt(st.nextToken());// 감소 중요도(1<=5)
		int K = Integer.parseInt(st.nextToken());// 완료 중요도(1<=3)
		int day = 0;
		int prev = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
		
		while(N-->0) pq.add(Integer.parseInt(br.readLine()));
			
		while(!pq.isEmpty())
		{
			int now = pq.poll();
			
			++day;
			
			sb.append(prev = prev / 2 + now).append('\n');
			
			now -= M;
			
			if(now > K)
				pq.add(now);
		}
		
		System.out.println(day);
		System.out.print(sb);
	}
}