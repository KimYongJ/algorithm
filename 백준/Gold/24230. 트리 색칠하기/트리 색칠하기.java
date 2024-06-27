// https://github.com/kimyongj/algorithm

class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node = node;this.next = next;}
}
class Main{
	
	static int		result;
	static int		N;
	static int		color[];
	static boolean	visit[];
	static Node		adNode[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void marking(int node, int beforeColor) {
		visit[node] = true;
		for(Node now=adNode[node]; now!=null; now=now.next) 
		{
			if(!visit[now.node] && beforeColor == color[now.node]) 
			{ 
				marking(now.node, color[now.node]);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N		= read();
		color	= new int[N+1];
		visit	= new boolean[N+1];
		adNode	= new Node[N+1];

		for(int i=1; i<=N; i++) 
		{
			color[i] = read();
		}
		
		int a,b;
		for(int i=1; i<N; i++) 
		{
			a = read();
			b = read();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}

		for(int node=1; node<=N; node++)
		{
			if(!visit[node] && color[node] != 0) 
			{
				marking(node,color[node]);
				result++;
			}
		}
		
		System.out.print(result);
	}
}