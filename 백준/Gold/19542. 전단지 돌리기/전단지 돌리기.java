// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int a,b, N, S, D, dist, dp[];
	static Node adNode[];
	static boolean visit[];
	public static int filldp(int now) {
		if(visit[now]) 
			return 0;
		visit[now] = true;
		
		int max = 0;
		for(Node n=adNode[now]; n!=null; n=n.next)
			if(!visit[n.node])
				max = Math.max(filldp(n.node),max);

		return dp[now] += max + 1;
	}
	public static void DFS(int now) {
		if(visit[now]) return;
		visit[now] = true;
		
		for(Node n=adNode[now]; n!=null; n=n.next)
			if(!visit[n.node] && dp[n.node]-1 >= D) // 노드 탐색시 들어가야만 할 때 들어간다.
				DFS(n.node);

		dist += 2;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		S 		= Integer.parseInt(st.nextToken());
		D 		= Integer.parseInt(st.nextToken());
		dp		= new int[N+1];
		adNode 	= new Node[N+1];
		
		
		for(int i=1; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken( ));
			b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		visit = new boolean[N+1];
		filldp(S); // 각 노드당 자신이 갖고 있는 가장 깊은 노드까지 거리를 담는다.
		
		visit = new boolean[N+1];
		DFS(S);
		
		System.out.println(dist - 2);
	}
}