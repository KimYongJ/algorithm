// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	long dist;
	Node next;
	Node(int node, long dist, Node next){
		this.node=node; this.dist=dist;
		this.next=next;
	}
}
class Main{
	
	static Node adNode[];
	static boolean visit[];
	// DFS는 자식 노드들의 거리가 가장 먼 것을 반환한다.
	public static long DFS(int node, long dist) {
		if(visit[node]) return 0;
		visit[node] = true;
		long sum = 0;
		
		for(Node now=adNode[node]; now!=null; now=now.next) {
			sum = Math.max(sum, DFS(now.node,now.dist));
		}
		
		return dist + sum;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N 	= Integer.parseInt(st.nextToken());
		adNode 	= new Node[N+1];
		visit	= new boolean[N+1];
		
		int a,b,d;
		for(int i=1; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b,d, adNode[a]);
			adNode[b] = new Node(a,d, adNode[b]);
		}
		
		System.out.print( DFS(1,0) );
		
	}
}