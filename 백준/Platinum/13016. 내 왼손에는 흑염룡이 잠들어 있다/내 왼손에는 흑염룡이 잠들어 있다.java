//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13016
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node, dist; Node next;
	Node(int n, int d, Node t){node=n; dist=d; next=t;}
}
class Main{
	
	
	static int N, resDist[];
	static int maxNode, maxDist;
	static Node adNode[];
	static boolean visit[];
	
	public static void DFS(int node, int dist) {
		resDist[node] = Math.max(resDist[node], dist);
		if(maxDist < dist) {
			maxDist = dist;
			maxNode = node;
		}
		for(Node next=adNode[node]; next!=null; next=next.next)
		{
			if(!visit[next.node])
			{
				visit[next.node] = true;
				DFS(next.node, dist + next.dist);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());	// 노드 수(2<=오만)
		adNode	= new Node[N+1];
		resDist	= new int[N+1];
		
		for(int i=1; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());	// 거리(1<=사만)
			adNode[a] = new Node(b, d, adNode[a]);
			adNode[b] = new Node(a, d, adNode[b]);
		}
		
		for(int i=0, start = 1; i<3; i++)
		{
			visit			= new boolean[N+1];
			visit[start]	= true;
			maxDist			= 0;
			
			DFS(start, 0);
			
			start = maxNode;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(resDist[i]).append('\n');
		System.out.print(sb.toString());
	}
}