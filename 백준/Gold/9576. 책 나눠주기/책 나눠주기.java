//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9576
import java.util.PriorityQueue;
class Node{	int a, b;	Node(int a, int b){this.a=a; this.b=b;} }
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int find(int parent[], int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent, parent[x]);
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0) 
		{
			PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.b != b.b ? a.b-b.b : a.a-b.a);
			int N		= read(); // 총 책의 수
			int M		= read(); // 학생 수
			int cnt		= 0;
			int parent[]= new int[N+2];
			
			for(int i=1; i<N+2; i++)
				parent[i] = i;
			
			for(int i=0; i<M; i++)
				pq.add(new Node(read(),read()));
			
			while(!pq.isEmpty()) 
			{
				Node now = pq.poll();
				int x = find(parent, now.a);
				if(x <= now.b)
				{
					cnt++;
					parent[x] = find(parent, x+1); // 유니온
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());

	}
}