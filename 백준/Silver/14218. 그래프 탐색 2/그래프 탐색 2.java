//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14218
import java.util.ArrayDeque;
import java.util.Arrays;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node;this.next=next;}
}
class Main{
	
	static final int MAX = 1<<30;
	static int N, M, dist[];
	static Node adNode[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void BFS(int node, int startDist)
	{
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean visit[]		= new boolean[N+1];
		visit[node]			= true;
		
		q.add(new int[] {node, startDist});
		
		while(!q.isEmpty())
		{
			int[] now		= q.poll();
			int nextDist	= now[1] + 1;
			
			for(Node next=adNode[now[0]]; next!=null; next=next.next)
				if(!visit[next.node] && nextDist < dist[next.node])
				{
					visit[next.node]= true;
					dist[next.node]	= nextDist;
					q.add(new int[] {next.node, nextDist});
				}

		}
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();
		M		= read();
		adNode	= new Node[N+1];
		dist	= new int[N+1];
		
		while(M-->0)
		{
			int a		= read();
			int b		= read();
			adNode[a]	= new Node(b, adNode[a]);
			adNode[b]	= new Node(a, adNode[b]);
		}
		
		Arrays.fill(dist, MAX);
		dist[1] = 0;
		
		BFS(1, 0);	// 최초 dist 세팅
		
		int T = read();
		while(T-->0)
		{
			
			int a		= read();
			int b		= read();
			adNode[a]	= new Node(b, adNode[a]);
			adNode[b]	= new Node(a, adNode[b]);
			
			if(2 <= Math.abs(dist[a] - dist[b]))	// 절대값 차이가 2이상 나야 경로 갱신을 한다.
			{
				if(dist[a] < dist[b])				// 높은 값을 작은값 + 1로 수정하고 해당 위치부터 거리를 갱신
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
				sb.append(dist[i] == MAX ? -1 : dist[i]).append(' ');
			sb.append('\n');
			
		}
		System.out.print(sb.toString());
	}
}