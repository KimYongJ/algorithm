//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node;this.next=next;}
}
class Main{
	
	static int N, sum;
	static Node adNode[];
	static boolean visit[];
	public static void DFS(int node, int depth) {
		boolean flag = false;
		for(Node now=adNode[node]; now!=null; now=now.next)
			if(!visit[now.node]) 
			{
				visit[now.node] = true;
				flag = true;
				DFS(now.node,depth+1);
			}
		if(!flag)
			sum += depth;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		adNode 	= new Node[N+1];
		visit 	= new boolean[N+1];
		int a,b;
		
		for(int i=1; i<N; i++) 
		{
			st 			= new StringTokenizer(br.readLine());
			a 			= Integer.parseInt(st.nextToken());
			b 			= Integer.parseInt(st.nextToken());
			adNode[a] 	= new Node(b, adNode[a]);
			adNode[b] 	= new Node(a, adNode[b]);
		}
		visit[1] = true;
		DFS(1, 0);
		System.out.print(sum%2 == 0 ? "No" : "Yes");
	}
}