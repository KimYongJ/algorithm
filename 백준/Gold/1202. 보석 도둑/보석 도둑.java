// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{
	int w, c; Node(int w,int c){this.w=w;this.c=c;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> Kpq	= new PriorityQueue<>();
		PriorityQueue<Node> list	= new PriorityQueue<Node>((a,b)->a.w-b.w);
		PriorityQueue<Integer> store= new PriorityQueue<Integer>((a,b)->b-a);
		int N = read();
		int K = read();
		
		for(int i=0; i<N; i++) 
			list.add(new Node(read(),read()));
		
		for(int i=0; i<K; i++)
			Kpq.add(read());
		
		long res = 0;
		while(!Kpq.isEmpty())
		{
			int weight = Kpq.poll();
			
			while(!list.isEmpty() && list.peek().w <= weight)
				store.add(list.poll().c);
			
			if(!store.isEmpty())
				res += store.poll();
		}
		System.out.print(res);
	}
}
