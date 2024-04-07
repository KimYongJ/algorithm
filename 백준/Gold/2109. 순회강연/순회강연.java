// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int money, day;
	Node(int money, int day){this.money = money; this.day = day;}
}
class Main{
	static Node		 	now;
	static boolean		visit[];
	static int			a, b, N, money;
	static PriorityQueue<Node> pq;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		pq 		= new PriorityQueue<Node>((a,b)->b.money-a.money);
		visit 	= new boolean[10001];
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			pq.add(new Node(a,b));
		}
		
		while(!pq.isEmpty()) 
		{
			now = pq.poll();
			
			if(!visit[now.day]) 
			{
				visit[now.day] = true;
				money += now.money;
			}else 
			{
				for(int i=now.day-1; i>0; i--) 
				{
					if(!visit[i]) 
					{
						visit[i] = true;
						money += now.money;
						break;
					}
				}
			}
		}
		
		System.out.println(money);
	}
}