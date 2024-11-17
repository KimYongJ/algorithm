//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1058

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int N, cnt;
	static Node adNode[];
	static boolean visit[], isFriend[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		adNode	= new Node[N];
		isFriend= new boolean[N][N];
		
		for(int y=0; y<N; y++)
		{
			String str = br.readLine();
			for(int x=0; x<N; x++)
				if(str.charAt(x) == 'Y')
				{
					adNode[y] = new Node(x, adNode[y]);
					adNode[x] = new Node(y, adNode[x]);
				}
			
		}
		
		int res = 0;
		for(int i=0; i<N; i++)
		{
			cnt = 0;
			visit = new boolean[N];
			visit[i] = true;
			for(Node next=adNode[i]; next!=null; next=next.next)
				if(!visit[next.node])
				{
					visit[next.node]= true;
					++cnt;
				}
			for(Node next=adNode[i]; next!=null; next=next.next)
				if(visit[next.node])
					DFS(next.node);

			res = Math.max(res, cnt);
		}
		System.out.print(res);
	}
	public static void DFS(int node) {
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				++cnt;
				visit[next.node] = true;
			}
	}
}