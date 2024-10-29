//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15511
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static Node adNode[];
	static int color[];
	
	public static boolean DFS(int node, int nowColor) {
		color[node] = nowColor;
		int nextColor = nowColor == 1 ? 2 : 1;
		for(Node next=adNode[node]; next!=null; next=next.next) {
			if(color[next.node] == 0)
			{
				if(!DFS(next.node, nextColor))
					return false;
			}else if(color[next.node] == nowColor)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N	= Integer.parseInt(st.nextToken());	// 직원 수(1<=백만)
		int M	= Integer.parseInt(st.nextToken());	// 기존팀정보(1<=백만)
		adNode	= new Node[N+1];
		color	= new int[N+1];
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		for(int i=1; i<=N; i++)
			if(color[i] == 0)
				if( !DFS(i,1) )
				{
					System.out.print("IMPOSSIBLE");
					return;
				}
		System.out.print("POSSIBLE");
	}
}