//https://www.acmicpc.net/problem/7511
//1초 128MB
//2// 테스트 케이스
//3// 유저 수 1<=1,000,000
//1// 친구 관계 수 1<=100,000
//0 1// 친구 관계
//2// 질의 수
//0 1// 알고싶은 두 노드
//1 2
//5
//3
//0 1
//1 2
//3 4
//2
//0 2
//1 3
//이하 답
//Scenario 1:
//1
//0
//
//Scenario 2:
//1
//0
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int T, N, K, Q;
	static int[] parent;
	static int[] rank;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		parent = new int[1_000_002];
		rank = new int[1_000_002];
		for(int t=1; t<=T; t++)
		{
			N = Integer.parseInt(br.readLine());
			K = Integer.parseInt(br.readLine());
			
			for(int i=1; i<=N+1; i++)
			{
				parent[i] = i;
				rank[i] = 0;
			}
			
			while(K-->0)
			{
				st = new StringTokenizer(br.readLine());
				int parent1 = find(Integer.parseInt(st.nextToken())+1);
				int parent2 = find(Integer.parseInt(st.nextToken())+1);
				
				if(parent1 == parent2)
					continue;
				
				if(rank[parent1] < rank[parent2])
					parent[parent1] = parent2;
				else if(rank[parent1] > rank[parent2])
					parent[parent2] = parent1;
				else {
					parent[parent1] = parent2;
					rank[parent2]++;
				}
			}
			
			Q = Integer.parseInt(br.readLine());
			sb.append("Scenario ").append(t).append(":\n");
			while(Q-->0)
			{
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())+1;
				int b = Integer.parseInt(st.nextToken())+1;
				
				sb.append(find(a) == find(b) ? 1 : 0).append('\n');
			}
			
			sb.append('\n');
			
		}
		System.out.print(sb);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}