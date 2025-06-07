//https://www.acmicpc.net/problem/16168
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int cnt;
	static int V, E;
	static int in[];
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		in = new int[V + 1];
		parent = new int[V + 1];
		
		for(int i=1; i<=V; i++)
			parent[i] = i;
		
		for(int i=1; i<=E; i++)
		{
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			in[n1]++;
			in[n2]++;
			union(n1, n2);
		}
		
		int parentCnt = 0;
		int odd = 0;
		
		for(int i=1; i<=V; i++)
		{
			if(parent[i] == i)
				++parentCnt;
			if(in[i] % 2 == 1)
				++odd;
		}
		
		if(parentCnt >= 2 || (odd != 0 && odd != 2))
			System.out.print("NO");
		else
			System.out.print("YES");
	}
	static void union(int n1, int n2) {
		int parent1 = find(n1);
		int parent2 = find(n2);
		
		if(parent1 == parent2)
			return;
		
		if(parent[parent1] < parent[parent2])
			parent[parent2] = parent1;
		else
			parent[parent1] = parent2;
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}