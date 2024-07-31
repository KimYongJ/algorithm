// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{
	int s,e; Node(int s,int e){this.s=s; this.e=e;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> list	= new PriorityQueue<Node>((a,b)->a.s-b.s);
		PriorityQueue<Integer> rooms= new PriorityQueue<>();
		int N	= read();
		int cnt = 0;
		
		for(int i=0; i<N; i++) 
			list.add(new Node(read(),read()));
		
		while(N-->0)
		{
			Node now = list.poll();
			
			if(!rooms.isEmpty() && rooms.peek() <= now.s)
				rooms.poll();
			else 
				cnt++;
			
			rooms.add(now.e);
		}
		System.out.print(cnt);
	}
}