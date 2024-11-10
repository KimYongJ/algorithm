//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1131
class Node{
	int node, dist; Node next;Node(int n,int d, Node t){node=n;dist=d; next=t;}
}
class Main{
	
	static int N;
	static long distSum[];
	static int nodeCnt[];
	static Node adNode[];

	public static void main(String[] args)throws Exception{
		N		= read();
		nodeCnt	= new int[N+1];
		adNode	= new Node[N+1];
		distSum = new long[N+1];
		for(int i=1; i<N; i++)
		{
			int a		= read();
			int b		= read();
			int d		= read();
			adNode[a]	= new Node(b, d, adNode[a]);
			adNode[b]	= new Node(a, d, adNode[b]);
		}

		DFS1(1, 0, 0);// 해당 DFS함수에서 1번에서 모든노드로의 총거리 합과, 각 노드당 갖는 자식 개수를 센다
		DFS2(1, 0);

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(distSum[i]).append('\n');
		System.out.print(sb.toString());
	}
	public static long DFS1(int node, int dist, int prevNode) {
		distSum[node] = dist;
		
		nodeCnt[node] = 1;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(next.node != prevNode)
			{
				distSum[node] += DFS1(next.node, dist + next.dist, node);
				nodeCnt[node] += nodeCnt[next.node];
			}
		
		return distSum[node];
	}
	public static void DFS2(int node, int prevNode) {
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(next.node != prevNode)
			{
				long plus			= (N - nodeCnt[next.node]- 1) * next.dist;
				long minus			= (nodeCnt[next.node] - 1)  * next.dist;
				distSum[next.node]	= distSum[node] + plus - minus;

				DFS2(next.node, node);
			}
	}
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}