//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24232
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node, idx, isReverse; // 정방향 : 0 / 역방향 : 1
	Node next;
	Node(int node, int idx, int isReverse, Node next){
		this.node=node; this.idx=idx; this.isReverse=isReverse;
		this.next=next;
	}
}
class Main{
	
	static int N;
	static int reverseCnt[];		// 각 노드당 역방향의 개수를 카운팅할 배열
	static int isReverse[];			// 간선을 최종적으로 뒤집을지 말지 결정하는 배열
	static Node adNode[];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N			= Integer.parseInt(br.readLine());
		adNode		= new Node[N+1];
		isReverse	= new int[N-1];
		reverseCnt	= new int[N+1];
		
		for(int i=0; i<N-1; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, i, 0, adNode[a]);
			adNode[b] = new Node(a, i, 1, adNode[b]);
		}
		// 1번 노드의 역방향 개수를 담는다.
		reverseCnt[1] = reverse_edge_DFS(1, 0);
		// 1번노드의 역방향 개수를 활용해 모든 노드의 역방향 개수를 구한다(내가갈때 정방향이면 상대 노드는 역방향간선이 + 1, 내가 갈 때 역방향이면 상대 노드는 역방향 간선이 -1이된다)
		all_reverse_edge_DFS(1, 0, reverseCnt[1]);
		
		// 역방향 개수가 가장 작은 노드를 찾는다.
		int minNode = 0;
		int minReverseEdge = 1<<30;
		for(int i=1; i<=N; i++)
			if(reverseCnt[i] < minReverseEdge)
			{
				minReverseEdge = reverseCnt[i];
				minNode = i;
			}
		
		// 역방향 개수가 가장 작은 노드를 DFS로 탐색하며 결과를 담는다.
		find_result_DFS(minNode, 0);
		
		StringBuilder sb = new StringBuilder();
		for(int i : isReverse)
			sb.append(i);
		System.out.print(sb.toString());
	}
	public static void all_reverse_edge_DFS(int node, int prev, int cnt) {
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(next.node != prev)
			{
				reverseCnt[next.node] = reverseCnt[node] + (next.isReverse == 0 ? 1 : -1);
				all_reverse_edge_DFS(next.node, node, reverseCnt[next.node]);
			}
	}
	public static int reverse_edge_DFS(int node, int prev) {
		int cnt = 0;
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(next.node != prev)
				cnt += reverse_edge_DFS(next.node, node) + next.isReverse;
		return cnt;
	}
	public static void find_result_DFS(int node, int prev) {
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(next.node != prev)
			{
				isReverse[next.idx] = next.isReverse;	// 역방향이면 next.isReverse가 1이된다. 해당 간선이 역방향이라면 자연스럽게 1이담기게됨
				find_result_DFS(next.node, node);
			}
	}
}