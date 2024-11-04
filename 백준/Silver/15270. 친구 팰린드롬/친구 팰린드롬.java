//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15270
class Edge{
	int a,b;
	Edge(int a, int b){this.a=a; this.b=b;}
}
class Main{
	
	static int N, M, max;
	static boolean visit[];
	static Edge edge[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int idx, int sum) {
		max = Math.max(max, sum);
		
		if(idx == M)
			return;

		int a = edge[idx].a;
		int b = edge[idx].b;
		if(!visit[a] && !visit[b])
		{
			visit[a] = visit[b] = true;
			DFS(idx + 1, sum + 2);
			visit[a] = visit[b] = false;
		}
		
		DFS(idx + 1, sum);
	}
	
	public static void main(String[] args)throws Exception{
		N		= read();	// 2<=20
		M		= read();	// 0<=(N*N - N)/2
		visit	= new boolean[N + 1];
		edge	= new Edge[M + 1];
		
		for(int i=0; i<M; i++)
			edge[i] = new Edge(read(), read());
		
		DFS(0, 0);
		
		if(max < N)
			++max;
		System.out.print(max);
	}
}