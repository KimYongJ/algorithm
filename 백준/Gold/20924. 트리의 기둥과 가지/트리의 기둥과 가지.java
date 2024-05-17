// https://github.com/KimYongJ/algorithm

import java.util.ArrayDeque;

class Node{
	int node, dist;	Node next;
	Node(int node, int dist)			{this.node=node; this.dist=dist;}
	Node(int node, int dist, Node next)	{this.node=node; this.dist=dist; this.next=next;}
}
class Main{
	
	static int 		a, b, c, N, cnt, root, dist1, dist2;
	static Node 	adNode[];
	static boolean 	visit[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int BFS() // 기가 노드 찾기
	{
		ArrayDeque<Node> q = new ArrayDeque<>();
		
		q.add(new Node(root,0));
		
		while(!q.isEmpty() && cnt < 2)  // 큐 데이터가 있으면서, 자식노드 카운팅이 2미만일 때
		{
			Node now = q.poll();

			if(!visit[now.node]) 
			{
				visit[now.node] = true;
				
				dist1 	= now.dist;	// 기둥 길이
				root 	= now.node; // 기둥의 마지막 노드
				cnt 	= 0;
				
				for(Node n=adNode[now.node]; n!=null; n=n.next)
					if(!visit[n.node])
					{
						cnt++;		// 자식 개수 카운팅
						q.add(new Node(n.node, now.dist + n.dist));
					}
			}
		}
		return root;
	}
	public static int DFS(int node, int sum)  // 가장 긴 가지 구하기
	{

		int max = 0;
		
		for(Node n=adNode[node]; n!=null; n=n.next)
			if(!visit[n.node]) 
			{
				visit[n.node] = true;
				max = Math.max( max, DFS(n.node, n.dist) );
			}

		return sum + max;
	}
	public static void main(String[] args)throws Exception{
		N		= read();
		root 	= read();
		visit 	= new boolean[N+1];
		adNode	= new Node[N+1];
		
		for(int i=1; i<N; i++) 
		{
			a 			= read();
			b 			= read();
			c 			= read();
			adNode[a] 	= new Node(b, c, adNode[a]);
			adNode[b] 	= new Node(a, c, adNode[b]);
		}

		dist2 = DFS( BFS(), 0);
		
		System.out.print(
							new StringBuilder().append(dist1).append(' ')
												.append(dist2).toString()
						);
	}
}