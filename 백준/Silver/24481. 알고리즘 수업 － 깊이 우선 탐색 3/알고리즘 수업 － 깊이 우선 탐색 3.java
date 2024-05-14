// https://github.com/KimYongJ/algorithm
import java.util.PriorityQueue;
class Main{
	static int order[];
	static boolean visit[];
	static PriorityQueue<Integer>[] adList;
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node, int depth) {
		if(visit[node]) return;
		
		visit[node] = true;
		order[node] = depth;
		
		while(!adList[node].isEmpty())
			DFS(adList[node].poll(), depth+1);
	}
	public static void main(String[] args)throws Exception{
		int N = read();
		int M = read();
		int R = read();
		int a,b;
		
		order	= new int[N+1];
		visit	= new boolean[N+1];
		adList 	= new PriorityQueue[N+1];
		
		for(int i=0; i<=N; i++) 
		{
			order[i] 	= -1;
			adList[i] 	= new PriorityQueue<>();
		}
		
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			adList[a].add(b);
			adList[b].add(a);
		}
		
		DFS(R, 0);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(order[i]).append('\n');

		System.out.print(sb);
	}
}