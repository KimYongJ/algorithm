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


class Main{
	
	static int T, N, K, Q;
	static int[] parent;
	static int[] rank;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		StringBuilder sb = new StringBuilder();
		T = in.nextInt();
		parent = new int[1_000_002];
		rank = new int[1_000_002];
		for(int t=1; t<=T; t++)
		{
			N = in.nextInt();
			K = in.nextInt();
			
			for(int i=1; i<=N+1; i++)
			{
				parent[i] = i;
				rank[i] = 0;
			}
			
			while(K-->0)
			{
				int parent1 = find(in.nextInt()+1);
				int parent2 = find(in.nextInt()+1);
				
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
			
			Q = in.nextInt();
			sb.append("Scenario ").append(t).append(":\n");
			while(Q-->0)
				sb.append(find(in.nextInt() + 1) == find(in.nextInt() + 1) ? 1 : 0).append('\n');
			
			sb.append('\n');
			
		}
		System.out.print(sb);
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