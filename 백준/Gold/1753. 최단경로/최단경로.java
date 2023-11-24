// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		final int INF = 200_001;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		ArrayList<Node>[] list = new ArrayList[N+1];
		for(int i=1; i<=N; i++)
			list[i] = new ArrayList<Node>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,d));	
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		pq.add(new Node(start,0));
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int nowNode = node.node;
			int nowDist = node.dist;
			for(int i=0; i<list[nowNode].size(); i++) {
				int nextNode = list[nowNode].get(i).node;
				int nextDist = list[nowNode].get(i).dist;
				
				if(dist[nextNode] > nowDist + nextDist) {
					dist[nextNode] = nowDist + nextDist;
					pq.add(new Node(nextNode,nowDist + nextDist));
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			if(dist[i]==INF) sb.append("INF").append('\n');
			else sb.append(dist[i]).append('\n');
		}
		System.out.print(sb);
	}
}
class Node{
	int node, dist;
	Node(int node, int dist){
		this.node = node;
		this.dist = dist;
	}
}