//https://www.acmicpc.net/problem/1446
//2초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int dist[] = new int[D + 1];
		List<Node> adList[] = new ArrayList[D + 1];

		for(int i=0; i<=D; i++)
		{
			dist[i] = D;
			adList[i] = new ArrayList<>();
			if(i < D)
				adList[i].add(new Node(i+1, 1));
		}
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(b <= D)
				adList[a].add(new Node(b,d));
		}
		
		pq.add(new Node(0, 0));
		dist[0] = 0;
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			
			if(now.d > dist[now.a])
				continue;
			
			for(Node next : adList[now.a])
			{
				int nextDist = dist[now.a] + next.d;
				
				if(nextDist < dist[next.a])
				{
					dist[next.a] = nextDist;
					pq.add(new Node(next.a, nextDist));
				}
			}
		}
		
		
		System.out.print(dist[D]);
	}
	static class Node implements Comparable<Node>{
		int a, d;
		Node(int a, int d){
			this.a=a;
			this.d=d;
		}
		@Override
		public int compareTo(Node o) {
			return d - o.d;
		}
	}
}