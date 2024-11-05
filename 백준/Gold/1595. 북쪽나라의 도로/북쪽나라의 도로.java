//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1595
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node, dist; Node next;
	Node(int n, int d, Node nt){node=n; dist=d; next=nt;}
}
class Main{
	
	static final int MAX = 10_001;
	static Node adNode[];
	static int maxNode, maxDist;
	static boolean visit[];
	public static void DFS(int node, int dist) {
		if(maxDist < dist) {
			maxDist = dist;
			maxNode = node;
		}
		visit[node] = true;
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
				DFS(next.node, dist + next.dist);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		adNode = new Node[MAX];
		while(true)
		{
			String str = br.readLine();
			if(str == null || "".equals(str))
				break;
			StringTokenizer st = new StringTokenizer(str);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b,d,adNode[a]);
			adNode[b] = new Node(a,d,adNode[b]);
		}
		visit = new boolean[MAX];
		DFS(1, 0);
		
		maxDist = 0;
		visit = new boolean[MAX];
		DFS(maxNode, 0);
		
		System.out.print(maxDist);
	}
}