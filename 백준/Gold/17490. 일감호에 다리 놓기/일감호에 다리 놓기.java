//https://www.acmicpc.net/problem/17490
//2초 256MB
//5 3 9// 강의동수 3<=1,000,000 공사구간수0<=N 가진돌수 0<=5,000,000,000
//2 1 3 2 5//노드에서 중앙 와우도까지 놓아야 하는 돌의 개수
//2 3// 공사중인 구간의 수가 주어짐
//4 5
//5 1
//YES

class Main{
	
	static int N, M;
	static long K;
	static int cnt[];
	static int parent[];
	static boolean isBroken[];

	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextLong();
		cnt = new int[N + 1];
		parent = new int[N + 1];
		isBroken = new boolean[N + 1];
		
		if(M <= 1)
		{
			System.out.print("YES");
			return;
		}
		
		for(int i=1; i<=N; i++)
		{
			cnt[i] = in.nextInt();
			parent[i] = i;
		}
		
		for(int i=1; i<=M; i++)
		{
			int a = in.nextInt();
			int b = in.nextInt();
			int min = Math.min(a, b);
			int max = Math.max(a, b);
			
			if(max == N && min == 1)
				isBroken[1] = true;
			else
				isBroken[max] = true;
		}
		
		for(int i=1; i<=N; i++)
		{
			int left = i;
			int right = i + 1;
			
			if(right > N)
				right = 1;
			
			if(isBroken[right])
				continue;
			
			int p1 = find(parent[left]);
			int p2 = find(parent[right]);
			
			if(cnt[p1] < cnt[p2])
				parent[p2] = p1;
			else
				parent[p1] = p2;
		}
		
		for(int i=1; i<=N; i++)
			if(parent[i] == i)
				K -= cnt[i];
		
		System.out.print(K >= 0 ? "YES" : "NO");
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