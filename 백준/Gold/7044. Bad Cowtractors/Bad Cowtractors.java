//https://www.acmicpc.net/problem/7044
//1초 128MB
//5 8// 노드수(2<=1,000), 간선수(1<=20,000)
//1 2 3// 간선 수만큼 두노드와 비용이 주어짐
//1 3 7
//2 3 10
//2 4 4
//2 5 8
//3 4 6
//3 5 2
//4 5 17
//답(불가능할 경우 -1 출력) : 42
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Main{
	
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int N = Integer.parseInt(st.nextToken());// 노드수(2<=1,000)
		int M = Integer.parseInt(st.nextToken());// 간선수(1<=20,000)
		int parent[] = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Node(a,b,c));
		}
		
		int edgeCnt = 1;
		int distSum = 0;
		
		while(!pq.isEmpty() && edgeCnt != N)
		{
			Node now = pq.poll();
			if(union(now.n1, now.n2, parent))
			{
				edgeCnt += 1;
				distSum += now.dist;
			}
		}
		
		System.out.print(edgeCnt == N ? distSum : -1);
	}
	static boolean union(int n1, int n2, int[] parent) {
		int p1 = find(n1, parent);
		int p2 = find(n2, parent);
		
		if(p1 == p2)
			return false;
		
		if(parent[p1] < parent[p2])
			parent[p2] = p1;
		else
			parent[p1] = p2;
		
		return true;
	}
	static int find(int node, int [] parent) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node], parent);
	}
	static class Node implements Comparable<Node>{
		int n1, n2, dist;
		Node(int n1, int n2, int d){
			this.n1=n1;
			this.n2=n2;
			this.dist = d;
		}
		@Override
		public int compareTo(Node o) {
			return o.dist - dist;
		}
	}
}