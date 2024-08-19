// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int d, f;Node(int d,int f){this.d=d;this.f=f;}
}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq		= new PriorityQueue<Node>((a,b)->a.d-b.d);
		PriorityQueue<Integer>fuelQ = new PriorityQueue<Integer>((a,b)->b-a);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N	= Integer.parseInt(br.readLine());
		int res = 0;
		
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			pq.add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		st = new StringTokenizer(br.readLine());
		int goal = Integer.parseInt(st.nextToken()); // 목표위치
		int nowp = Integer.parseInt(st.nextToken()); // 초기 연료이자 초기에 갈 수 있는 최대 위치.
		int before = -1;
		
		while(nowp < goal && before != nowp) 
		{
			before = nowp;
			while(!pq.isEmpty() && pq.peek().d <= nowp) 
				fuelQ.add(pq.poll().f);
			
			if(!fuelQ.isEmpty()) 
			{
				nowp += fuelQ.poll();
				res++;
			}
		}
		
		if(nowp < goal)
			res = -1;
		
		System.out.print(res);
	}
}