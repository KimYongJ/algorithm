//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/24526
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{ int node; Node next; Node(int nd, Node nt){node=nd; next=nt;} }

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N			= Integer.parseInt(st.nextToken());
		int T			= Integer.parseInt(st.nextToken());
		int indg[]		= new int[N + 1];
		Node adNode[]	= new Node[N + 1];
		
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
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
					++res;
					q.add(next.node);
				}
		}
		
		System.out.print(res);
	}
}