//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9576
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{	int a, b;	Node(int a, int b){this.a=a; this.b=b;} }
class Main{
	public static int find(int parent[], int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent, parent[x]);
	}
	public static void union(int parent[], int x, int y) {
		parent[x] = find(parent, y);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.b != b.b ? a.b-b.b : a.a-b.a);
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 총 책의 수
			int M = Integer.parseInt(st.nextToken()); // 학생 수
			int cnt = 0;
			int parent[] = new int[N+2];
			for(int i=1; i<N+2; i++)
				parent[i] = i;
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				pq.add(new Node(a,b));
			}
			
			while(!pq.isEmpty()) 
			{
				Node now = pq.poll();
				int x = find(parent, now.a);
				if(x <= now.b)
				{
					cnt++;
					union(parent, x, x+1);
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());

	}
}