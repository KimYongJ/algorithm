//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14950
//2초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 도시 수 1<=만
		int M = Integer.parseInt(st.nextToken());// 도로 개수 1<=만
		int T = Integer.parseInt(st.nextToken());// 증가 비용 1<=100
		int parent[] = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());// 노드 번호
			int b = Integer.parseInt(st.nextToken());// 노드 번호
			int cost = Integer.parseInt(st.nextToken());// 비용 1<=만
			pq.add(new Node(a,b,cost));
		}
		
		int cnt = N - 1;
		int ans = 0;
		int plus = 0;
		while(cnt != 0)
		{
			Node now = pq.poll();
			int p1 = getParent(now.a, parent);
			int p2 = getParent(now.b, parent);
			if(p1 != p2)
			{
				if(p1 < p2)
					parent[p2] = p1;
				else
					parent[p1] = p2;
				
				ans += now.cost + plus;
				cnt -= 1;
				plus+= T;
			}
		}
		
		System.out.print(ans);
	}
	public static int getParent(int node, int[] parent) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node], parent);
	}
}
class Node implements Comparable<Node>{
	int a, b;
	int cost;
	Node(int a, int b, int cost){
		this.a=a;
		this.b=b;
		this.cost=cost;
	}
	@Override
	public int compareTo(Node o) {
		return cost - o.cost;
	}
}