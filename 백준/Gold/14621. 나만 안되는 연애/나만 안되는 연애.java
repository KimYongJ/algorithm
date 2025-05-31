//https://www.acmicpc.net/problem/14621
//2초 256MB
//5 7// 학교 수 (2 ≤ N ≤ 1,000)도로의 개수 (1 ≤ M ≤ 10,000)
//M W W W M// 성별
//1 2 12// 노드 u, v와 거리 d(1 ≤ d ≤ 1,000)
//1 3 10
//4 2 5
//5 2 5
//2 5 10
//3 4 3
//5 4 7

import java.util.PriorityQueue;

class Main{
	
	static int N, M;
	static int ans;
	static boolean gender[];
	static UnionFind uf;
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		gender = new boolean[N + 1];
		uf = new UnionFind(N);
		pq = new PriorityQueue<>();
		
		for(int i=1; i<=N; i++)
			gender[i] = "M".equals(in.nextString());
		
		while(M-->0)
		{
			int node1 = in.nextInt();
			int node2 = in.nextInt();
			int dist = in.nextInt();
			if(gender[node1] != gender[node2])
				pq.add(new Node(node1, node2, dist));
		}
		
		int edgeCnt = 0;
		
		while(!pq.isEmpty() && edgeCnt + 1!= N)
		{
			Node now = pq.poll();
			
			if(uf.union(now.n1, now.n2))
			{
				edgeCnt += 1;
				ans += now.dist;
			}
		}
		
		if(edgeCnt + 1 == N)
		{
			System.out.print(ans);
			return;
		}
		
		System.out.print(-1);
	}
	static class UnionFind{
		int N;
		int parent[];
		UnionFind(int N)
		{
			this.N = N;
			this.parent = new int[N + 1];
			for(int i=1; i<=N; i++)
				parent[i] = i;
		}
		int find(int node)
		{
			if(parent[node] == node) return node;
			return parent[node] = find(parent[node]);
		}
		boolean union(int n1, int n2)
		{
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
	}
	static class Node implements Comparable<Node>{
		int n1, n2, dist;
		Node(int n1, int n2, int dist){
			this.n1 = n1;
			this.n2 = n2;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			return dist - o.dist;
		}
	}
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;
	    String nextString() throws Exception {
	        StringBuilder sb = new StringBuilder();
	        byte c;
	        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
	        do sb.appendCodePoint(c);
	        while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
	        return sb.toString();
	    }
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