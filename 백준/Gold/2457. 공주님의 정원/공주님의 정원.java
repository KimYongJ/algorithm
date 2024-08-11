//https://github.com/kimyongj/algorithm
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
	public static void main(String args[])throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.s != b.s  ? a.s-b.s : a.e-b.e);
		int N = read();
		
		for(int i=0; i<N; i++) 
			pq.add(new Node(read() * 100 + read(),read() * 100 + read()));

		int j		= 0;
		int cnt		= 0;
		int end		= 301;
		int maxEnd;
		while(end < 1201)
		{
			maxEnd = 0;
			while(j<N)
			{
				if(pq.peek().s > end)
					break;
				else if(maxEnd < pq.peek().e) {
					maxEnd = pq.poll().e;
				}else {
					pq.poll();
				}
				j++;
			}
			if(maxEnd != 0)
			{
				end = maxEnd;
				cnt++;
			}
			else break;
			
		}
		
		if(end < 1201)
			cnt = 0;
		System.out.print(cnt);
	}
}