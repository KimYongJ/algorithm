//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17197
class Node{
	int node; Node next;
	Node(int n, Node t){node=n; next=t;}
}
class Main{
	
	static final int MAX = 1<<30;
	static Node adNode[];
	static boolean visit[];
	static int position[][];
	static int minX, maxX, minY, maxY, res;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node) {
		minX = Math.min(minX, position[node][0]);
		minY = Math.min(minY, position[node][1]);
		maxX = Math.max(maxX, position[node][0]);
		maxY = Math.max(maxY, position[node][1]);
		visit[node] = true;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
				DFS(next.node);
	}
	
	public static void main(String[] args)throws Exception{
		int N		= read();	// 소들의 좌표 수 (2<=십만)
		int M		= read();	// 소들의 연결관계 수 (2<=십만)
		adNode		= new Node[N+1];
		position	= new int[N+1][2];
		visit		= new boolean[N+1];
		res 		= MAX;
		
		for(int i=1; i<=N; i++)
		{
			position[i][0] = read();
			position[i][1] = read();
		}
		
		while(M-->0)
		{
			int a		= read();
			int b		= read();
			adNode[a]	= new Node(b, adNode[a]);
			adNode[b]	= new Node(a, adNode[b]);
		}

		for(int i=1; i<=N; i++)
			if(!visit[i])
			{
				minX = MAX;
				minY = MAX;
				maxX = 0;
				maxY = 0;
				DFS(i);
				res = Math.min(((maxY-minY) + (maxX-minX))<<1,res);
			}
		System.out.print(res);
	}
}