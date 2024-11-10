//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18785
import java.util.Arrays;
class Node{int node; Node next; Node(int n, Node t){node=n; next=t;}}

class Main{
	
	static int N;
	static int[] time, origin;
	static Node adNode[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node, int prevNode) {
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(next.node != prevNode)
			{
				++time[next.node];
				DFS(next.node, node);
				time[node] += (12 - time[next.node]);
			}
		
		++time[prevNode];
		
		time[node] %= 12;
	}
	public static void main(String[] args)throws Exception{
		N		= read();
		origin	= new int[N+1];
		adNode	= new Node[N+1];

		for(int i=1; i<=N; i++)
			origin[i] = read();
		
		for(int i=1; i<N; i++)
		{
			int a		= read();
			int b		= read();
			adNode[a]	= new Node(b, adNode[a]);
			adNode[b]	= new Node(a, adNode[b]);
		}
		
		int cnt = 0;
		for(int i=1; i<=N; i++)
		{
			time = Arrays.copyOf(origin, N+1);
			
			DFS(i,0);
			
			if(time[i] == 0 || time[i] == 1)
				cnt++;
		}
		System.out.print(cnt);
	}
}