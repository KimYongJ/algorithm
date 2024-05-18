// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;	Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int a, b, T, N, M, cnt;
	static String str;
	static Node adNode[];
	static boolean visit[];
	public static boolean DFS(int now, int before) {
		if(visit[now]) return true;
		visit[now] = true;
		
		cnt++;
		
		for(Node n=adNode[now]; n!=null; n=n.next) {
			if( visit[n.node] && n.node != before ) {
				return false;
			}
			if(!visit[n.node]) {
				if(!DFS(n.node, now))
					return false;
			}
		}

		return true;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder 	sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			cnt		= 0;
			N 		= Integer.parseInt(br.readLine());
			M 		= Integer.parseInt(br.readLine());
			str		= "graph";
			adNode 	= new Node[N+1];
			visit	= new boolean[N+1];
			for(int i=0; i<M; i++) 
			{
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				adNode[a] = new Node(b, adNode[a]);
				adNode[b] = new Node(a, adNode[b]);
			}
			
			if( DFS(1,1) && cnt == N )
				str = "tree";
			
			sb.append(str)
				.append('\n');
		}
		
		System.out.print(sb);
	}
}