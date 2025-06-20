//https://www.acmicpc.net/problem/7044
//1초 128MB
//5 8// 노드수(2<=1,000), 간선수(1<=20,000)
//1 2 3// 간선 수만큼 두노드와 비용이 주어짐
//1 3 7
//2 3 10
//2 4 4
//2 5 8
//3 4 6
//3 5 2
//4 5 17
//답(불가능할 경우 -1 출력) : 42

import java.util.PriorityQueue;

class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Reader in = new Reader();
		int N = in.nextInt();// 노드수(2<=1,000)
		int M = in.nextInt();// 간선수(1<=20,000)
		int parent[] = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(M-->0)
			pq.add(new Node(in.nextInt(),in.nextInt(),in.nextInt()));
		
		int edgeCnt = 1;
		int distSum = 0;
		
		while(!pq.isEmpty() && edgeCnt != N)
		{
			Node now = pq.poll();
			if(union(now.n1, now.n2, parent))
			{
				edgeCnt += 1;
				distSum += now.dist;
			}
		}
		
		System.out.print(edgeCnt == N ? distSum : -1);
	}
	static boolean union(int n1, int n2, int[] parent) {
		int p1 = find(n1, parent);
		int p2 = find(n2, parent);
		
		if(p1 == p2)
			return false;
		
		if(parent[p1] < parent[p2])
			parent[p2] = p1;
		else
			parent[p1] = p2;
		
		return true;
	}
	static int find(int node, int [] parent) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node], parent);
	}
	static class Node implements Comparable<Node>{
		int n1, n2, dist;
		Node(int n1, int n2, int d){
			this.n1=n1;
			this.n2=n2;
			this.dist = d;
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