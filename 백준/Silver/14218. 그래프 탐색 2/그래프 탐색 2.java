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
	
	static final int MAX = Integer.MAX_VALUE;
	static int N, M, dist[];
	static Node adNode[];
	static StringBuilder sb = new StringBuilder();

	public static void BFS(int node, int startDist)
	{
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean visit[] = new boolean[N+1];
		visit[node] = true;
		q.add(new int[] {node, startDist});
		while(!q.isEmpty())
		{
			int[] now		= q.poll();
			int nextDist	= now[1] + 1;
			
			for(Node next=adNode[now[0]]; next!=null; next=next.next)
				if(!visit[next.node] && nextDist < dist[next.node])
				{
					visit[next.node] = true;
					dist[next.node] = nextDist;
					q.add(new int[] {next.node, nextDist});
				}

		}
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
		
		Arrays.fill(dist, MAX);
		dist[1] = 0;
		BFS(1, 0);	// 최초 dist 세팅
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
			if(2 <= Math.abs(dist[a] - dist[b]))
			{
				if(dist[a] < dist[b])
				{
					dist[b] = dist[a] + 1;
					BFS(b, dist[b]);
				}
				else
				{
					dist[a] = dist[b] + 1;
					BFS(a, dist[a]);
				}
			}
			for(int i=1; i<=N; i++)
			{
				int num = dist[i];
				if(dist[i] == MAX)
					num = -1;
				sb.append(num).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}