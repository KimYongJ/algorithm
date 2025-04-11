//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14950
//2초 256MB

import java.util.PriorityQueue;

class Main{
	public static void main(String[] args)throws Exception{
		Reader in	= new Reader();
		int N		= in.nextInt();// 도시 수 1<=만
		int M		= in.nextInt();// 도로 개수 1<=만
		int T		= in.nextInt();// 증가 비용 1<=100
		int parent[]= new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		while(M-->0)
		{
			int a		= in.nextInt();// 노드 번호
			int b		= in.nextInt();// 노드 번호
			int cost	= in.nextInt();// 비용 1<=만
			pq.add(new Node(a,b,cost));
		}
		
		int cnt = N - 1;
		int ans	= 0;
		int plus= 0;
		
		while(cnt != 0)
		{
			Node now = pq.poll();
			
			int p1 = getParent(now.a, parent);
			int p2 = getParent(now.b, parent);
			
			if(p1 != p2)
			{
				if(p1 < p2)
					parent[p2] = p1;
				else
					parent[p1] = p2;
				
				
				ans += now.cost + plus;
				
				cnt -= 1;
				
				plus+= T;
			}
		}
		
		System.out.print(ans);
	}
	public static int getParent(int node, int[] parent) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node], parent);
	}
}
class Node implements Comparable<Node>{
	int a, b;
	int cost;
	Node(int a, int b, int cost){
		this.a=a;
		this.b=b;
		this.cost=cost;
	}
	@Override
	public int compareTo(Node o) {
		return cost - o.cost;
	}
}


class Reader {
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
