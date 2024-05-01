// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Main{
	
	static int N, start, Q, dp[];
	static ArrayList<Integer>[] adlist;
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int DFS(int node) {
		if(dp[node] != -1) 
			return 0;
		
		dp[node] = 1;
		
		for(int next : adlist[node])
			dp[node] += DFS(next);
		
		return dp[node];
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		start 	= read();
		Q 		= read();
		dp		= new int[N+1];
		adlist	= new ArrayList[N+1];
		
		for(int i=0; i<=N; i++) 
		{
			adlist[i] = new ArrayList<>();
			dp[i] = -1;
		}
		
		int a,b;
		for(int i=1; i<N; i++)
		{
			a	= read();
			b	= read();
			adlist[a].add(b);
			adlist[b].add(a);
		}
		
		DFS(start);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<Q; i++)
			sb.append(dp[read()])
				.append('\n');
		System.out.println(sb);
	}
}