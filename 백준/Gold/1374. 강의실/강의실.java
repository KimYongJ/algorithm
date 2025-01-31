//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1374
//2초 / 128mb
import java.util.PriorityQueue;

class Node implements Comparable<Node>{
	int s,e;
	Node(int s,int e){
		this.s=s;
		this.e=e;
	}
	@Override
	public int compareTo(Node o) {
		return s - o.s;
	}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> rooms	= new PriorityQueue<>();
		PriorityQueue<Node> list		= new PriorityQueue<>();
		
		int res = 0;
		int T	= read();
		
		while(T-->0)
		{
			read();
			list.add(new Node(read(),read()));
		}
		
		while(!list.isEmpty()) 
		{
			Node now = list.poll();
			if(!rooms.isEmpty() && rooms.peek() <= now.s) 
				rooms.poll();
			else
				res++;
			
			rooms.add(now.e);
		}
		System.out.print(res);
	}
}