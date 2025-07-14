//https://www.acmicpc.net/problem/15835
//3초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int parent[] = new int[51];
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++)
		{
			clear();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			while(M-->0)
			{
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());//경로의 시작 체크포인트와 끝 체크포인트 (1 ≤ a, b ≤ N)
				int b = Integer.parseInt(st.nextToken());//경로의 시작 체크포인트와 끝 체크포인트 (1 ≤ a, b ≤ N)
				int d = Integer.parseInt(st.nextToken());//해당 경로의 거리 (1 ≤ d ≤ 500)
				pq.add(new Node(a,b,d));
			}
			
			int edgeCnt = 1;
			int sum = 0;
			while(!pq.isEmpty() && N != edgeCnt)
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
				
				edgeCnt += 1;
				sum += now.d;
			}
			
			sb.append("Case #").append(i).append(": ")
				.append(sum).append(" meters\n");
		}
		System.out.print(sb);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	static void clear() {
		pq.clear();
		for(int i=0; i<parent.length; i++)
			parent[i] = i;
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
			return d - o.d;
		}
	}
}