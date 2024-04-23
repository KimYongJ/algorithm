// https://github.com/kimyongj/algorithm
class Node{
	int node;
	Node adNode;
	Node(int node, Node adNode){
		this.node	= node;
		this.adNode = adNode;
	}
}
class Main{
	
	static int N, M;
	static Node node[];
	static boolean visit[];
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void DFS(int idx, int cnt) {
		if(cnt == 5) {
			System.out.println(1);
			System.exit(0);
		}
		for(Node now = node[idx]; now != null; now = now.adNode) {
			if(!visit[now.node]) {
				visit[now.node] = true;
				DFS(now.node,cnt+1);
				visit[now.node] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();	// 사람수
		M 		= read();	// 친구 관계 수
		node	= new Node[N];
		visit	= new boolean[N];

		int a,b;
		for(int i=0; i<M; i++) {
			a 		= read();
			b 		= read();
			node[a] = new Node(b, node[a]);
			node[b] = new Node(a, node[b]);
		}
		
		for(int i=0; i<N; i++) {
			visit[i] = true;
			DFS(i,1);
			visit[i] = false;
		}
		System.out.println(0);
	}
}