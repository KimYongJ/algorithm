// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{
	int idx,cnt;
	Node(int idx,int cnt){this.idx=idx; this.cnt=cnt;}
}
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
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.idx-b.idx);
		int N		= read();
		long sum	= 1;
		long tmpSum = 0;
		int idx, cnt;
		for(int i=0; i<N; i++) 
		{
			idx = read();
			cnt = read();
			sum += cnt;
			pq.add(new Node(idx, cnt));
		}
		
		sum /= 2;
		
		for(int i=0; i<N; i++) 
		{
			Node now = pq.poll();
			tmpSum += now.cnt;
			if(sum <= tmpSum) 
			{
				System.out.print(now.idx);
				return;
			}
		}
	}
}