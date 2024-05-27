// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node=node; this.next = next;}
}
class Main{
	
	static int N;			// 노드수
	static int dp[]; 		// 1번 노드부터 각 노드로까지의 깊이 저장
	static Node[] adNode;	// 인접노드 저장
	static boolean visit[];	// 방문체크
	// DFS를 통해 자기를 포함한 자기 하위 노드가 몇개있는지 dp배열에 담는다.
	public static int DFS(int node) 
	{
		visit[node] = true;
		
		for(Node n=adNode[node]; n!=null; n=n.next)
			if(!visit[n.node])
				dp[node] += DFS(n.node);
		
		return dp[node] += 1;
	}
	// 주어진 노드개수로 사용되는 총 간선을 구하는 식
	public static long nC2(long n) {
		return n*(n-1) / 2;
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		dp 		= new int[N+1];
		adNode 	= new Node[N+1];
		visit 	= new boolean[N+1];
		
		int a,b;
		for(int i=1; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a 			= Integer.parseInt(st.nextToken());
			b 			= Integer.parseInt(st.nextToken());
			adNode[a] 	= new Node(b, adNode[a]);
			adNode[b] 	= new Node(a, adNode[b]);
		}
		
		DFS(1); // 1번 노드부터 시작하여 자기 하위에 노드가 몇개있는지 넣는다.
		
		long result = 0;
		long totalUsedEdges = nC2(N); // 전체 간선의 사용 개수
		
		for(int i=2; i<=N; i++)
			// 전체 사용간선  -  i노드와 그 하위 노드를 포함한 노드들을 제외한 노드들끼리의 간선 개수 = 해당 간선이 사용된 횟수
			result += totalUsedEdges - nC2(N - dp[i]);  
		
		System.out.print(result);
	}
}