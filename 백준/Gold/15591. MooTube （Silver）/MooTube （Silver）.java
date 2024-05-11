// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node, dist;Node next;
	Node(int node, int dist, Node next){
		this.node=node;this.dist=dist;this.next=next;
	}
}
class Main{
	static final int MAX = 1_000_000_000;
	static int K;
	static Node[] adNode;
	static boolean visit[];
	public static int DFS(int node, int beforeDist) {
		int cnt = 0;
		for(Node now=adNode[node]; now!=null; now=now.next) {
			if(!visit[now.node]) {
				visit[now.node] = true;
				if(K <= Math.min(beforeDist, now.dist)) {
					cnt ++ ;
					cnt += DFS(now.node, now.dist);
				}
			}
		}
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		adNode = new Node[N+1];
		int a,b,c;
		for(int i=1; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b,c, adNode[a]);
			adNode[b] = new Node(a,c, adNode[b]);
		}
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0) 
		{
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			visit = new boolean[N+1];
			visit[a] = true;
			sb.append(DFS(a, MAX))
				.append('\n');
		}
		System.out.print(sb);
	}
}