//https://www.acmicpc.net/problem/6909
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static int c, r, d;
	static int cnt[];
	static int parent[];
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		c = Integer.parseInt(st.nextToken());//전체 도시의 수
		r = Integer.parseInt(st.nextToken());//도시 사이 도로(다리)의 수
		d = Integer.parseInt(st.nextToken());//목적지 도시의 수
		cnt = new int[c + 1];
		pq = new PriorityQueue<>();
		parent = new int[c + 1];
		
		for(int i=1; i<=c; i++)
			parent[i] = i;
		
		while(r-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			pq.add(new Node(a,b,d));
		}
		
		
		for(int i=0; i<d; i++)
			cnt[Integer.parseInt(br.readLine())] = 1;
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			int p1 = find(now.a);
			int p2 = find(now.b);
			
			if(p1 == p2)
				continue;
			
			if(parent[p1] < parent[p2])
			{
				parent[p2] = p1;
				cnt[p1] += cnt[p2];
			}
			else
			{
				parent[p1] = p2;
				cnt[p2] += cnt[p1];
			}
			
			if(cnt[ find(1) ] == d)
			{
				System.out.print(now.d);
				return;
			}
		}
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	static class Node implements Comparable<Node>{
		int a, b, d;
		Node(int a, int b, int d){
			this.a=a;
			this.b=b;
			this.d=d;
		}
		@Override
		public int compareTo(Node o) {
			return o.d - d;
		}
	}
}