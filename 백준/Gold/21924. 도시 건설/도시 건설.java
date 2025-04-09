//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/21924
//1초 512MB
import java.util.PriorityQueue;

class Main{
	
	static int parent[];
	
	public static int getParent(int node) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node]);
	}
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		int N = in.nextInt(); // 건물개수 3<=십만
		int M = in.nextInt(); // 도로개수 2<=min( N*(N-1)/2 , 십만) 
		parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		long total = 0;
		while(M-->0)
		{
			int a = in.nextInt();	// 건물번호 1<=N
			int b = in.nextInt();	// 건물번호 1<=N
			int c = in.nextInt();	// 1<=백만
			pq.add(new Node(a,b,c));
			total += c;
		}
		
		long ans = 0;
		int cnt = 1;
		
		while(cnt < N)
		{
			if(pq.isEmpty())
			{
				System.out.print(-1);
				return;
			}
			Node now = pq.poll();
			int p1 = getParent(now.a);
			int p2 = getParent(now.b);
			if(p1 != p2)
			{
				if(p1 < p2)
					parent[p2] = p1;
				else
					parent[p1] = p2;
				
				ans += now.cost;
				cnt += 1;
			}
		}
		
		System.out.print(total - ans);
	}
}
class Node implements Comparable<Node>{
	int a, b, cost;
	Node(int a, int b, int cost){
		this.a=a;
		this.b=b;
		this.cost=cost;
	}
	@Override
	public int compareTo(Node o) {
		return cost - o.cost;
	}
}
class Reader {
	final int SIZE = 1 << 15;
	byte[] buffer = new byte[SIZE];
	int index, size;

	int nextInt() throws Exception {
		int n = 0;
		byte c;
		while ((c = read()) <= 32)
			;
		boolean neg = c == '-' ? true : false;
		if (neg)
			c = read();
		do
			n = (n << 3) + (n << 1) + (c & 15);
		while (isNumber(c = read()));
		return neg ? -n : n;
	}

	boolean isNumber(byte c) {
		return 47 < c && c < 58;
	}

	byte read() throws Exception {
		if (index == size) {
			size = System.in.read(buffer, index = 0, SIZE);
			if (size < 0)
				buffer[0] = -1;
		}
		return buffer[index++];
	}
}
