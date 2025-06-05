//https://www.acmicpc.net/problem/17619
//4 2// 통나무 개수(1 ≤ N ≤ 100,000), 질문 개수(1 ≤ Q ≤ 100,000)
//1 5 2// x1, x2, y 좌표
//3 7 4
//7 9 1
//10 13 4
//1 3// 통나무 번호 두개가 주어짐
//1 4
//질의에 따라 이동가능한지 출력
//1
//0
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 통나무 개수(1 ≤ N ≤ 100,000)
		int Q = Integer.parseInt(st.nextToken());// 질문 개수(1 ≤ Q ≤ 100,000)
		UnionFind uf = new UnionFind(N);
		List<Query> point = new ArrayList<>();
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			point.add(new Query(s, e, i));
		}
		
		Collections.sort(point);
		
		int e = point.get(0).e;
		int idx = point.get(0).idx;
		for(int i=1; i<N; i++)
		{
			Query q = point.get(i);
			
			if(q.s <= e)
			{
				uf.union(idx, q.idx);
				e = Math.max(e, q.e);
			}
			else
			{
				e = q.e;
				idx = q.idx;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			sb.append(uf.isConnected(n1, n2)).append('\n');
		}
		
		System.out.print(sb);
	}
	static class UnionFind{
		int N;
		int parent[];
		UnionFind(int N){
			this.N=N;
			this.parent = new int[N + 1];
			for(int i=1; i<=N; i++)
				parent[i] = i;
		}
		int isConnected(int n1, int n2) {
			return find(n1) == find(n2) ? 1 : 0;
		}
		void union(int n1, int n2) {
			int parent1 = find(n1);
			int parent2 = find(n2);
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
	}
	static class Query implements Comparable<Query>{
		int s, e, idx;
		Query(int s, int e, int idx){
			this.s=s;
			this.e=e;
			this.idx=idx;
		}
		@Override
		public int compareTo(Query o) {
			return s - o.s;
		}
	}
}