//https://github.com/KimyongJ/algorithm
//https://www.acmicpc.net/problem/18769
//2초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	static class Node implements Comparable<Node>{
		int n1, n2, value;
		Node(int n1, int n2, int value){
			this.n1=n1;
			this.n2=n2;
			this.value = value;
		}
		@Override
		public int compareTo(Node o) {
			return value - o.value;
		}
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer	st;
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			pq.clear();
			st = new StringTokenizer(br.readLine());
			int R		= Integer.parseInt(st.nextToken());// 2<=500, 행
			int C		= Integer.parseInt(st.nextToken());// 2<=500, 열
			int len		= R * C;
			int edgeCnt = len - 1;
			int ans		= 0;
			
			int parent[] = new int[len + 1];
			
			for(int i=0; i<len; i++)
				parent[i] = i;
			
			// 가로 입력 받기
			for(int i=0; i<R; i++)
			{
				st = new StringTokenizer(br.readLine());
				int node1 = i*C;
				int node2 = node1 + 1;
				for(int j=0; j<C-1; j++)
					pq.add(new Node(node1++, node2++, Integer.parseInt(st.nextToken())));
			}
			// 세로입력받기
			for(int i=0; i<R-1; i++)
			{
				st = new StringTokenizer(br.readLine());
				int node1 = i * C;
				int node2 = node1 + C;
				for(int j=0; j<C; j++)
					pq.add(new Node(node1++, node2++, Integer.parseInt(st.nextToken())));
			}
			
			while(edgeCnt != 0)
			{
				Node now = pq.poll();
				
				int p1 = getParent(now.n1, parent);
				int p2 = getParent(now.n2, parent);
				if(p1 != p2)
				{
					if(p1 < p2)
						parent[p2] = p1;
					else
						parent[p1] = p2;
					
					edgeCnt -= 1;
					ans += now.value;
				}
			}
			sb.append(ans).append('\n');
		}
		System.out.print(sb);
	}
	static int getParent(int node, int [] parent) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node], parent);
	}
}