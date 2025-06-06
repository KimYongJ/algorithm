//https://www.acmicpc.net/problem/24391
//2초 256MB

class Main{
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		int N = in.nextInt();
		int M = in.nextInt();
		int parent[] = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(M-->0)
		{
			int parent1 = find(in.nextInt(), parent);
			int parent2 = find(in.nextInt(), parent);
			if(parent1 == parent2)
				continue;
			
			if(parent[parent1] < parent[parent2])
				parent[parent2] = parent1;
			else
				parent[parent1] = parent2;
		}
		
		int prevNode = 0;
		int result = -1;
		while(N-->0)
		{
			int parentNode = find(in.nextInt(), parent);
			
			if(prevNode != parentNode)
				++result;
			
			prevNode = parentNode;
		}
		System.out.print(result);
	}
	static int find(int node, int parent[]) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node], parent);
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