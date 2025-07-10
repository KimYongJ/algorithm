//https://www.acmicpc.net/problem/32477
//4초 1024

class Main{
	
	static int res = 1;
	static int N, M;
	static int parent[];
	static int cnt[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		
		parent = new int[N + 1];
		cnt = new int[N + 1];
		
		for(int i=0; i<=N; i++)
		{
			parent[i] = i;
			cnt[i] = 1;
		}
		
		while(M-->0)
		{
			int p1 = find(in.nextInt());
			int p2 = find(in.nextInt());
			
			if(p1 == p2)
				continue;
			
			if(parent[p1] > parent[p2])
			{
				int tmp = p1;
				p1 = p2;
				p2 = tmp;
			}
			
			parent[p2] = p1;
			cnt[p1] += cnt[p2];
			
			res = Math.max(res, cnt[p1]);
		}
		
		System.out.print(res);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	
	static class Reader
	{
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