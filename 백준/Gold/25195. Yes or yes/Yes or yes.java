// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node = node;this.next = next;}
}
class Main{
	
	static int 		N, M;
	static boolean 	visit[];
	static Node 	adNode[];
	
	public static boolean DFS(int node) {
		boolean flag = false;
		
		for(Node n=adNode[node]; n!=null; n=n.next) {
			if(!visit[n.node] && DFS(n.node)) 
				return true;
			else
				flag = true;
		}
		
		return !flag;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		M 		= Integer.parseInt(st.nextToken());
		adNode 	= new Node[N+1];
		visit	= new boolean[N+1];
		
		int a, b, n;
		for(int i=0; i<M; i++) 
		{
			st 			= new StringTokenizer(br.readLine());
			a 			= Integer.parseInt(st.nextToken());
			b 			= Integer.parseInt(st.nextToken());
			adNode[a] 	= new Node(b, adNode[a]);
		}
		
		n	= Integer.parseInt(br.readLine());
		st 	= new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++)
			visit[Integer.parseInt(st.nextToken())] = true;
		
		String str = "Yes";
		
		if(!visit[1]) 
			str = DFS(1) ? "yes" : "Yes";
		
		System.out.print(str);
	}
}