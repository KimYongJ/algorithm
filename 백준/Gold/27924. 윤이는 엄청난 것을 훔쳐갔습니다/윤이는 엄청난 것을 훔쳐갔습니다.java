//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27924
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static Node adNode[];
	static int dist[];
	static boolean flag, visit[];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		flag	= false;
		dist	= new int[N+1];
		adNode	= new Node[N+1];
		visit	= new boolean[N+1];
		
		Arrays.fill(dist, 200_001);
		
		StringTokenizer st;
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		visit = new boolean[N+1];
		visit[b] = true;
		DFS(b, 0);
		visit = new boolean[N+1];
		visit[c] = true;
		DFS(c, 0);
		visit = new boolean[N+1];
		visit[a] = true;
		result_DFS(a, 0);
		
		if(flag)	System.out.print("YES");
		else		System.out.print("NO");
	}
	public static void result_DFS(int node, int depth) {
		if(flag)
			return;
		
		int edgeCnt = 0;
		for(Node next=adNode[node]; next!=null; next=next.next)
		{
			++edgeCnt;
			if(!visit[next.node])
			{
				visit[next.node] = true;
				result_DFS(next.node, depth + 1);
			}
		}
		if(edgeCnt == 1 && depth < dist[node])
			flag = true;
	}
	public static void DFS(int node, int depth) {
		int edgeCnt = 0;
		for(Node next=adNode[node]; next!=null; next=next.next)
		{
			++edgeCnt;
			if(!visit[next.node])
			{
				visit[next.node] = true;
				DFS(next.node, depth + 1);
			}
		}
		if(edgeCnt == 1)
			dist[node] = Math.min(dist[node], depth);
	}
}