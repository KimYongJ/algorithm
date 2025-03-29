//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6497
//1초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node implements Comparable<Node>{
	int n1, n2, dist;
	Node(int n1, int n2, int dist){
		this.n1=n1;
		this.n2=n2;
		this.dist = dist;
	}
	@Override
	public int compareTo(Node o) {
		return dist - o.dist;
	}
}
class Main{

	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			PriorityQueue<Node> pq = new PriorityQueue<>();
			int M		= Integer.parseInt(st.nextToken());	// 1<=이십만
			int N		= Integer.parseInt(st.nextToken());	// m-1 <=이십만
			int distSum = 0;
			parent		= new int[N + 1];
			
			if(M == 0)
				break;
			
			for(int i=1; i<=N; i++)
				parent[i] = i;
			
			while(N-->0)
			{
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				
				distSum += dist;
				
				pq.add(new Node(n1,n2,dist));
			}
			
			int sum = 0;
			
			while(!pq.isEmpty())
			{
				Node now = pq.poll();
				int parent1 = getParent(now.n1);
				int parent2 = getParent(now.n2);
				if(parent1 != parent2)
				{
					if(parent1 < parent2)
						parent[parent2] = parent1;
					else
						parent[parent1] = parent2;
					
					sum += now.dist;
				}
			}
			
			sb.append(distSum - sum).append('\n');
		}
		System.out.print(sb);
	}
	public static int getParent(int node) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node]);
	}
}