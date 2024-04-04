// https://github.com/kimyongj/algorithm

/** 문제 해설.. 
3 4  0분 12분   24분
5 6  1분 13분   25분....
7 8  2분 14분
2 3  3분 15분
1 5  4분 16분
4 8  5분 17분
1 2  6분 18분
6 7  7분 19분
2 3  8분 20분
7 8  9분 21분
1 2 10분 22분
6 7 11분 23분
 * */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
	int node;
	long dist;
	Node(int node, long dist){
		this.node = node;
		this.dist = dist;
	}
}
class Main{
	
	static final long MAX_VALUE = 100_000_000_000_000L;
	static long nextDist, dist[];
	static int N, M;
	static ArrayList<Node>[] adlist;
	static PriorityQueue<Node> pq;
	public static long Dijkstra() {
		dist[1] = 0;
		pq.add(new Node(1,0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();

			for(Node next : adlist[now.node]) {
				if(now.dist <= next.dist) {
					nextDist = next.dist+1;
				}else {
					nextDist = (long)Math.ceil((double)(now.dist-next.dist)/M)*M + next.dist + 1;
				}
				
				if(dist[next.node] > nextDist) {
					dist[next.node]= nextDist;
					pq.add(new Node(next.node, nextDist));
				}
				
			}
			
		}
		
		return dist[N];
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		M 		= Integer.parseInt(st.nextToken());
		dist 	= new long[N+1];
		adlist 	= new ArrayList[N+1];
		pq		= new PriorityQueue<>((a,b)->Long.compare(a.dist, b.dist));
		for(int i=0; i<=N; i++) {
			dist[i] 	= MAX_VALUE;
			adlist[i] 	= new ArrayList<>();
		}
		int a,b;
		for(int i=0; i<M; i++) {
			st	= new StringTokenizer(br.readLine());
			a 	= Integer.parseInt(st.nextToken());
			b 	= Integer.parseInt(st.nextToken());
			adlist[a].add(new Node(b,i));
			adlist[b].add(new Node(a,i));
		}
		
		System.out.println( Dijkstra() );
	}
}