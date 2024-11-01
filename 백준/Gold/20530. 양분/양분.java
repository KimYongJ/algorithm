//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20530
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static Node adNode[];
	static int[] parent, groupKey;
	static boolean findCycle;
	static boolean[] visit, isCycle;
	
	public static void check_cycle_DFS(int nowNode, int rootNode) {
		if(nowNode != rootNode)
		{
			isCycle[nowNode] = true;
			check_cycle_DFS(parent[nowNode],rootNode);
		}
	}
	public static void find_cycle_DFS(int nowNode, int beforeNode) {
		for(Node next=adNode[nowNode]; next!=null && !findCycle; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				parent[next.node] = nowNode; 
				find_cycle_DFS(next.node, nowNode); 
			}
			else if(next.node != beforeNode){
				findCycle = true;
				isCycle[next.node] = true;
				check_cycle_DFS(nowNode, next.node);
			}
	}
	public static void grouping_DFS(int nowNode, int key) {
		for(Node next=adNode[nowNode]; next!=null; next=next.next)
			if(!visit[next.node] && !isCycle[next.node])
			{
				visit[next.node]	= true;
				groupKey[next.node] = key;
				grouping_DFS(next.node, key);
			}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N		= Integer.parseInt(st.nextToken());	// 노드수(1<=이십만)
		int Q		= Integer.parseInt(st.nextToken());	// 쿼리수(1<=이십만)
		adNode		= new Node[N+1];
		visit		= new boolean[N+1];
		isCycle		= new boolean[N+1];
		findCycle	= false;
		groupKey	= new int[N+1];
		parent		= new int[N+1];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		visit[1] = true;
		find_cycle_DFS(1,0);
		
		visit = new boolean[N+1];
		for(int i=1; i<=N; i++)
			if(isCycle[i])
			{
				groupKey[i] = i;
				visit[i] = true;
				grouping_DFS(i, i);
			}
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(groupKey[a] == groupKey[b] ? 1 : 2).append('\n');
		}
		
		System.out.print(sb.toString());
	}
}