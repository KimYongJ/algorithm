//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1197
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node implements Comparable<Node>{
	int a,b,c;
	Node(int a, int b, int c){
		this.a=a; this.b=b; this.c=c;
	}
	@Override
	public int compareTo(Node o) {
		return c - o.c;
	}
}
class Main{
	
	static int[] parent;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());	// 1<=만
		int E = Integer.parseInt(st.nextToken());	// 1<=십만
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int result = 0;
		
		parent = new int[V + 1];
		for(int i=1; i<=V; i++)
			parent[i] = i;
		
		for(int i=0; i<E; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Node(a,b,c));
		}
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			int p1 = getParent(parent[now.a]);
			int p2 = getParent(parent[now.b]);
			if(p1 != p2) {
				result += now.c;
				if(p1 < p2)
					parent[p2] = p1;
				else
					parent[p1] = p2;
			}
		}
		System.out.print(result);
	}
	public static int getParent(int node) {
		return parent[node] = parent[node] == node ? 
				node : getParent(parent[node]);
	}
}