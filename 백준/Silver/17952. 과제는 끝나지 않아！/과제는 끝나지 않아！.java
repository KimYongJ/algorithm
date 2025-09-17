//https://www.acmicpc.net/problem/17952
//2초 256MB
//3 // 최대 분수(1<=1,000,000)
//1 100 3 // 1 A T : 과제 만점은 A(1<=100)이고, 과제 해결시 T분(1<=1,000,000)이 걸린다.
//0 // 0입력은 해당 시점에 주어진 과제가 없는 것
//0
//성애가 받을 과제 점수 출력 : 100

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Main{
	
	static int res;
	static int N;
	static ArrayDeque<Work> q;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		q = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			if(a == 0)
			{
				if(!q.isEmpty())
				{
					Work w = q.peekFirst();
					if(--w.time == 0)
					{
						res += w.score;
						q.pollFirst();
					}
				}
				continue;
			}
			
			int score = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken()) - 1;
			
			if(time == 0)
			{
				res += score;
				continue;
			}
			q.addFirst(new Work(time, score));
		}
		
		System.out.print(res);
	}
	static class Work{
		int time, score;
		Work(int t, int s){
			time = t;
			score = s;
		}
	}
}