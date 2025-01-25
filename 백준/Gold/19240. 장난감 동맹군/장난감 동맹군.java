//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19240
//1.5초 / 256mb
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int now;
	Node next;
	Node(int now, Node next){
		this.now = now; 
		this.next= next;
	}
}
class Main{
	
	static int N,M;
	static int color[];
	static Node adNode[];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N		= Integer.parseInt(st.nextToken());	// 3<=삼십만
			M		= Integer.parseInt(st.nextToken());	// 1<=삼십만
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
			boolean flag = true;
			for(int i=1; i<=N; i++)
				if(color[i] == 0 && DFS(i,1))
				{
					flag = false;
					break;
				}
			
			sb.append(flag ? "YES" : "NO").append('\n');
		}
		System.out.print(sb);
	}
	public static boolean DFS(int node, int flag) {
		color[node] = flag;
		for(Node next=adNode[node]; next!=null; next=next.next) {
			if(color[next.now]== 0)
			{
				if(DFS(next.now, ~flag))
					return true;
			}
			else if(color[next.now] == color[node])
				return true;
		}
		return false;
	}
}