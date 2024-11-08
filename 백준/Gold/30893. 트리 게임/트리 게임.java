//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/30893
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int n, Node t){node=n; next=t;}
}
class Main{
	
	static int		N, S, E;
	static Node		adNode[];
	static boolean	visit[];
	
	public static boolean DFS(int node, int flag)
	{
		if(node == E)
			return true;

		int childCnt = 0;
		boolean bool = false;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				++childCnt;
				visit[next.node] = true;
				bool |= DFS(next.node, 1-flag);
			} 
		
		// 방문 순서가 홀수이고, 자식 노드가 
		if(flag == 1 && childCnt > 1)	// 자식 노드가 없다면 무조건 false
			return false;
		else return bool;
		// flag가 0일 때 First, 1일 때 Second여야함
//		if(bool)
//			return flag == 0;
//		else
//			return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 정점개수(2<=십만)
		S		= Integer.parseInt(st.nextToken());
		E		= Integer.parseInt(st.nextToken());
		adNode	= new Node[N+1];
		visit	= new boolean[N+1];
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		visit[S] = true;
		
		if(DFS(S,0))
			System.out.print("First");
		else
			System.out.print("Second");
	}
}