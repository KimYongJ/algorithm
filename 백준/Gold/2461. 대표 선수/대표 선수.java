//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2461
//2초 / 256MB

import java.util.PriorityQueue;

class Node
{
	int idx, value;
	Node(int i, int v)
	{
		idx = i;
		value = v;
	}
}

class Main{
	public static void main(String[] args)throws Exception{
		int N	= read();	// 학급수1<=1000
		int M	= read();	// 학생수1<=1000
		int min	= Integer.MAX_VALUE;
		int max	= 0;
		
		PriorityQueue<Integer>[] pq = new PriorityQueue[N];
		PriorityQueue<Node> comp	= new PriorityQueue<>((a,b)->a.value-b.value);
		
		for(int i=0; i<N; i++)
		{
			pq[i] = new PriorityQueue<>();
			
			for(int j=0; j<M; j++)
				pq[i].add(read());
			
			int value = pq[i].poll();
			comp.add(new Node(i, value));
			min = Math.min(min, value);
			max = Math.max(max, value);
		}
		
		int diff = max - min;
		
		while(true)
		{
			Node now = comp.poll();
			if(pq[now.idx].isEmpty())
				break;
			
			int value = pq[now.idx].poll();
			
			comp.add(new Node(now.idx, value));
			
			min	 = comp.peek().value;
			max	 = Math.max(max, value);
			diff = Math.min(diff, max - min);
		}
		
		System.out.print(diff);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}