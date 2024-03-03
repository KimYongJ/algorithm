// https://github.com/KimYongJ/algorithm

import java.util.ArrayDeque;
import java.util.PriorityQueue;


class Main{

	static int N, M, R, a, b, cnt, nextNode, order[];
	static boolean visit[];
	static ArrayDeque<Integer> q;
	static PriorityQueue<Integer> list[];
	
	// 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void BFS() {
		q.add(R);
		visit[R] = true;
		order[R] = ++cnt;
		while(!q.isEmpty()) {
			int now = q.poll();
			
			while(!list[now].isEmpty()) {
				nextNode = list[now].poll();
				if(!visit[nextNode]) {
					visit[nextNode] = true;
					order[nextNode] = ++cnt;
					q.add(nextNode);
				}

			}
		}
	}
	public static void main(String[] args)throws Exception{
		N 					= read();
		M 					= read();
		R 					= read();
		order 				= new int[N+1];
		visit				= new boolean[N+1];
		list 				= new PriorityQueue[N+1];
		q					= new ArrayDeque<>();
		for(int i=1; i<=N; i++) 
			list[i] = new PriorityQueue<>();
		
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			list[a].add(b);
			list[b].add(a);
		}

		
		BFS();
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(order[i]).append('\n');
		System.out.println(sb);
	}
}