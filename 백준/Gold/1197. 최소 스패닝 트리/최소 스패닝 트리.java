//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1197
//1초 128MB
import java.util.PriorityQueue;
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
class Main{
	
	static int[] parent;
	
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int V		= read();	// 1<=만
		int E		= read();	// 1<=십만
		int result	= 0;
		
		parent = new int[V + 1];
		for(int i=1; i<=V; i++)
			parent[i] = i;
		
		for(int i=0; i<E; i++)
			pq.add(new Node(read(), read(), read()));
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			int p1 = getParent(parent[now.a]);
			int p2 = getParent(parent[now.b]);
			if(p1 != p2)
			{
				result += now.c;
				if(p1 < p2)
					parent[p2] = p1;
				else
					parent[p1] = p2;
			}
		}
		System.out.print(result);
	}
	public static int getParent(int node) {
		if(parent[node] == node)
			return node;
		return parent[node] = getParent(parent[node]);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}