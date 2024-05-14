// https://github.com/KimYongJ/algorithm
import java.util.PriorityQueue;

class Main{

	static long 	sum, order;
	static boolean 	visit[];
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
		int N = read();
		int M = read();
		int R = read();
		int a,b;
		
		visit	= new boolean[N+1];
		adList 	= new PriorityQueue[N+1];
		
		for(int i=0; i<=N; i++) 
			adList[i] 	= new PriorityQueue<Integer>((_i__,__i_)->__i_-_i__);
		
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			adList[a].add(b);
			adList[b].add(a);
		}
		
		DFS(R, 0);
		

		System.out.print(sum);
	}
}