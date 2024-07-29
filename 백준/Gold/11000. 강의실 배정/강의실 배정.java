// https://github.com/kimyongj/algorithm
import java.util.Arrays;
import java.util.PriorityQueue;
class Node{
	int s,e;
	Node(int s,int e){this.s=s;this.e=e;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N		= read();
		Node[] list = new Node[N];

		for(int i=0; i<N; i++) 
			list[i] = new Node(read(),read());
		
		Arrays.sort(list,(a,b)->a.s-b.s);

		pq.add(list[0].e);
		
		for(int i=1; i<N; i++) 
		{
			if(pq.peek() <= list[i].s) 
				pq.poll();

			pq.add(list[i].e);
		}
		System.out.print(pq.size());
	}
}