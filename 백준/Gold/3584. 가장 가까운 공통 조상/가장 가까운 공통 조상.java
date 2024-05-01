// https://github.com/KimYongJ/algorithm

class Node{
	int node;
	Node before;
	public Node(int node,Node before) {this.node=node; this.before=before;}
}
class Main{
	
	static boolean visit[];
	static Node adNode[];
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
	public static int DFS(int node){
		if(visit[node]) 
			return node;
		visit[node] = true;
		if(adNode[node]!=null)
			return DFS(adNode[node].node);
		else
			return -1;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb	= new StringBuilder();
		int a,b,N, T 		= read();
		while(T-->0) {
			N 		= read();
			adNode 	= new Node[N+1];
			visit 	= new boolean[N+1];
			for(int i=1; i<N; i++) {
				a = read();
				b = read();
				adNode[b] = new Node(a, adNode[b]);	// 자식 -> 부모로 갈 수 있게 만듦
			}
			DFS( read() );								// DFS를 돌며 도는것들 visit체크 
			sb.append( DFS( read() ) ).append('\n');	// 처음으로 만나는 visit = true인 것이 공통 조상
		}
		System.out.println(sb);
	}
	
}