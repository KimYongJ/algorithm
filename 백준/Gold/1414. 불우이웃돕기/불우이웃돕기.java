//https://www.acmicpc.net/problem/1414
//3//방 개수
//abc// i번째 렌선이 각 컴퓨터에 연결된 길이가 주어짐
//def
//ghi
//답 : 40

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		DSU dsu = new DSU(N);
		int res = 0;
		
		for(int i=1; i<=N; i++)
		{
			String str = br.readLine();
			for(int j=1; j<=N; j++)
			{
				int len = getNum(str.charAt(j - 1));
				
				if(len == 0)
					continue;
				else if(i == j)
					res += len;
				else
					pq.add(new Node(i, j, len));
			}
		}
		
		int conn = 0;
		
		while(!pq.isEmpty())
		{
			Node n = pq.poll();
			
			if(dsu.union(n.n1, n.n2))
				conn++;
			else
				res += n.dist;
		}
		
		System.out.print(conn != N - 1 ? -1 : res);
	}
	static int getNum(char c)
	{
		if(c == '0')
			return 0;
		
		int res = 0;
		if(c <= 'Z')
			res = (c - 'A') +  27;
		else
			res = (c - 'a') + 1;
		return res;
	}
	static class Node implements Comparable<Node>{
		int n1, n2, dist;
		Node(int n1, int n2, int dist){
			this.n1 = n1;
			this.n2 = n2;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			return dist - o.dist;
		}
	}
	static class DSU
	{
		int N;
		int parent[];
		
		DSU(int N)
		{
			this.N = N;
			this.parent = new int[N + 1];
			for(int i=1; i<=N; i++)
				parent[i] = i;
		}
		
		boolean union(int n1, int n2)
		{
			int p1 = find(n1);
			int p2 = find(n2);
			
			if(p1 == p2)
				return false;
			
			if(parent[p1] < parent[p2])
				parent[p2] = p1;
			else
				parent[p1] = p2;
			
			return true;
		}
		
		int find(int node)
		{
			if(parent[node] == node) return node;
			return parent[node] = find(parent[node]);
		}
		
	}
}
