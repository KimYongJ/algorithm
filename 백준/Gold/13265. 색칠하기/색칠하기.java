// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int 	node, edge, a, b, T, type[];
	static Node adNode[];
	static boolean result;
	public static void DFS(int now,int flag) {
		if(type[now] != 0) return;
		type[now]	= flag;
		for(Node n=adNode[now]; n!=null && result; n=n.next) {
			if(type[n.node]== flag)	result = false;
			else DFS(n.node, -flag);
		}
			
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			node = Integer.parseInt(st.nextToken());
			edge = Integer.parseInt(st.nextToken());
			
			type	= new int[node+1];
			adNode	= new Node[node+1];
			
			for(int e=0; e<edge; e++) 
			{
				st= new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				adNode[a] = new Node(b, adNode[a]);
				adNode[b] = new Node(a, adNode[b]);
			}
			result = true;
			
			for(int n=1; n<=node && result; n++)
				if(type[n] == 0)
					DFS(n,1);
			
			if(result)	sb.append("possible");
			else 		sb.append("impossible");
			sb.append('\n');			
		}
		System.out.print(sb);
	}
}