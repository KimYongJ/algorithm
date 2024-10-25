//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/1119
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Node{
	int node; Node next;
	Node(int nd, Node nt){node=nd; next=nt;}
}
class Main{
	
	static int nodeCnt;
	static Node adNode[];
	static boolean visit[];

	public static void DFS(int node) {
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				++nodeCnt;
				DFS(next.node);
			}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());
		int totalEdge	= 0;
		adNode			= new Node[N];
		visit			= new boolean[N];
		
		if(N == 1)
		{
			System.out.print(0);
			return;
		}
		
		for(int y=0; y<N; y++)
		{
			String str = br.readLine();
			for(int x=0; x<N; x++) {
				if(str.charAt(x) == 'Y')
				{
					++totalEdge;
					adNode[y] = new Node(x, adNode[y]);
				}
			}
		}
		
		totalEdge /= 2;	// 실제 연결 간선 개수

		int cycleCnt = 0;
		for(int i=0; i<N; i++)
			if(!visit[i])
			{
				cycleCnt++;
				nodeCnt	= 1;
				visit[i]= true;
				DFS(i);
				if(nodeCnt == 1)
				{
					System.out.print(-1);
					return;
				}
				totalEdge -= nodeCnt-1;
			}
		
		int res = -1;
		
		if(cycleCnt-1 <= totalEdge)
			res = cycleCnt-1;
		
		System.out.print(res);
	}
}