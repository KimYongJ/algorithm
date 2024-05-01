// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Node{
	int node, dist;
	Node(int node, int dist){this.node=node; this.dist=dist;}
}
class Main{
	
	static int N, maxDist, maxNode;
	static boolean visit[];
	static ArrayList<Node>[] adlist;
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void DFS(int now, int sum) {
		if(maxDist < sum) 
		{
			maxDist = sum;
			maxNode = now;
		}
		for(Node next : adlist[now]) 
		{
			if(!visit[next.node]) 
			{
				visit[next.node] = true;
				DFS(next.node, sum + next.dist);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		adlist	= new ArrayList[N+1];
		for(int i=0; i<=N; i++)
			adlist[i] = new ArrayList<>();
		
		int now, next;
		for(int i=1; i<=N; i++) 
		{
			now = read();
			while((next = read()) != -1) 
				adlist[now].add(new Node(next, read()));
		}
		
		visit		= new boolean[N+1];
		visit[1]	= true;
		DFS(1,0);
		
		maxDist 		= 0;
		visit			= new boolean[N+1];
		visit[maxNode] 	= true;
		DFS(maxNode, 0);
		System.out.println(maxDist);
	}
}