//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13237
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int root, N, H[];
	static Node adNode[];
	public static void DFS(int node, int depth) {
		H[node] = depth;
		for(Node next=adNode[node]; next!=null; next=next.next)
			DFS(next.node, depth + 1);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		H		= new int[N+1];
		adNode	= new Node[N+1];
		
		for(int i=1; i<=N; i++)
		{
			int now = Integer.parseInt(br.readLine());
			if(now < 0)
				root = i;
			else
				adNode[now] = new Node(i, adNode[now]);
		}
		
		DFS(root, 0);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(H[i]).append('\n');
		System.out.print(sb.toString());
	}
}