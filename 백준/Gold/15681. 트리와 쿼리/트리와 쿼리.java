import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int N, start, Q, dp[];
	static ArrayList<Integer>[] adlist;
	public static int DFS(int node) {
		if(dp[node] != -1) 
			return 0;
		
		dp[node] = 1;
		
		for(int next : adlist[node])
			dp[node] += DFS(next);
		
		return dp[node];
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		start 	= Integer.parseInt(st.nextToken());
		Q 		= Integer.parseInt(st.nextToken());
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
			st	= new StringTokenizer(br.readLine());
			a	= Integer.parseInt(st.nextToken());
			b	= Integer.parseInt(st.nextToken());
			adlist[a].add(b);
			adlist[b].add(a);
		}
		
		DFS(start);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<Q; i++)
			sb.append(dp[Integer.parseInt(br.readLine())])
				.append('\n');
		System.out.println(sb);
	}
}