//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25516

class Node{
	int node; Node next; Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int N, K, cnt, apple[];
	static Node adNode[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node, int depth) {
		if(K < depth) return;
		
		cnt += apple[node];
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			DFS(next.node, depth + 1);
	}
	public static void main(String[] args)throws Exception{
		N		= read();
		K		= read();
		apple	= new int[N];
		adNode	= new Node[N];
		
		for(int i=1; i<N; i++)
		{
			int p = read();
			int c = read();
			adNode[p] = new Node(c, adNode[p]);
		}
		
		for(int i=0; i<N; i++)
			apple[i] = read();
		
		DFS(0,0);
		
		System.out.print(cnt);
	}
}