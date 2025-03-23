//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1368
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node implements Comparable<Node>{
	int a,b,c;
	Node(int a, int b, int c){
		this.a=a; 
		this.b=b; 
		this.c=c;
	}
	@Override
	public int compareTo(Node o) {
		return this.c - o.c;
	}
}
class Main{
	
	static int[] parent;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			pq.add(new Node(i-1,N, Integer.parseInt(br.readLine())));
			parent[i] = i;
		}
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
			{
				int v = Integer.parseInt(st.nextToken());
				if(i != j)
					pq.add(new Node(i, j, v));
			}
		}
		
		int result = 0;
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			int p1 = getParent(now.a);
			int p2 = getParent(now.b);
			if(p1 != p2)
			{
				if(p1 < p2)
					parent[p2] = p1;
				else
					parent[p1] = p2;
				
				result += now.c;
			}
		}
		System.out.print(result);
	}
	public static int getParent(int node) {
		if(parent[node] == node)
			return node;
		return parent[node] = getParent(parent[node]);
	}
}