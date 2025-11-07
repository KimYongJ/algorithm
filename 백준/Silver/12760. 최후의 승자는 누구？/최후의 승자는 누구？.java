//https://www.acmicpc.net/problem/12760
//1초 128MB
//5 3// 플레이어 수 (2<=100), 카드수(1<=100)
//5 4 3 // 1번 플레이어부터 , 플레이어 수 만큼 카드 번호가 입력됨(1<=100)
//3 4 5
//3 5 4
//4 5 3
//3 4 4
//답 : 1 2 3 4

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int score[] = new int[N + 1];
		PriorityQueue<Integer>[] pq= new PriorityQueue[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			pq[i] = new PriorityQueue<>((a,b) -> b-a);
			for(int j=0; j<M; j++)
			{
				int num = Integer.parseInt(st.nextToken());
				pq[i].add(num);
			}
		}
		
		for(int i=0; i<M; i++)
		{
			int max = 0;
			
			for(int j=1; j<=N; j++)
				max = Math.max(pq[j].peek(), max);
			
			for(int j=1; j<=N; j++)
			{
				int num = pq[j].poll();
				if(max == num)
					++score[j];
			}
		}
		
		int max = 0;
		for(int i=1; i<=N; i++)
			max = Math.max(max, score[i]);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			if(max == score[i])
				sb.append(i).append(' ');
		
		System.out.print(sb);
	}
}