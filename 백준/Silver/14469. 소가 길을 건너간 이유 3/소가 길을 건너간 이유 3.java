// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{
	int a, c;
	Node(int a, int c){this.a=a;this.c=c;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.a-b.a);
		int N = read();
		
		while(N-->0)
			pq.add(new Node(read(),read()));
			
		int res = 0;
		while(!pq.isEmpty()) 
		{
			Node now = pq.poll();
			if(now.a > res)
			{
				res = now.a;
			}
			res += now.c;
		}
		System.out.print(res);
	}
}
