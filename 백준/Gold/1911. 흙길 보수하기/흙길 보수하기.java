//https://github.com/KimYongJ/algorithm
import java.util.PriorityQueue;
class Node{
	int s,e;
	Node(int s, int e){this.s=s; this.e=e;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.s-b.s);
		int N = read();
		int L = read();
		int idx = 0;
		int cnt = 0;
		for(int i=0; i<N; i++) 
			pq.add(new Node(read(),read()));
		
		while(!pq.isEmpty()) 
		{
			Node now = pq.poll();
			int s = Math.max(now.s, idx);
			int l = now.e-s;
			if(l > 0) 
			{
				int c = l/L;
				if(l%L > 0) c++;
				cnt += c;
				idx = s + c*L;
			}
		}
		System.out.print(cnt);
	}
}