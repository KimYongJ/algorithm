//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/10678
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node, dist; Node next;
	Node(int n, int d, Node t){node=n; dist=d; next=t;}
}
class Main{
	
	static int N, M;
	static boolean time[][];
	static Node[] adNode1, adNode2;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		M		= Integer.parseInt(st.nextToken());
		adNode1	= new Node[N+1];
		adNode2	= new Node[N+1];
		time	= new boolean[16_001][2];
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a		= Integer.parseInt(st.nextToken());
			int b		= Integer.parseInt(st.nextToken());
			int c		= Integer.parseInt(st.nextToken());
			int d		= Integer.parseInt(st.nextToken());
			adNode1[a]	= new Node(b, c, adNode1[a]);
			adNode2[a]	= new Node(b, d, adNode2[a]);
		}
		
		DFS(1, 0, adNode1, 0);
		DFS(1, 0, adNode2, 1);
		
		for(int i=1; i<16_000; i++)
			if(time[i][0] && time[i][1])
			{
				System.out.print(i);
				return;
			}
		
		System.out.print("IMPOSSIBLE");
	}
	public static void DFS(int node, int dist, Node adNode[], int flag) {
		if(node == N)
		{
			time[dist][flag] = true;
			return;
		}
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(node < next.node)
				DFS(next.node, dist + next.dist, adNode, flag);
	}
}