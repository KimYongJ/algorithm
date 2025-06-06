//https://www.acmicpc.net/problem/1765
//2초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int parent[];
	static int enemy[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());// 학생수 (2 ≤ 1000) 
		M = Integer.parseInt(br.readLine());// 인간관계 수(1 ≤ 5000)
		parent = new int[N + 1];
		enemy = new int[N + 1];
		
		for(int i=0; i<=N; i++)
			parent[i] = i;

		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			if(cmd == 'E')
			{
				setEnemy(n1, n2);
				continue;
			}
			
			union(n1, n2);
		}
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
			if(parent[i] == i)
				++cnt;

		System.out.print(cnt);
	}
	static void setEnemy(int n1, int n2) {
		if(enemy[n1] != 0) union(enemy[n1], n2);
		if(enemy[n2] != 0) union(enemy[n2], n1);
		
		enemy[n1] = n2;
		enemy[n2] = n1;
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