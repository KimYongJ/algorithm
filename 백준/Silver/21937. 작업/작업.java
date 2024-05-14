// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node=node; this.next=next;
	}
}
class Main{

	static int 			a, b, N, M;
	static Node 		adNode[];
	static boolean 		visit[];
	public static int DFS(int node) {
		if(visit[node]) 
			return 0;
		visit[node] = true;
		
		int result = 1;
		
		for(Node now=adNode[node]; now!=null; now=now.next)
			result += DFS(now.node);
		
		return result;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		M 		= Integer.parseInt(st.nextToken());
		adNode 	= new Node[N+1];
		visit	= new boolean[N+1];

		for(int i=0; i<M; i++) 
		{
			st 			= new StringTokenizer(br.readLine());
			a 			= Integer.parseInt(st.nextToken());
			b 			= Integer.parseInt(st.nextToken());
			adNode[b] 	= new Node(a, adNode[b]);
		}
		System.out.print(  DFS(Integer.parseInt(br.readLine()))-1  );
	}
}