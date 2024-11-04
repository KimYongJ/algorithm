//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15270

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Edge{
	int a,b;
	Edge(int a, int b){this.a=a; this.b=b;}
}
class Main{
	
	static int N, M, max;
	static boolean visit[];
	static Edge edge[];
	public static void DFS(int idx, int sum) {
		max = Math.max(max, sum);
		
		if(idx == M)
			return;

		int a = edge[idx].a;
		int b = edge[idx].b;
		if(!visit[a] && !visit[b])
		{
			visit[a] = visit[b] = true;
			DFS(idx + 1, sum + 2);
			visit[a] = visit[b] = false;
		}
		
		DFS(idx + 1, sum);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 2<=20
		M		= Integer.parseInt(st.nextToken());	// 0<=(N*N - N)/2
		visit	= new boolean[N + 1];
		edge	= new Edge[M + 1];
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edge[i] = new Edge(a,b);
		}
		
		DFS(0, 0);
		
		if(max < N)
			++max;
		System.out.print(max);
	}
}