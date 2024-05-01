// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node{
	int node, dist;
	Node(int node, int dist){this.node=node; this.dist=dist;}
}
class Main{
	
	static int N, maxDist, maxNode;
	static boolean visit[];
	static ArrayList<Node>[] adlist;
	public static void DFS(int now, int sum) {
		if(maxDist < sum) 
		{
			maxDist = sum;
			maxNode = now;
		}
		for(Node next : adlist[now]) 
		{
			if(!visit[next.node]) 
			{
				visit[next.node] = true;
				DFS(next.node, sum + next.dist);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		adlist	= new ArrayList[N+1];
		for(int i=0; i<=N; i++)
			adlist[i] = new ArrayList<>();
		
		int base, node, dist;
		for(int i=1; i<=N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			base = Integer.parseInt(st.nextToken());
			while(true) 
			{
				node = Integer.parseInt(st.nextToken());
				if(node == -1) break;
				dist = Integer.parseInt(st.nextToken());
				adlist[base].add(new Node(node, dist));
			}
		}
		
		visit		= new boolean[N+1];
		visit[1]	= true;
		DFS(1,0);
		
		maxDist 		= 0;
		visit			= new boolean[N+1];
		visit[maxNode] 	= true;
		DFS(maxNode, 0);
		System.out.println(maxDist);
	}
}