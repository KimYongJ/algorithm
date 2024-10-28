//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/7535
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static Node adNode[];
	static int color[];
	static boolean flag;
	
	public static boolean DFS(int node, int f) {
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(color[next.node] == 0)
			{ 
				if(!DFS(next.node, color[next.node] = f==1 ? 2 : 1))
					return false;
			}
			else if(color[next.node] == f)
				return false;
		
		return true;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N	= Integer.parseInt(st.nextToken());	// 노드 수(1<=이천)
			int L	= Integer.parseInt(st.nextToken());	// 상호작용 수(1<=백만)
			adNode	= new Node[N+1];	// 연결노드
			color	= new int[N+1];		// 해당 값
			flag	= true;
			while(L-->0)
			{
				st = new StringTokenizer(br.readLine());
				int a		= Integer.parseInt(st.nextToken());
				int b		= Integer.parseInt(st.nextToken());
				adNode[a]	= new Node(b, adNode[a]);
				adNode[b]	= new Node(a, adNode[b]);
			}			
			for(int n=1; n<=N&& flag; n++)
				if(color[n] == 0)
				{
					color[n] = 1;
					flag = DFS(n, 1);
				}
			sb.append("Scenario #").append(i).append(':').append('\n');
			if( flag )
				sb.append("No suspicious bugs found!");
			else
				sb.append("Suspicious bugs found!");

			sb.append('\n').append('\n');
		}
		System.out.print(sb.toString());
	}
}