//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14615
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int n, Node t){node=n; next=t;}
}
class Main{
	
	static Node[] forward, reverse;
	static boolean[] visit, isReachableF, isReachableR;
	
	public static void DFS(int node, Node[] adNode, boolean[] isReach) {
		visit[node] = isReach[node] = true;
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				DFS(next.node, adNode, isReach);
			}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N			= Integer.parseInt(st.nextToken());	// 노드 수 3<=십만
		int M			= Integer.parseInt(st.nextToken());	// 간선수 1<=백만
		forward			= new Node[N+1];					// 정방향 1번 노드에서 모든 노드로 갈 수 있는지
		reverse			= new Node[N+1];					// 역방향 N번 노드에서 모든 노드로 갈 수 있는지
		isReachableF	= new boolean[N+1];					// 1번에서 특정 노드로갈 수 있다면 true
		isReachableR	= new boolean[N+1];					// N번에서 특정 노드로갈 수 있다면 true
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			forward[a] = new Node(b, forward[a]);
			reverse[b] = new Node(a, reverse[b]);
		}
		
		visit = new boolean[N+1];
		DFS(1, forward, isReachableF);
		visit = new boolean[N+1];
		DFS(N, reverse, isReachableR);
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int node = Integer.parseInt(br.readLine());
			if(isReachableF[node] && isReachableR[node])
				sb.append("Defend the CTP");
			else
				sb.append("Destroyed the CTP");
			sb.append('\n');
		}
		System.out.print(sb.toString());		
	}
}