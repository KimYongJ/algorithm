// https://github.com/KimYongJ/algorithm

import java.util.ArrayList;
import java.util.PriorityQueue;

class Node{
	int node, flag; long dist;
	Node(int node, long dist){
		this.node = node;
		this.dist = dist;
	}
	Node(int node, long dist, int flag){
		this.node = node;
		this.dist = dist;
		this.flag = flag;
	}
}

class Main{
	static final long MAX = 4_000_000_000L;
	static int N, M, a, b, c, result;
	static long wolfD[][] , foxD[];
	static boolean fvisit[], wvisit[][];
	static ArrayList<Node>[] adlist;
	static PriorityQueue<Node> fpq;
	static PriorityQueue<Node> wpq;
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		wolfD 	= new long[N+1][2];
		foxD 	= new long[N+1];
		adlist 	= new ArrayList[N+1];
		
		for(int i=0; i<=N; i++) 
		{
			wolfD[i][0] = 
			wolfD[i][1] = 
			foxD[i] 	= MAX;
			adlist[i] 	= new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			c = read();
			adlist[a].add(new Node(b,c));
			adlist[b].add(new Node(a,c));
		}
		
		Dijkstra_wolf();
		Dijkstra_fox();
		
		System.out.println(result);
	}
	public static void Dijkstra_wolf() {
		long nextDist; int nflag;
		wpq			= new PriorityQueue<Node>((a,b)-> Long.compare(a.dist,b.dist));
		wvisit		= new boolean[N+1][2];
		wolfD[1][0] = 0;
		wpq.add(new Node(1,0,0));
		
		while(!wpq.isEmpty()) {
			Node now = wpq.poll();
			
			if(!wvisit[now.node][now.flag]) 
			{
				wvisit[now.node][now.flag] = true; 
				nflag = (now.flag+1)%2;
				for(Node next : adlist[now.node]) 
				{ 
					if(!wvisit[next.node][nflag]) {
						nextDist = now.dist + next.dist*(nflag==0 ? 4 : 1);
						if(wolfD[next.node][nflag] > nextDist) {
							wolfD[next.node][nflag] = nextDist;
							wpq.add(new Node(next.node, nextDist, nflag));
						}
					}
				}
			}
		}
	}
	public static void Dijkstra_fox() {
		long nextDist;
		fpq		= new PriorityQueue<Node>((a,b)-> Long.compare(a.dist,b.dist));
		fvisit	= new boolean[N+1];
		foxD[1] = 0;
		fpq.add(new Node(1,0));
		while(!fpq.isEmpty()) {
			Node now = fpq.poll();
			
			
			if(fvisit[now.node]) continue;
			if(Math.min(wolfD[now.node][0],wolfD[now.node][1]) > foxD[now.node]) 
				result++;
			fvisit[now.node] = true;
			
			for(Node next : adlist[now.node]) 
			{
				if(!fvisit[next.node]) 
				{
					nextDist = now.dist + next.dist*2;
					if(foxD[next.node] > nextDist) 
					{
						foxD[next.node]= nextDist;
						fpq.add(new Node(next.node, nextDist));
					}
				}
			}
		}
	}
}