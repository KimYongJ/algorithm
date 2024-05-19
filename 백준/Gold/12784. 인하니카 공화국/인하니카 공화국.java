// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node, dist;
	Node next;
	Node(int node, int dist, Node next){this.node=node; this.dist=dist; this.next=next;}
}
class Main{
	
	static int a, b, d, T, N, M, sum;
	static boolean visit[];
	static Node adNode[];
	static StringBuilder sb = new StringBuilder();
	public static int DFS(int now, int parentDist) {
		visit[now] = true;
		
		int sum = 0, cnt = 0;
		for(Node n=adNode[now]; n!=null; n=n.next) {
			if(!visit[n.node]) {
				sum += DFS(n.node, n.dist);
				cnt++;
			}
		}

		if(now == 1) {
			sb.append(sum).append('\n');
		}
		if(cnt == 0) {
			return parentDist;
		}
		return Math.min(sum, parentDist);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			N 		= Integer.parseInt(st.nextToken());
			M 		= Integer.parseInt(st.nextToken());
			adNode 	= new Node[N+1];
			visit	= new boolean[N+1];
			sum		= 0;
			for(int i=0; i<M; i++) 
			{
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				adNode[a] = new Node(b,d,adNode[a]);
				adNode[b] = new Node(a,d,adNode[b]);
			}
			
			DFS(1,0);

		}
		System.out.print(sb);
	}
}