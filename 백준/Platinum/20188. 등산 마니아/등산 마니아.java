// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node=node; this.next = next;
	}
}
class Main{
	
	static int dp[]; 		// 1번 노드부터 각 노드로까지의 깊이 저장
	static int parent[]; 	// 자기의 부모 노드를 저장
	static int result;		// 결과
	static int a, b, N;
	static Node[] adNode;
	static boolean visit[];
	
	public static void DFS(int parentNode, int node,int depth) {
		parent[node]	= parentNode;	// 부모노드 저장
		dp[node] 		= depth;		// 깊이 저장
		visit[node] 	= true;			// 방문처리
		
		for(Node n=adNode[node]; n!=null; n=n.next)
			if(!visit[n.node])
				DFS(node,n.node, depth + 1);
	}
	public static int get(int a, int b) {
		// a노드와 b노드의 첫 번째 공통 부모를 찾는다.
		while(a != b) {
			if(dp[a] >= dp[b]) {
				a = parent[a];
			}else if(dp[a] < dp[b]) {
				b = parent[b];
			}
		}
		return a;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		dp 		= new int[N+1];
		parent 	= new int[N+1];
		visit 	= new boolean[N+1];
		adNode 	= new Node[N+1];
		
		for(int i=1; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a 			= Integer.parseInt(st.nextToken());
			b 			= Integer.parseInt(st.nextToken());
			adNode[a] 	= new Node(b, adNode[a]);
			adNode[b] 	= new Node(a, adNode[b]);
		}
		
		DFS(0,1,0); // dp에 1번노드부터의 거리를 담음
		
		for(int i=N; i>1; i--)
			for(int j=i-1; j>0; j--)
				result += dp[i] + dp[j] - dp[get(i,j)];

		
		System.out.print(result);
	}
}