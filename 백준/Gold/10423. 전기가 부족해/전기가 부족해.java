//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/10423
//1초 256MB
import java.util.PriorityQueue;

class Main{
	public static void main(String[] args)throws Exception{
		Reader in	= new Reader();
		int N		= in.nextInt();// 도시개수N(1<=천)
		int M		= in.nextInt();// 설치가능 케이블수M(1<=십만)
		int K		= in.nextInt();// 발전소개수K(1<=N)
		int parent[]= new int[N + 1];
		int cnt		= N - K;
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		for(int i=0; i<K; i++)
			parent[in.nextInt()] = 0;// 발전소는 같은 부모를 두도록 마킹
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		while(M-->0)
		{
			int a = in.nextInt();// 도시1
			int b = in.nextInt();// 도시2
			int c = in.nextInt();// 비용(0<=만)
			pq.add(new Node(a,b,c));
		}
		
		int ans = 0;
		
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
				
				ans += now.cost;
				cnt -= 1;
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
	int a, b, cost;
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

