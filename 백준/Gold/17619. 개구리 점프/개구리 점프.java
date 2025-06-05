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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main{
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		int N = in.nextInt();// 통나무 개수(1 ≤ N ≤ 100,000)
		int Q = in.nextInt();// 질문 개수(1 ≤ Q ≤ 100,000)
		
		UnionFind uf = new UnionFind(N);
		List<Query> point = new ArrayList<>();
		
		for(int i=1; i<=N; i++)
		{
			int s = in.nextInt();
			int e = in.nextInt();
			in.nextInt(); // Y값은 필요 없으므로 그냥 버린다.
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
			int n1 = in.nextInt();
			int n2 = in.nextInt();
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
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;

	    int nextInt() throws Exception {
	        int n = 0;
	        byte c;
	        boolean isMinus = false;
	        while ((c = read()) <= 32) { if (size < 0) return -1; }
	        if (c == 45) { c = read(); isMinus = true; }
	        do n = (n << 3) + (n << 1) + (c & 15);
	        while (isNumber(c = read()));
	        return isMinus ? ~n + 1 : n;
	    }

	    boolean isNumber(byte c) {
	        return 47 < c && c < 58;
	    }

	    byte read() throws Exception {
	        if (index == size) {
	            size = System.in.read(buffer, index = 0, SIZE);
	            if (size < 0) buffer[0] = -1;
	        }
	        return buffer[index++];
	    }
	}
}