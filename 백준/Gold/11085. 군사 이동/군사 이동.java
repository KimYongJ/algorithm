//https://www.acmicpc.net/problem/11085
//2초 256MB

import java.util.PriorityQueue;

class Main{

	static int P, W;
	static int start, end;
	static int parent[];
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		P = in.nextInt();
		W = in.nextInt();
		start = in.nextInt();
		end = in.nextInt();
		parent = new int[P + 1];
		pq = new PriorityQueue<>();
		
		for(int i=1; i<=P; i++)
			parent[i] = i;
		
		while(W-->0)
		{
			int n1 = in.nextInt();
			int n2 = in.nextInt();
			int dist = in.nextInt();
			pq.add(new Node(n1, n2, dist));
		}
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			int parent1 = find(now.n1);
			int parent2 = find(now.n2);
			if(parent1 == parent2)
				continue;
			
			if(parent[parent1] < parent[parent2])
				parent[parent2] = parent1;
			else
				parent[parent1] = parent2;
			
			if(find(start) == find(end))
			{
				System.out.print(now.dist);
				return;
			}
			
		}
		System.out.print(0);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	static class Node implements Comparable<Node>{
		int n1, n2, dist;
		Node(int n1, int n2, int dist){
			this.n1=n1;
			this.n2=n2;
			this.dist=dist;
		}
		@Override
		public int compareTo(Node o) {
			return o.dist - dist;
		}
	}
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;
	    int nextInt() throws Exception {
	        int n = 0;
	        byte c;
	        boolean isMinus = false;
	        while ((c = read()) <= 32) { if (size < 0) return -1; }
	        if (c == 45) { c = read(); isMinus = true; }
	        do n = (n << 3) + (n << 1) + (c & 15);
	        while (isNumber(c = read()));
	        return isMinus ? ~n + 1 : n;
	    }
	    boolean isNumber(byte c) {
	        return 47 < c && c < 58;
	    }
	    byte read() throws Exception {
	        if (index == size) {
	            size = System.in.read(buffer, index = 0, SIZE);
	            if (size < 0) buffer[0] = -1;
	        }
	        return buffer[index++];
	    }
	}
}