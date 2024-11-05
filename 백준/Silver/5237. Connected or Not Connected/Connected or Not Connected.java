//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5237

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static Node adNode[];
	static boolean visit[];
	public static int DFS(int node) {
		int cnt = 1;
		visit[node] = true;
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
				cnt += DFS(next.node);
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N	= Integer.parseInt(st.nextToken());
			int K	= Integer.parseInt(st.nextToken());
			adNode	= new Node[N];
			visit	= new boolean[N];
			
			while(K-->0)
			{
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adNode[a] = new Node(b, adNode[a]);
				adNode[b] = new Node(a, adNode[b]);
			}
			
			if(N == DFS(0))
				sb.append("Connected.");
			else
				sb.append("Not connected.");
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}