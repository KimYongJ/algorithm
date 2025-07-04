//https://www.acmicpc.net/problem/3803
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, res;
	static int parent[];
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		parent = new int[51];
		pq = new PriorityQueue<>();
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N == 0)
				break;
			M = Integer.parseInt(st.nextToken());
			res = 0;
			
			if(M == 0)
			{
				sb.append(res).append('\n');
				br.readLine();
				continue;
			}
			
			init();
			
			while(M-- > 0)
			{
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				pq.add(new Node(a,b,c));
			}
			
			int edgeCnt = 1;
			
			while(!pq.isEmpty() && edgeCnt != N)
			{
				Node now = pq.poll();
				
				int p1 = find(now.a);
				int p2 = find(now.b);
				
				if(p1 == p2)
					continue;
				
				if(parent[p1] < parent[p2])
					parent[p2] = p1;
				else
					parent[p1] = p2;
				
				res += now.c;
				edgeCnt += 1;
			}
			sb.append(res).append('\n');
			br.readLine();
		}
		
		System.out.print(sb);
	}
	static void init() {
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		pq.clear();
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	static class Node implements Comparable <Node>{
		int a, b, c;
		Node(int a, int b, int c){
			this.a=a;
			this.b=b;
			this.c=c;
		}
		@Override
		public int compareTo(Node o) {
			return c - o.c;
		}
	}
}