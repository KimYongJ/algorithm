// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	Node before;
	public Node(int node,Node before) {this.node=node; this.before=before;}
}
class Main{
	
	static int a, b, T, N;
	static boolean visit[];
	static Node adNode[];
	public static int DFS(int node) {
		if(visit[node]) 
			return node;
		visit[node] = true;
		if(adNode[node]!=null) {
			return DFS(adNode[node].node);
		}else
			return -1;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder 	sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		while(T-->0) {
			N 		= Integer.parseInt(br.readLine());
			adNode 	= new Node[N+1];
			visit 	= new boolean[N+1];
			int a,b;
			for(int i=1; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				adNode[b] = new Node(a, adNode[b]);
			}
			st = new StringTokenizer(br.readLine());
			DFS(Integer.parseInt(st.nextToken()));
			sb.append( DFS(Integer.parseInt(st.nextToken())) ).append('\n');
		}
		System.out.println(sb);
	}
	
}