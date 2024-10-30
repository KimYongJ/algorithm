//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25516

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int N, K, apple[];
	static Node adNode[];
	public static int DFS(int node, int depth) {
		if(K < depth)
			return 0;
		int cnt = apple[node];
		for(Node next=adNode[node]; next!=null; next=next.next)
			cnt += DFS(next.node, depth + 1);
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		K		= Integer.parseInt(st.nextToken());
		apple	= new int[N];
		adNode	= new Node[N];
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adNode[p] = new Node(c, adNode[p]);
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			apple[i] = Integer.parseInt(st.nextToken());
		
		System.out.print(DFS(0,0));
	}
}