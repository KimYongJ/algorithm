//https://www.acmicpc.net/problem/12004
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int parent[];
	static DSU dsu;
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dsu = new DSU(N);
		parent = new int[N + 1];
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
		{
			adList[i] = new ArrayList<>();
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		boolean isOpen[] = new boolean[N + 1];
		boolean res[] = new boolean[N + 1];
		int closeOrder[] = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			closeOrder[i] = Integer.parseInt(br.readLine());
		
		int component = 0;
		for(int i=N; i>=1; i--)
		{
			int num = closeOrder[i];
			isOpen[num] = true;
			
			component++;
			
			for(int next : adList[num])
				if(isOpen[next] && dsu.union(num, next))
					component--;
			
			res[i] = component == 1;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(res[i] ? "YES" : "NO").append('\n');
		
		System.out.print(sb);
	}
	static class DSU{
		
		int N;
		int parent[];
		
		DSU(int N){
			this.N = N;
			this.parent = new int[N + 1];
			init();
		}
		void init() {
			for(int i=0; i<N; i++)
				parent[i] = i;
		}
		int find(int node) {
			if(parent[node] == node) return node;
			return parent[node] = find(parent[node]);
		}
		boolean union(int a, int b) {
			int p1 = find(a);
			int p2 = find(b);
			
			if(p1 == p2)
				return false;
			
			if(parent[p1] < parent[p2])
				parent[p2] = p1;
			else
				parent[p1] = p2;
			
			return true;
		}
	}
}