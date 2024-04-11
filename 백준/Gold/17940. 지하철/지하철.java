// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
	int node, trans, dist;
	Node(int node, int dist){ this.node=node;	this.dist=dist;	}
	Node(int node, int trans, int dist){
		this.node=node; this.trans=trans;
		this.dist=dist;
	}
}
class Main{
	static final int MAX = 100_000_000;
	static int N, END;
	static int trans[], dist[];
	static boolean[] company;
	static ArrayList<Node>[] adlist;
	static PriorityQueue<Node> pq;
	public static void Dijkstra() {
		trans[0] = dist[0] = 0;
		pq.add(new Node(0,0,0));
		int nextTrans, nextDist;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			for(Node next : adlist[now.node]) {
				nextTrans = now.trans + (company[now.node] == company[next.node] ? 0 : 1);
				nextDist = now.dist + next.dist;
				if(trans[next.node] == nextTrans) {
					if(dist[next.node]> nextDist) {
						dist[next.node] = nextDist; 
						pq.add(new Node(next.node, nextTrans, nextDist));
					}
				}
				if(trans[next.node]> nextTrans) {
					trans[next.node]= nextTrans; 
					pq.add(new Node(next.node, nextTrans, nextDist));
				}
			}
			
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		END 	= Integer.parseInt(st.nextToken());
		trans 	= new int[N];
		dist 	= new int[N];
		company = new boolean[N]; 		// false면 0번회사, true면 1번회사
		adlist	= new ArrayList[N];
		pq		= new PriorityQueue<Node>((a,b)->{
						if(a.trans==b.trans)
							return a.dist-b.dist;
						return a.trans-b.trans;
					});
		
		for(int i=0; i<N; i++) 
		{
			trans[i] = dist[i] = MAX;
			adlist[i] = new ArrayList<>();
		}
		for(int i=0; i<N; i++)
			company[i] = Integer.parseInt(br.readLine()) == 1;
		
		int num;
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
			{
				num = Integer.parseInt(st.nextToken());
				if(num > 0) {
					adlist[i].add(new Node(j,num));
					adlist[j].add(new Node(i,num));
				}
			}
		}
		Dijkstra();
		System.out.print(
							new StringBuilder()
							.append(trans[END])
							.append(' ')
							.append(dist[END])
							.toString()
						);
		
	}
}
