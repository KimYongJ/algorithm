//https://www.acmicpc.net/problem/17352
//4// 노드 수 2<=300,000
//1 2// 연결된 두 노드
//1 3
//답 : 1 4

class Main{
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		int N = in.nextInt();
		
		UnionFind uf = new UnionFind(N);
		
		for(int i=2; i<N; i++)
		{
			int n1 = in.nextInt();
			int n2 = in.nextInt();
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
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;
	
	    String nextString() throws Exception {
	        StringBuilder sb = new StringBuilder();
	        byte c;
	        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
	        do sb.appendCodePoint(c);
	        while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
	        return sb.toString();
	    }
	
	    char nextChar() throws Exception {
	        byte c;
	        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
	        return (char)c;
	    }
	    
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
	
	    long nextLong() throws Exception {
	        long n = 0;
	        byte c;
	        boolean isMinus = false;
	        while ((c = read()) <= 32);
	        if (c == 45) { c = read(); isMinus = true; }
	        do n = (n << 3) + (n << 1) + (c & 15);
	        while (isNumber(c = read()));
	        return isMinus ? ~n + 1 : n;
	    }
	
	    double nextDouble() throws Exception {
	        double n = 0, div = 1;
	        byte c;
	        boolean isMinus = false;
	        while ((c = read()) <= 32) { if (size < 0) return -12345; }
	        if (c == 45) { c = read(); isMinus = true; }
	        else if (c == 46) { c = read(); }
	        do n = (n * 10) + (c & 15);
	        while (isNumber(c = read()));
	        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
	        return isMinus ? -n : n;
	    }
	
	    boolean isNumber(byte c) {
	        return 47 < c && c < 58;
	    }
	
	    boolean isAlphabet(byte c){
	        return (64 < c && c < 91) || (96 < c && c < 123);
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