//https://www.acmicpc.net/problem/17250
//5초 512MB
//5 4//노드수(2<=100,000), 간선 수(1<=100,000)
//3// N개 줄에 각 은하 내에 존재하는 행성 수가 주어짐
//9
//10
//11
//15
//1 2//간선 개수만큼 연결된 두 노드가 주어짐
//2 3
//4 5
//4 3
//답
//12
//22
//26
//48

class Main{

	static int N, M;
	static int cnt[];
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();//노드수(2<=100,000)
		M = in.nextInt();//간선 수(1<=100,000)
		cnt = new int[N + 1];
		parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			cnt[i] = in.nextInt();
			parent[i] = i;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=M; i++)
		{
			int p1 = find(in.nextInt());
			int p2 = find(in.nextInt());
			if(p1 == p2) {
				sb.append(cnt[p1]).append('\n');
			}
			else if(parent[p1] < parent[p2]) {
				parent[p2] = p1;
				cnt[p1] += cnt[p2];
				sb.append(cnt[p1]).append('\n');
			}
			else {
				parent[p1] = p2;
				cnt[p2] += cnt[p1];
				sb.append(cnt[p2]).append('\n');
			}
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