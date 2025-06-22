//https://www.acmicpc.net/problem/23324
//5 4 2// 정점수(2<=100,000), 간선수(1<=200,000), 제외 정수 (1<=M)
//1 2
//2 3
//3 4
//4 5
// 답 : 6

class Main{
	
	static int N, M, K;
	static int cnt[];
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();// 정점수(2<=100,000)
		M = in.nextInt();// 간선수(1<=200,000)
		K = in.nextInt();// 제외 정수 (1<=M)
		cnt = new int[N + 1];
		parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			cnt[i] = 1;
			parent[i] = i;
		}
		
		for(int i=1; i<=M; i++)
		{
			int p1 = find(in.nextInt());
			int p2 = find(in.nextInt());
			
			if(K == i || p1 == p2)
				continue;
			
			if(parent[p1] < parent[p2])
			{
				parent[p2] = p1;
				cnt[p1] += cnt[p2];
			}
			else
			{
				parent[p1] = p2;
				cnt[p2] += cnt[p1];
			}
		}
		
		long res = 1;
		int groupCnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			if(parent[i] == i)
			{
				res *= cnt[i];
				groupCnt++;
			}
		}
		
		System.out.print(groupCnt == 1 ? 0 : res);
	}
	static int find(int node)
	{
		if(parent[node] == node)
			return node;
		
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