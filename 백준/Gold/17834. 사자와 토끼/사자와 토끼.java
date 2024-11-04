//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17834
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int[] colorCnt, color;
	static Node adNode[];
	public static void DFS(int node, int col) {
		++colorCnt[col];
		color[node] = col;
		for(Node next=adNode[node]; next!=null; next=next.next) {
			if(color[next.node] == 0) {
				DFS(next.node, col == 1 ? 2 : 1);
			}else if(color[next.node] == col) {
				System.out.print(0);
				System.exit(0);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N	= Integer.parseInt(st.nextToken());	// 노드 수 (2 ≤ N ≤ 50,000)
		int M	= Integer.parseInt(st.nextToken());	// 간선 수 (1 ≤ M ≤ 500,000)
		adNode	= new Node[N+1];
		color	= new int[N+1];
		colorCnt= new int[3];
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);			
		}
		
		DFS(1, 1);
		
		System.out.print((colorCnt[1] * colorCnt[2]) << 1);
	}
}