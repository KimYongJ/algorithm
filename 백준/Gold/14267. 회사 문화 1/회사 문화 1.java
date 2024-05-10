// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node=node;this.next=next;}
}

class Main{
	
	static int N, M, x, y, dp[];
	static Node adNode[];
	public static void DFS(int node) {
		for(Node now=adNode[node]; now!=null; now=now.next) {
			dp[now.node] += dp[node]; 
			DFS(now.node);
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N+1];
		adNode = new Node[N+1];
		
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for(int i=2; i<=N; i++) {
			x = Integer.parseInt(st.nextToken());
			adNode[x] = new Node(i, adNode[x]);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			dp[x] += y;
		}
		DFS(1);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(dp[i]).append(' ');
		System.out.println(sb);
	}
}