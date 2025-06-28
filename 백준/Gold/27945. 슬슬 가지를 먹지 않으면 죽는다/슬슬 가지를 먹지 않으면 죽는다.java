//https://www.acmicpc.net/problem/27945
//1초 1024MB

import java.util.PriorityQueue;

class Main{
	
	static int N, M;
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(M-->0)
			pq.add(new Node(in.nextInt(),in.nextInt(),in.nextInt()));
		
		
		int edgeCnt = 1;
		int day = 0;
		
		while(!pq.isEmpty() && edgeCnt != N)
		{
			Node now = pq.poll();
			int p1 = find(now.n1);
			int p2 = find(now.n2);
			
			if(p1 == p2)
				continue;
			
			if(day + 1 != now.t)
				break;
			
			day++;
			edgeCnt++;
			
			if(parent[p1] < parent[p2])
				parent[p2] = p1;
			else
				parent[p1] = p2;
		}
		
		System.out.print(day + 1);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	static class Node implements Comparable<Node>{
		int n1, n2, t;
		Node(int n1, int n2, int t){
			this.n1=n1;
			this.n2=n2;
			this.t=t;
		}
		@Override
		public int compareTo(Node o) {
			return t - o.t;
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