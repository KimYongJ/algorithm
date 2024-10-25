//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2132
class Node{
	int node; Node next;
	Node(int node, Node next){
		this.node=node; this.next=next;
	}
}
public class Main {
	
	static int N, minNode, maxCost, arr[];
	static Node adNode[];
	static boolean visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		N		= read();
		arr		= new int[N+1];
		adNode	= new Node[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		for(int i=1; i<N; i++)
		{
			int a		= read();
			int b		= read();
			adNode[a]	= new Node(b, adNode[a]);
			adNode[b]	= new Node(a, adNode[b]);
		}
		
		minNode = 10000;
		DFS(1, arr[1], 0);// 1번 노드를 시작으로 모든노드를 탐색하며 최대배이용 기장 큰 노드를 찾는다.
		
		// 1번 노드에서 떨어진, 최대비용이 가장 큰 노드가 minNode에 담김, 다시 이를 통해 최대 cost를 찾으면됨
		// 트리의 지름을 응용한 문제로 단순 지나친 노드 개수가 아니라 큰 비용을 갖는 노드일 때만 노드번호를 저장하는 것이 포인트
		int node	= minNode;
		minNode		= 10000;
		maxCost		= 0;
		DFS(node, arr[node], 0);

		System.out.print(
						new StringBuilder().append(maxCost)
						.append(' ').append( Math.min(node, minNode) )
						);
	}
	public static void DFS(int node, int cost, int beforeNode) {
		if(maxCost < cost)
		{
			maxCost = cost;
			minNode = node;
		}
		else if(maxCost == cost)
			minNode = Math.min(minNode, node);

		for(Node next=adNode[node]; next!=null; next=next.next)
			if(beforeNode != next.node)
				DFS(next.node, cost + arr[next.node], node);
	}

}