//https://www.acmicpc.net/problem/13905
//1초 256MB

import java.util.PriorityQueue;

class Main{

	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		int N = in.nextInt();//집의 수(2≤N≤100,000)
		int M = in.nextInt();//다리의 수(1≤M≤300,000)
		int s = in.nextInt();
		int e = in.nextInt();
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		UnionFind uf = new UnionFind(N);
		
		for(int i=0; i<M; i++)
			pq.add(new Node(in.nextInt(),in.nextInt(),in.nextInt()));
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			if(uf.union(now.n1, now.n2) && uf.find(s) == uf.find(e))
			{
				System.out.print(now.dist);
				return;
			}
		}
		
		System.out.print(0);
	}

	static class UnionFind{
		int N;
		int parent[];
		UnionFind(int N){
			this.N=N;
			this.parent = new int[N + 1];
			for(int i=1; i<=N; i++)
				parent[i] = i;
		}
		boolean union(int n1, int n2) {
			int parent1 = find(n1);
			int parent2 = find(n2);
			if(parent1 == parent2)
				return false;
			
			if(parent[parent1] < parent[parent2])
				parent[parent2] = parent1;
			else
				parent[parent1] = parent2;
			
			return true;
		}
		int find(int node) {
			if(parent[node] == node) return node;
			return parent[node] = find(parent[node]);
		}
	}

	static class Node implements Comparable<Node>{
		int n1, n2, dist;
		Node(int n1, int n2, int dist){
			this.n1=n1;
			this.n2=n2;
			this.dist = dist;
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