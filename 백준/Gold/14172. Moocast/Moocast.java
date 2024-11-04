//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14172
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}

class Main{
	
	static Node adNode[];
	static int N;
	static boolean visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	static int DFS(int node) {
		int cnt = 1;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				cnt += DFS(next.node);
			}

		return cnt;
	}
	
	public static void main(String[] args)throws Exception{
		N		= read();
		adNode	= new Node[N];
		
		int map[][] = new int[N][4];
		for(int i=0; i<N; i++)
		{
			map[i][0] = read();
			map[i][1] = read();
			map[i][2] = read();
			map[i][3] = map[i][2] * map[i][2];
		}
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
			{
				int ny = map[i][0] - map[j][0];
				int nx = map[i][1] - map[j][1];
				if(ny*ny + nx*nx <= map[i][3])
					adNode[i] = new Node(j,adNode[i]);
			}
		
		int cnt = 0;
		for(int i=0; i<N; i++)
		{
			visit		= new boolean[N];
			visit[i]	= true;
			cnt = Math.max(cnt, DFS(i));
		}
		System.out.print(cnt);
	}
}