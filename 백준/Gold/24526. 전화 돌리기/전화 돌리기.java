//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/24526
import java.util.ArrayDeque;

class Node{ int node; Node next; Node(int nd, Node nt){node=nd; next=nt;} }

class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N			= read();
		int T			= read();
		int indg[]		= new int[N + 1];
		Node adNode[]	= new Node[N + 1];
		
		while(T-->0)
		{
			int s = read();
			int e = read();
			adNode[e] = new Node(s, adNode[e]);
			++indg[s];
		}
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++)
			if(indg[i] == 0)
				q.add(i);
		
		int res = q.size();
		
		while(!q.isEmpty())
		{
			int now = q.poll();
			for(Node next=adNode[now]; next!=null; next=next.next)
				if(--indg[next.node] == 0)
				{
					res++;
					q.add(next.node);
				}
		}
		System.out.print(res);
	}
}