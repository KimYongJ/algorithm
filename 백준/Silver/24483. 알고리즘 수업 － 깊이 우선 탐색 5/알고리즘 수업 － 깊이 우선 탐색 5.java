// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{

	static long sum, order;
	static boolean visit[];
	static PriorityQueue<Integer>[] adList;
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node, long depth) {
		if(visit[node]) return;
		
		visit[node] = true;
		sum += ++order * depth;
		
        while (!adList[node].isEmpty())
            DFS(adList[node].poll(), depth + 1);
        
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int a,b;
		
		visit	= new boolean[N+1];
		adList 	= new PriorityQueue[N+1];
		
		for(int i=0; i<=N; i++) 
			adList[i] 	= new PriorityQueue<Integer>();
		
		
		for(int i=0; i<M; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		DFS(R, 0);
		

		System.out.print(sum);
	}
}