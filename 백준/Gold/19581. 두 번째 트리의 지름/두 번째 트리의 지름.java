//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19581
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node, dist; Node next;
	Node(int n, int d, Node next){node=n; dist=d; this.next=next;}
}
class Main{
	
	static Node adNode[];
	static int maxNode, maxDist;
	static boolean visit[];

	public static void findMaxNode_DFS(int node, int dist) {
		if(maxDist < dist)
		{
			maxDist = dist;
			maxNode = node;
		}
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				findMaxNode_DFS(next.node, dist + next.dist);
			}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine()); // 노드수(3<=십만)
		adNode	= new Node[N + 1];
		
		for(int i=1; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());// 가중치(1<=이만)
			adNode[a] = new Node(b, d, adNode[a]);
			adNode[b] = new Node(a, d, adNode[b]);
		}
		
		int start, end, dist1, dist2;
		
		visit	 = new boolean[N+1];
		visit[1] = true;
		findMaxNode_DFS(1, 0);
		
		maxDist			= 0;
		start			= maxNode;
		visit			= new boolean[N+1];
		visit[maxNode]	= true;
		findMaxNode_DFS(maxNode, 0);
		end = maxNode;
		
		// start에서 end를 제외하고 구하고
		// end에서 start를 제외하고 구한다.
		visit			= new boolean[N+1];
		maxDist			= 0;
		visit[start]	= visit[end] = true;
		findMaxNode_DFS(start, 0);
		dist1 = maxDist;
		
		visit			= new boolean[N+1];
		maxDist			= 0;
		visit[start]	= visit[end] = true;
		findMaxNode_DFS(end, 0);
		dist2 = maxDist;
		
		System.out.print(Math.max(dist1, dist2));
	}
}