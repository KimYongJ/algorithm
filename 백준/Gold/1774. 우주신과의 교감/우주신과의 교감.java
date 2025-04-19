//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1774
//2초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node implements Comparable<Node>{
	int a,b;
	double dist;
	Node(int a, int b, double dist){
		this.a=a;
		this.b=b;
		this.dist=dist;
	}
	@Override
	public int compareTo(Node o) {
		return Double.compare(dist , o.dist);
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N			= Integer.parseInt(st.nextToken());	// 1<=1000
		int M			= Integer.parseInt(st.nextToken());	// 1<=1000
		int cnt			= N - 1;
		int parent[]	= new int[N + 1];
		int data[][]	= new int[N + 1][2];
		
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
			
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int parent1 = getParent(Integer.parseInt(st.nextToken()), parent);
			int parent2 = getParent(Integer.parseInt(st.nextToken()), parent);
			
			if(parent1 != parent2)
			{
				if(parent1 < parent2)
					parent[parent2] = parent1;
				else
					parent[parent1] = parent2;

				cnt -= 1;
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i=1; i<N; i++)
		{
			for(int j=i+1; j<=N; j++)
			{
				double dx = data[i][0] - data[j][0];
				double dy = data[i][1] - data[j][1];
				
				pq.add(new Node(i, j, Math.sqrt(dx * dx + dy * dy)));
			}
		}
		
		double ans = 0;
		
		while(cnt != 0)
		{
			Node now = pq.poll();
			int parent1 = getParent(now.a, parent);
			int parent2 = getParent(now.b, parent);
			
			if(parent1 != parent2)
			{
				if(parent1 < parent2)
					parent[parent2] = parent1;
				else
					parent[parent1] = parent2;

				ans += now.dist;
				cnt	-= 1;
			}
		}
		
		System.out.printf("%.2f", ans);
	}
	public static int getParent(int node, int[] parent) {
		if(parent[node] == node)
			return node;
		
		return parent[node] = getParent(parent[node], parent);
	}
}