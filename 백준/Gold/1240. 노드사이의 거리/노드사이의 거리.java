// https://github.com/kimyongj/algorithm
class Node{
	int node, dist;
	Node before;
	Node(int node, int dist, Node before){
		this.node = node;
		this.dist = dist;
		this.before = before;
	}
}
class Main{
	static int N, M, start, end, dist;
	static Node adNode[];
	static boolean visit[];
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void DFS(int node, int sum) {
		if(dist != 0) return;
		if(node == end) 
		{
			dist = sum;
			return;
		}
		for(Node now=adNode[node]; now!=null; now=now.before) 
		{
			if(!visit[now.node]) 
			{
				visit[now.node] = true;
				DFS(now.node, sum + now.dist);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		adNode 	= new Node[N+1];
		int a,b,c;
		for(int n=1; n<N; n++) {
			a 			= read();
			b 			= read();
			c 			= read();
			adNode[a] 	= new Node(b,c,adNode[a]); // 양방향 연결
			adNode[b] 	= new Node(a,c,adNode[b]);
		}
		StringBuilder sb = new StringBuilder();
		for(int m=0; m<M; m++) { // 입력 받을 때마다 DFS를 돌아도 충분
			start 			= read();
			end 			= read();
			dist			= 0;
			visit 			= new boolean[N+1];
			visit[start] 	= true;
			DFS(start,0);
			sb.append(dist).append('\n');
		}
		System.out.println(sb);
	}
}