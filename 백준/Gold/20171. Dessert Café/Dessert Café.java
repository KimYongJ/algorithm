//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20171
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int N, K, res;
	static Node adNode[];
	static boolean visit[], apt[];
	
	public static boolean DFS(int node)
	{
		boolean findApt = false;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
		{
			if(!visit[next.node])
			{
				visit[next.node] = true;
				if(DFS(next.node))
					findApt = true;
			}
		}
		if(!findApt && !apt[node])
			return false;
		res++;
		return true;
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 노드 수 (3<=십만)
		K		= Integer.parseInt(st.nextToken());	// 아파트 위치 수(1<=N)
		adNode	= new Node[N+1];
		apt		= new boolean[N+1];
		visit	= new boolean[N+1];
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
					Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		st = new StringTokenizer(br.readLine());
		while(K-->0)
			apt[Integer.parseInt(st.nextToken())] = true;
		
		for(int i=1; i<=N; i++)
			if(apt[i] && !visit[i])	// 방문하지 않았고 아파트가 아닐 때
			{
				visit[i] = true;
				DFS(i);
			}
		
		System.out.print(res);
	}
}