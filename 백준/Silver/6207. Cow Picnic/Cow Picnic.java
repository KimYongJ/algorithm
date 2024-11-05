//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6207

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int K, N, M;
	static int[] cowCnt, nowNode;
	static Node adNode[];
	static boolean visit[];
	public static void DFS(int node) {
		visit[node] = true;
		++cowCnt[node];
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
				DFS(next.node);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K		= Integer.parseInt(st.nextToken());	// 소의 수(1<=100)
		N		= Integer.parseInt(st.nextToken());	// 노드 수(1<=1,000)
		M		= Integer.parseInt(st.nextToken());	// 간선 수(1<=10,000)
		nowNode	= new int[K+1];						// 최초에 소들이 있는 노드번호
		cowCnt	= new int[N+1];						// 각 노드에 도착한 소의 수
		adNode	= new Node[N+1];
		
		for(int i=0; i<K; i++)
			nowNode[i] = Integer.parseInt(br.readLine());
		
		while(M-->0)
		{
			st			= new StringTokenizer(br.readLine());
			int a		= Integer.parseInt(st.nextToken());
			int b		= Integer.parseInt(st.nextToken());
			adNode[a]	= new Node(b, adNode[a]);
		}
		
		for(int node : nowNode) {
			visit = new boolean[N+1];
			DFS(node);
		}
		
		
		int res = 0;
		for(int cnt : cowCnt)
			if(cnt == K)
				++res;
		
		System.out.print(res);
	}
}