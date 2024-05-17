// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{
	int node, dist;	Node next;
	Node(int node, int dist)			{this.node=node; this.dist=dist;}
	Node(int node, int dist, Node next)	{this.node=node; this.dist=dist; this.next=next;}
}
class Main{
	
	static int a, b, c, N, cnt, root, dist1, dist2;
	static Node adNode[];
	static boolean visit[];
	public static int BFS() {
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
	public static int DFS(int node, int sum) {

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
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		root 	= Integer.parseInt(st.nextToken());
		visit 	= new boolean[N+1];
		adNode	= new Node[N+1];
		
		for(int i=1; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, c, adNode[a]);
			adNode[b] = new Node(a, c, adNode[b]);
		}

		dist2 = DFS( BFS(), 0);
		
		System.out.print(
							new StringBuilder().append(dist1).append(' ')
												.append(dist2).toString()
						);
	}
}