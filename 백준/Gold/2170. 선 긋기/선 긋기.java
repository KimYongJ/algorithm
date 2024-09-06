//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2170
import java.util.PriorityQueue;
class Node{int s,e; Node(int s,int e){this.s=s;this.e=e;}}
class Main{
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.s-b.s);
		int N	= read();
		int res = 0;
		
		for(int i=0; i<N; i++)
			pq.add(new Node(read(),read()));
		
		Node now	= pq.poll();
		int s		= now.s;
		int e		= now.e;
		while(!pq.isEmpty())
		{
			now = pq.poll();
			if(now.s <= e)
			{
				e = Math.max(e, now.e);
			}
			else 
			{
				res += e - s;
				s = now.s;
				e = now.e;
			}
		}
		System.out.print(res + e - s);
	}
}