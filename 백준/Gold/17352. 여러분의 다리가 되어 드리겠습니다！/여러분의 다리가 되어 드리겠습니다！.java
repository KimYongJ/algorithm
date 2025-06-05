//https://www.acmicpc.net/problem/17352
//4// 노드 수 2<=300,000
//1 2// 연결된 두 노드
//1 3
//답 : 1 4
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		UnionFind uf = new UnionFind(N);
		
		for(int i=2; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			uf.union(n1, n2);
		}
		
		System.out.print(uf.printDiff());
		
	}
	static class UnionFind{
		int N;
		int parent[];
		UnionFind(int N){
			this.N = N;
			this.parent = new int[N + 1];
			for(int i=1; i<=N; i++)
				parent[i] = i;
		}
		void union(int node1, int node2) {
			int parent1 = find(node1);
			int parent2 = find(node2);
			if(parent1 != parent2)
			{
				if(parent[parent1] < parent[parent2])
					parent[parent2] = parent1;
				else
					parent[parent1] = parent2;
			}
		}
		int find(int node) {
			if(parent[node] == node) return node;
			return parent[node] = find(parent[node]);
		}
		String printDiff() {
			StringBuilder sb = new StringBuilder();
			for(int i=1; i<=N; i++)
			{
				if(parent[i] == i)
				{
					sb.append(i).append(' ');
				}
			}
			return sb.toString();
		}
	}
}