//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1976
//2초 / 128MB
import java.util.ArrayDeque;
class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node=node;
		this.next=next;
	}
}
class Main{
	
	public static void main(String[] args)throws Exception{
		input();
		bfs();
		print();
	}
	public static void input() throws Exception{
		N		= read();	//도시 수1<=200
		M		= read();	//여행계획에 속한 도시 수 1<=천
		dir		= new int[M];
		visit	= new boolean[N+1];
		adNode	= new Node[N + 1];
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(read() == 1)
				{
					adNode[y] = new Node(x, adNode[y]);
					adNode[x] = new Node(y, adNode[x]);
				}

		for(int i=0; i<M; i++)
			dir[i] = read();
	}
	public static void print() {
		for(int d: dir)
			if(!visit[d])
			{
				System.out.print("NO");
				return;
			}
		System.out.print("YES");
	}
	public static void bfs() {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(dir[0]);
		visit[dir[0]] = true;
		while(!q.isEmpty())
		{
			int now = q.poll();
			for(Node next=adNode[now]; next != null; next=next.next)
			{
				if(!visit[next.node])
				{
					visit[next.node] = true;
					q.add(next.node);
				}
			}
		}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}

	static int N, M, dir[];
	static boolean visit[];
	static Node adNode[];
	
}
