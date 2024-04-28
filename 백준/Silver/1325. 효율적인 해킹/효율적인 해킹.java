// https://github.com/KimYongJ/algorithm

import java.util.ArrayList;

class Node{
	int node;
	Node child;
	Node(int node, Node child){this.node = node; this.child = child;}
}

class Main{
	static int N, M, max, arr[];
	static boolean visit[];
	static ArrayList<Integer> adlist[];
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void DFS(int node) {
		visit[node] = true;
		for(int next : adlist[node])
			if(!visit[next]) 
			{
				arr[next]++;
				DFS(next);				
			}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		arr 	= new int[N+1];
		adlist 	= new ArrayList[N+1];
		
		for(int i=0; i<=N; i++) adlist[i] = new ArrayList<>();
		int a,b;
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			adlist[a].add(b);
		}
		
		
		for(int i=1; i<=N; i++) {
			visit = new boolean[N+1];
			DFS(i);
		}
		for(int i=1; i<=N; i++) {
			if(arr[i] > max)
				max = arr[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			if(max == arr[i])
				sb.append(i).append(' ');
		

		System.out.println(sb);		
	}
}
