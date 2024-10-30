//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/11581
// 시작 노드는 1번, 도착 노드는 N
class Node{
	int node; Node next;
	Node(int node, Node next){
		this.node=node; this.next=next;
	}
}
class Main{
	
	static Node[] adNode;
	static int N;
	static boolean visit[];
	
    private static int read() throws Exception{
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
	
	public static boolean DFS(int now) {
		visit[now] = true;
		for(Node next=adNode[now]; next!=null; next=next.next)
		{
			if(!visit[next.node])
			{
				if(DFS(next.node))
					return true;
			}
			else
				return true;
		}
		
		visit[now] = false;
		
		return false;
	}
	public static void main(String[] args)throws Exception{
		N		= read();	// 노드 수(1<=100)
		adNode	= new Node[N+10];
		visit	= new boolean[N+10];
		
		for(int i=1; i<N; i++)
		{
			int T = read();
			while(T-->0)
				adNode[i] = new Node(read(), adNode[i]);
		}
		
		if(DFS(1))
			System.out.print("CYCLE");
		else
			System.out.print("NO CYCLE");
	}
}