// https://github.com/KimYongJ/algorithm

class Node{
	int node;
	Node child;
	Node(int node, Node child){this.node = node; this.child = child;}
}

class Main{
	static int N, M, max, arr[];
	static Node adlist[];
	static boolean visit[];
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void DFS(int node) {
		visit[node] = true;
		for(Node now=adlist[node]; now!=null; now=now.child) {
			if(!visit[now.node]) 
			{
				arr[now.node]++;
				DFS(now.node);				
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N = read();
		M = read();
		arr = new int[N+1];
		adlist = new Node[N+1];
		int a,b;
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			adlist[a] = new Node(b, adlist[a]);
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
