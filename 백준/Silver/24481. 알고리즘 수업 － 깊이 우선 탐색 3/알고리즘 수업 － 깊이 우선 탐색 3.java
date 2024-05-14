// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	static int order[];
	static boolean visit[];
	static PriorityQueue<Integer>[] adList;
	public static void DFS(int node, int depth) {
		if(visit[node]) return;
		visit[node] = true;
		order[node] = depth;
		while(!adList[node].isEmpty()) {
			DFS(adList[node].poll(), depth+1);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int a,b;
		order	= new int[N+1];
		visit	= new boolean[N+1];
		adList 	= new PriorityQueue[N+1];
		
		for(int i=0; i<=N; i++) {
			order[i] = -1;
			adList[i] = new PriorityQueue<>();
		}
		
		for(int i=0; i<M; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
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