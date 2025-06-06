//https://www.acmicpc.net/problem/20955
//4 2// 뉴런 개수(2<=100,000), 시냅스 개수(1<=2,100,000)
//1 2// 시냅스 개수만큼 연결이 주어짐
//3 4
//답 : 1

class Main{
	
	static int N, M;
	static int[] parent;
	static int[] rank;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		parent = new int[N + 1];
		rank = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		int cnt = 0;
		while(M-->0)
		{
			int parent1 = find(in.nextInt());
			int parent2 = find(in.nextInt());
			
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