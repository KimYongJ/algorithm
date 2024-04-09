// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int node; long dist;
	Node(int node, long dist){this.node=node; this.dist=dist;}
}
class Main{
	static long MAX = 200_000_000_000L;
	static int N, M;
	static long dist[];
	static boolean visit[];
	static ArrayList<Node>[] adlist;
	static PriorityQueue<Node> pq;
	public static void Dijkstra() {
		long result = -1, nextDist;
		dist[0] = 0;
		pq.add(new Node(0,0));
		
		while(!pq.isEmpty()) 
		{
			Node now = pq.poll();
			if(now.node == N) 
			{
				result = now.dist;
				break;
			}
			if(visit[now.node])continue;
			visit[now.node] = true; 
			
			for(Node next : adlist[now.node]) 
			{
				nextDist = next.dist + now.dist;
				if(dist[next.node]> nextDist) 
				{
					dist[next.node] = nextDist;
					pq.add(new Node(next.node, nextDist));
				}
			}
		}
		System.out.println(result);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		M 		= Integer.parseInt(st.nextToken());
		visit 	= new boolean[N];
		dist 	= new long[N];
		adlist 	= new ArrayList[N];
		pq 		= new PriorityQueue<Node>((a,b)->Long.compare(a.dist,b.dist));
		st 		= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			dist[i] 	= MAX;
			visit[i]	= Integer.parseInt(st.nextToken()) == 1;
			adlist[i] 	= new ArrayList<>();
		}
		int a,b,c;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adlist[a].add(new Node(b,c));
			adlist[b].add(new Node(a,c));
		}
		N--;
		Dijkstra();
	}
}