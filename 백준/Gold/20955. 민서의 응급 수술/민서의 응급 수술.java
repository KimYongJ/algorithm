//https://www.acmicpc.net/problem/20955
//4 2// 뉴런 개수(2<=100,000), 시냅스 개수(1<=2,100,000)
//1 2// 시냅스 개수만큼 연결이 주어짐
//3 4
//답 : 1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int[] parent;
	static int[] rank;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		rank = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		int cnt = 0;
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int parent1 = find(Integer.parseInt(st.nextToken()));
			int parent2 = find(Integer.parseInt(st.nextToken()));
			
			if(parent1 == parent2)
			{
				++cnt;// 이미 연결된 경우는 그 시냅스 연결을 끊는다.
				continue;
			}
			
			if(rank[parent1] < rank[parent2])
				parent[parent1] = parent2;
			else if(rank[parent1] > rank[parent2])
				parent[parent2] = parent1;
			else
			{
				parent[parent1] = parent2;
				rank[parent2]++;
			}
		}
		
		for(int i=1; i<=N; i++)
			if(parent[i] == i)
				++cnt;// 루트 노드일 때 시냅스 연결을 해야 하므로 플러스처리
		
		// 원래 트리가 한개 포함되어 있으므로 출력시 값 보정 처리
		System.out.print(cnt - 1);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}