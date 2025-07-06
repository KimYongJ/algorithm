//https://www.acmicpc.net/problem/1778
//1초 128MB

class Main{
	
	static int N, M, cnt;
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		StringBuilder sb = new StringBuilder();
		
		parent = new int[50_001];
		
		int idx = 0;
		while(true)
		{
			N = in.nextInt();
			M = in.nextInt();
			cnt = N;
			
			if(N == 0 && M == 0)
				break;
			
			for(int i=1; i<=N; i++)
				parent[i] = i;
			
			while(M-->0)
			{
				int p1 = find(in.nextInt());
				int p2 = find(in.nextInt());
				
				if(p1 == p2)
					continue;
				
				cnt--;
				
				if(parent[p1] < parent[p2])
					parent[p2] = p1;
				else
					parent[p1] = p2;
			}
			sb.append("Case ").append(++idx).append(": ").append(cnt).append('\n');
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