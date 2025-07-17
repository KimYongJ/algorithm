//https://www.acmicpc.net/problem/32665
//1초 1024MB

class Main{
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		int N = in.nextInt();
		int M = in.nextInt();
		int cnt[] = new int[N + 1];
		int parent[] = new int[N + 1];
		
		for(int i=0; i<=N; i++)
		{
			cnt[i] = 1;
			parent[i] = i;
		}
		
		int edgeCnt = 0;
		while(M-->0 && cnt[1] != N)
		{
			int p1 = find(in.nextInt(), parent);
			int p2 = find(in.nextInt(), parent);
			
			edgeCnt++;
			
			if(p1 == p2)
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
		
		System.out.print(cnt[1] == N ? edgeCnt : -1);
	}
	static int find(int node, int parent[]) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node], parent);
	}
}

class Reader {
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