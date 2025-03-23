//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1368
//2초 128MB
import java.util.PriorityQueue;

class Main{
	
	static int[] parent;
	
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int N	= read();
		parent	= new int[N + 1];
		
		// 스스로 파는 것이 더 이득일 경우를 해결하기 위해, 가상의 노드 하나를 추가해서 처리함
		for(int i=1; i<=N; i++)
		{
			// 가상의 노드 연결
			pq.add(new Node(i-1,N, read()));
			parent[i] = i;
		}
		
		for(int i=0; i<N; i++)
		{
			for(int j=0; j<N; j++)
			{
				int v = read();
				if(i != j)
					pq.add(new Node(i, j, v));
			}
		}
		
		int result = 0;
		int repeat = N;	// N번 반복시 종료
        
		while(0 < repeat)
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
				repeat--;    // 간선 연결시 카운팅
			}
		}
		System.out.print(result);
	}
	public static int getParent(int node) {
		if(parent[node] == node)
			return node;
		return parent[node] = getParent(parent[node]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}

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