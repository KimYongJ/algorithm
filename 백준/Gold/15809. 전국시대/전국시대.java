//https://www.acmicpc.net/problem/15809
//1초 128MB
//5 3// 국가수(1<=100,000), 기록의 수(1<=100,000)
//10// N줄에 걸쳐 각 국가의 병력이 주어짐(1<=10,000)
//20
//30
//40
//50
//1 1 2// M개의 줄에 걸쳐 O,P,Q가주어짐 
//1 3 4// O가 1이면 P,Q가 동맹을 의미, 2인경우 P,Q가 전쟁을 벌임을 의미
//2 1 3
////답
//2
//40 50

import java.util.PriorityQueue;

class Main{
	
	static int N, M;
	static int parent[];
	static long cnt[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();// 국가수(1<=100,000)
		M = in.nextInt();// 기록의 수(1<=100,000)
		parent = new int[N + 1];
		cnt = new long[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			cnt[i] = in.nextInt();
			parent[i] = i;
		}
		
		for(int i=1; i<=M; i++)
		{
			int o = in.nextInt();
			int p1 = find(in.nextInt());
			int p2 = find(in.nextInt());
			union(o, p1, p2);
		}
		
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		for(int i=1; i<=N; i++)
			if(parent[i] == i)
				pq.add(cnt[i]);

		StringBuilder sb = new StringBuilder();
		
		sb.append(pq.size()).append('\n');
		
		while(!pq.isEmpty())
			sb.append(pq.poll()).append(' ');

		System.out.print(sb);
	}
	static void union(int o, int p1, int p2) {
		if(o == 1)
		{
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
			return;
		}
		
		if(cnt[p1] == cnt[p2])
		{
			parent[p1] = parent[p2] = 0;
		}
		else if(cnt[p1] > cnt[p2])
		{
			cnt[p1] = cnt[p1] - cnt[p2];
			parent[p2] = parent[p1];
		}
		else {
			cnt[p2] = cnt[p2] - cnt[p1];
			parent[p1] = parent[p2];
		}
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