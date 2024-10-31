//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20171
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int		N, K, res;
	static Node		adNode[];
	static boolean	visit[], apt[];
	
	static int read() throws Exception{
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {c = System.in.read();}
        if (c == '-') {c = System.in.read();}
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
	
	public static boolean DFS(int node)
	{
		boolean findApt = false;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
		{
			if(!visit[next.node])
			{
				visit[next.node] = true;
				if(DFS(next.node))
					findApt = true;
			}
		}
		if(!findApt && !apt[node])
			return false;
		res++;
		return true;
	}
	
	public static void main(String[] args)throws Exception{
		N		= read();	// 노드 수 (3<=십만)
		K		= read();	// 아파트 위치 수(1<=N)
		adNode	= new Node[N+1];
		apt		= new boolean[N+1];
		visit	= new boolean[N+1];
		
		for(int i=1; i<N; i++)
		{
			int a = read();
			int b = read();
					read();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		while(K-->0)
			apt[read()] = true;
		
		for(int i=1; i<=N; i++)
			if(apt[i] && !visit[i])	// 방문하지 않았고 아파트가 아닐 때
			{
				visit[i] = true;
				DFS(i);
			}
		
		System.out.print(res);
	}
}