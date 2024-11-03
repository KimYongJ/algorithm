//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/26146

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int N, M;
	static Node[] forward, reverse;
	static boolean visit[];
	
	public static int DFS(int node, Node[] adNode) {
		visit[node] = true;
		int cnt = 1;
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
				cnt += DFS(next.node, adNode);
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 노드 개수(1<=이십만)
		M		= Integer.parseInt(st.nextToken());	// 항공편 개수(0<=오십만)
		forward	= new Node[N+1];
		reverse	= new Node[N+1];
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			forward[a] = new Node(b, forward[a]);
			reverse[b] = new Node(a, reverse[b]);
		}
		
		visit		= new boolean[N+1];
		int cnt1	= DFS(1, forward);
		visit		= new boolean[N+1];
		int cnt2 	= DFS(1, reverse);
		
		if(N == cnt1 && cnt1 == cnt2)
			System.out.print("Yes");
		else
			System.out.print("No");
	}
}