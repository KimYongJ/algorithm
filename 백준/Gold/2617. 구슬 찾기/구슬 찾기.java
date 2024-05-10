// https://github.com/kimyongj/algorithm
class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node = node; this.next = next;
	}
}
class Main{
	
	static int N, M, cnt, result;
	static boolean visit[];
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void DFS(int node, Node[] adNode) {
		if(cnt >= M) return;
		for(Node now=adNode[node]; now!=null && cnt < M; now=now.next) 
		{
			if(!visit[now.node]) 
			{
				cnt++;
				visit[now.node]= true; 
				DFS(now.node, adNode);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N = read();
		M = read();
		
		Node[] forward = new Node[N+1];
		Node[] reverse = new Node[N+1];
		int a,b;
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			forward[a] = new Node(b, forward[a]); // 정방향
			reverse[b] = new Node(a, reverse[b]); // 역방향
		}
		M = (N+1) / 2; // 중간값
		for(int i=1; i<=N; i++) 
		{
			cnt 		= 0;
			visit 		= new boolean[N+1];
			visit[i] 	= true;
			DFS(i, forward);
			if(cnt < M) 
			{
				cnt 	 = 0;
				visit 	 = new boolean[N+1];
				visit[i] = true;
				DFS(i, reverse);
			}
			if(cnt >= M)
				result++;
		}
		System.out.println(result);
	}
}