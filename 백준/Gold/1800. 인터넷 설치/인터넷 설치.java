//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1800
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int node, cost; Node next;
	Node(int node, int cost, Node next){this.node=node;this.cost=cost;this.next=next;}
}
class Node2{
	int node,  K;
	Node2(int node, int K){this.node=node; this.K=K;}
}
class Main{
	public static boolean dijkstra(int maxValue, int N, int K, Node[] adNode) {
		PriorityQueue<Node2> pq = new PriorityQueue<>((a,b)-> a.K - b.K);
		int useK[] = new int[N+1];
		
		Arrays.fill(useK, Integer.MAX_VALUE);
		
		pq.add(new Node2(1, 0));// 처음은 1번 학생이 0개의 K를 사용함
		useK[1] = 0;			// 1번학생은 0개의 K를 사용함
		while(!pq.isEmpty())
		{
			Node2 now = pq.poll();
			if(now.node == N)
				return true;

			for(Node next=adNode[now.node]; next != null; next=next.next)
			{
				if(maxValue < next.cost) {
					if(now.K < K && now.K + 1 < useK[next.node])
					{
						useK[next.node] = now.K + 1; 
						pq.add(new Node2(next.node, now.K + 1));
					}
				}else {
					if(now.K < useK[next.node]) {
						useK[next.node] = now.K; 
						pq.add(new Node2(next.node, now.K));
					}
				}
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N			= Integer.parseInt(st.nextToken());	// 학생수(1<=천)
		int P			= Integer.parseInt(st.nextToken());	// 케이블개수(1<=만)
		int K			= Integer.parseInt(st.nextToken());	// 공짜케이블 개수(0<=N)
		Node adNode[]	= new Node[N+1];
		int s = 0, e = 0;
		for(int i=0; i<P; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b,c,adNode[a]);
			adNode[b] = new Node(a,c,adNode[b]);
			e = Math.max(e, c);
		}
		
		int res = -1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(dijkstra(mid, N, K, adNode))
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		System.out.print(res);
	}
}