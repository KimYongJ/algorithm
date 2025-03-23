//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1647
//2초 128MB

import java.util.PriorityQueue;

class Main{
	
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Reader in = new Reader();
		int N	= in.nextInt();	// 2<=십만
		int M	= in.nextInt();	// 1<=백만
		parent	= new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(M-->0)
			pq.add(new Node(in.nextInt(), in.nextInt(), in.nextInt()));
		
		int total	= 0;
		int max		= 0;
		int repeat	= N - 1;
		
		while(0 < repeat)
		{
			Node now = pq.poll();
			int p1 = getParent(now.a);
			int p2 = getParent(now.b);
			if(p1 != p2)
			{
				if(p1 < p2)
					parent[p1] = p2;
				else
					parent[p2] = p1;
				
				total += now.c;
				repeat-= 1;
				
				if(max < now.c)
					max = now.c;
			}
		}
		System.out.print(total - max);
	}
	public static int getParent(int node) {
		if(parent[node] == node)
			return node;
		return parent[node] = getParent(parent[node]);
	}
}

class Node implements Comparable<Node>{
	int a,b,c;
	Node(int a, int b, int c){
		this.a=a;
		this.b=b;
		this.c=c;
	}
	@Override
	public int compareTo(Node o) {
		return this.c - o.c;
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
