// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{int d, f;Node(int d,int f){this.d=d;this.f=f;}}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq		= new PriorityQueue<Node>((a,b)->a.d-b.d);
		PriorityQueue<Integer>fuelQ = new PriorityQueue<Integer>((a,b)->b-a);
		int N	= read();
		int res = 0;
		
		for(int i=0; i<N; i++) 
			pq.add(new Node(read(),read()));

		int goal	= read();	// 목표위치
		int nowp	= read();	// 초기 연료이자 초기에 갈 수 있는 최대 위치.
		int before	= -1;		// 반복을 종료하기 위한 플레그
		
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