//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14218
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node;this.next=next;}
}
class Main{
	
	static int N, M, dist[];
	static Node adNode[];
	static StringBuilder sb = new StringBuilder();
	
	public static void BFS() {
		Arrays.fill(dist, -1);
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean visit[] = new boolean[N+1];
		visit[1] = true;
		dist[1] = 0;
		q.add(new int[] {1,0});
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(Node next=adNode[now[0]]; next!=null; next=next.next) {
				if(!visit[next.node]) {
					visit[next.node] = true;
					dist[next.node] = now[1] + 1;
					q.add(new int[] {next.node, now[1] + 1});
				}
			}
		}
		for(int i=1; i<=N; i++)
			sb.append(dist[i]).append(' ');
		sb.append('\n');
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		M		= Integer.parseInt(st.nextToken());
		adNode	= new Node[N+1];
		dist	= new int[N+1];
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
			BFS();
		}
		System.out.print(sb.toString());
	}
}