// https://github.com/kimyongj/algorithm
import java.util.Collections;
import java.util.PriorityQueue;
class Node{
	int base, inc;
	Node(int base, int inc){this.base=base; this.inc=inc;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.inc - b.inc);
		long sum	= 0;
		int N		= read();
		int base[]	= new int[N];
		
		for(int i=0; i<N; i++)base[i] = read();
		for(int i=0; i<N; i++)pq.add(new Node(base[i], read()));
		
		for(int i=0; i<N; i++) 
		{
			Node node = pq.poll();
			sum += node.base + (node.inc * (long)(i));
		}
		System.out.print(sum);
	}
}