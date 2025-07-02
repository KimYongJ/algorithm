//https://www.acmicpc.net/problem/29618
//0.5초 512MB

class Main{
	
	static int tree[];
	static int lazy[];
	static int N, Q;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		
		N = in.nextInt();
		Q = in.nextInt();
		tree = new int[N * 4];
		lazy = new int[N * 4];
		
		while(Q-->0)
			update(1, 1, N, in.nextInt(), in.nextInt(), in.nextInt());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(query(1, 1, N, i)).append(' ');
		
		System.out.print(sb);
	}
	static void update(int treeNode, int s, int e, int left, int right, int val) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left <= s && e <= right)
		{
			lazy[treeNode] = val;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, val);
		update(treeNode << 1 | 1, mid + 1, e, left, right, val);
	}
	static int query(int treeNode, int s, int e, int targetIdx) {
		
		propagate(treeNode, s, e);
		
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		if(targetIdx <= mid)
			return query(treeNode << 1, s, mid, targetIdx);
		
		return query(treeNode << 1 | 1, mid + 1, e, targetIdx);
	}
	static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] == 0)
			return;
		
		if(s == e && tree[treeNode] == 0)
			tree[treeNode] = lazy[treeNode];
		
		if(s != e)
		{
			if(lazy[treeNode << 1] == 0)
				lazy[treeNode << 1] = lazy[treeNode];
			if(lazy[treeNode << 1 | 1] == 0)
				lazy[treeNode << 1 | 1] = lazy[treeNode];
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