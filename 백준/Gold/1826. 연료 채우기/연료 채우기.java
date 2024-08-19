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
		
		while(nowp < goal)		// 현재 위치가 목표위치보다 작을 때
		{
			while(!pq.isEmpty() && pq.peek().d <= nowp) 
				fuelQ.add(pq.poll().f);// 현재위치보다 작은 주유소들의 연료를 fuelQ에삽입
			
			if(!fuelQ.isEmpty())
			{
				nowp += fuelQ.poll();// 큐에서 가장큰 연료를 하나 꺼내고 결과 +1
				res++;
			}else break;// 큐가 비어있다면 더 이상 갈 수 없는 것이므로 종료
		}
		
		if(nowp < goal)// 목표에 도달하지 못했을 때
			res = -1;
		
		System.out.print(res);
	}
}