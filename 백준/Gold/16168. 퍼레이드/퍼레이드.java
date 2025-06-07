//https://www.acmicpc.net/problem/16168
//2초 128MB

class Main{
	
	static int cnt;
	static int V, E;
	static int in[];
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		Reader read = new Reader();
		V = read.nextInt();
		E = read.nextInt();
		in = new int[V + 1];
		parent = new int[V + 1];
		
		for(int i=1; i<=V; i++)
			parent[i] = i;
		
		for(int i=1; i<=E; i++)
		{
			int n1 = read.nextInt();
			int n2 = read.nextInt();
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