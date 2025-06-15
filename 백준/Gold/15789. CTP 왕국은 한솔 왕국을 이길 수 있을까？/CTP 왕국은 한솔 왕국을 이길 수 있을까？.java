//https://www.acmicpc.net/problem/15789
//1초 512MB
//10 7// 왕국 수 3<=100,000, 동맹수 1<=200,000
//1 2// 동맹인 두 왕국
//1 3
//2 3
//1 4
//5 6
//8 10
//7 9
//5 9 1// ctp왕국 번호, 한솔 왕국번호, 추가 동맹 기회 k가 주어짐
//답 : 6

import java.util.PriorityQueue;

class Main{

	static int N, M;
	static int cnt[];
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
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
		
		int base = find(in.nextInt());
		int exception = find(in.nextInt());
		int k = in.nextInt();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> b-a);
		
		for(int i=1; i<=N; i++)
		{
			if(parent[i] == base || parent[i] == exception
					|| parent[i] != i)
				continue;
			pq.add(cnt[i]);
		}
		
		while(!pq.isEmpty() && k != 0)
		{
			cnt[base] += pq.poll();
			--k;
		}
		
		System.out.print(cnt[base]);
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