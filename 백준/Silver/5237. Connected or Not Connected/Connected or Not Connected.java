//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5237

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static Node adNode[];
	static boolean visit[];
	
    static int read() throws Exception{
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {c = System.in.read();}
        if (c == '-') {c = System.in.read();}
        do {val = 10 * val + c - 48;}
        while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
	
	public static int DFS(int node) {
		int cnt		= 1;
		visit[node] = true;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
				cnt += DFS(next.node);
		
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			int N	= read();
			int K	= read();
			adNode	= new Node[N];
			visit	= new boolean[N];
			
			while(K-->0)
			{
				int a		= read();
				int b		= read();
				adNode[a]	= new Node(b, adNode[a]);
				adNode[b]	= new Node(a, adNode[b]);
			}
			
			if(N == DFS(0))
				sb.append("Connected.");
			else
				sb.append("Not connected.");
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}