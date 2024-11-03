//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5938

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int N, M;
	static Node adNode[];
	static boolean visit[];
	
	public static void DFS(int node) {
		visit[node] = true;
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
				DFS(next.node);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		M		= Integer.parseInt(st.nextToken());
		adNode	= new Node[N+1];
		visit	= new boolean[N+1];
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		DFS(1);
		
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for(int i=1; i<=N; i++)
			if(!visit[i])
			{
				sb.append(i).append('\n');
				flag = true;
			}
		
		if(flag)
			System.out.print(sb.toString());
		else 
			System.out.print(0);
	}
}