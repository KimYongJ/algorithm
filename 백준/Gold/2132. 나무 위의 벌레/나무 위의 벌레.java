//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2132
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int node, Node next){
		this.node=node; this.next=next;
	}
}
public class Main {
	
	static int N, minNode, maxCnt, maxCost, arr[];
	static Node adNode[];
	static boolean visit[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		arr		= new int[N+1];
		adNode	= new Node[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		minNode = 10000;
		DFS(1, arr[1], 0);
		
		maxCost = 0;
		
		int node = minNode;
		minNode = 10000;
		DFS(node, arr[node], 0);

		StringBuilder sb = new StringBuilder();
		sb.append(maxCost).append(' ').append( Math.min(node, minNode));
		System.out.print(sb.toString());
	}
	public static void DFS(int node, int cost, int beforeNode) {
		if(maxCost < cost)
		{
			maxCost = cost;
			minNode = node;
		}
		else if(maxCost == cost)
			minNode = Math.min(minNode, node);

		for(Node next=adNode[node]; next!=null; next=next.next)
			if(beforeNode != next.node)
				DFS(next.node, cost + arr[next.node], node);
	}

}