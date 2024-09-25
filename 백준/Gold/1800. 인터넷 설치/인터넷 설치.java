//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1800
import java.util.Arrays;
import java.util.PriorityQueue;
class Node{	// 인접 노드 표현
	int node, cost; Node next;	Node(int node, int cost, Node next){this.node=node;this.cost=cost;this.next=next;}
}
class Qnode{// 큐에 들어갈 노드객체
	int node,  K;	Qnode(int node, int K){this.node=node; this.K=K;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean dijkstra(int maxValue, int N, int K, Node[] adNode) {
		PriorityQueue<Qnode> pq = new PriorityQueue<>((a,b)-> a.K - b.K);
		int useK[] = new int[N+1];
		
		Arrays.fill(useK, Integer.MAX_VALUE);
		
		pq.add(new Qnode(1, 0));// 처음은 1번 학생이 0개의 K를 사용함
		useK[1] = 0;			// 1번학생은 0개의 K를 사용함
		while(!pq.isEmpty())
		{
			Qnode now = pq.poll();
			if(now.node == N)
				return true;

			for(Node next=adNode[now.node]; next != null; next=next.next)
			{
				if(maxValue < next.cost)
				{
					if(now.K < K && now.K + 1 < useK[next.node])
					{
						useK[next.node] = now.K + 1; 
						pq.add(new Qnode(next.node, now.K + 1));
					}
				}
				else
				{
					if(now.K < useK[next.node])
					{
						useK[next.node] = now.K; 
						pq.add(new Qnode(next.node, now.K));
					}
				}
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		int N			= read();	// 학생수(1<=천)
		int P			= read();	// 케이블개수(1<=만)
		int K			= read();	// 공짜케이블 개수(0<=N)
		Node adNode[]	= new Node[N+1];
		int s			= 0;
		int e			= 0;
		
		for(int i=0; i<P; i++)
		{
			int a		= read();
			int b		= read();
			int c		= read();
			adNode[a]	= new Node(b,c,adNode[a]);
			adNode[b]	= new Node(a,c,adNode[b]);
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