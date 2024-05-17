// https://github.com/kimyongj/algorithm

class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int 	node, edge, a, b, T, type[];
	static Node adNode[];
	static boolean result;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	
	public static boolean DFS(int now,int flag) {
		if(type[now]!=0) return true;
		type[now] = flag;
		for(Node n=adNode[now]; n!=null; n=n.next) {
			if(type[n.node]== flag)
				return result=false;
			if(!DFS(n.node, -flag))
				return false;
		}
		return true;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder	sb = new StringBuilder();
		T = read();
		while(T-->0) {
			node = read();
			edge = read();
			
			type	= new int[node+1];
			adNode	= new Node[node+1];
			
			for(int e=0; e<edge; e++) 
			{
				a = read();
				b = read();
				adNode[a] = new Node(b, adNode[a]);
				adNode[b] = new Node(a, adNode[b]);
			}
			result = true;
			
			for(int n=1; n<=node; n++)
				if(type[n] == 0 && !DFS(n,1))
					break;
			
			if(result)	sb.append("possible");
			else 		sb.append("impossible");
			sb.append('\n');			
		}
		System.out.print(sb);
	}
}