// https://github.com/KimYongJ/algorithm

import java.util.ArrayList;
import java.util.Collections;


class Main{

	static int N, M, R, a, b, cnt, order[];
	static boolean visit[];
	static ArrayList<Integer> list[];
	
	// 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void DFS(int node) {
		visit[node] = true;
		order[node] = ++cnt;
		for(int nextNode : list[node]) 
			if(!visit[nextNode])
				DFS(nextNode);
	}
	public static void main(String[] args)throws Exception{
		N 					= read();
		M 					= read();
		R 					= read();
		order 				= new int[N+1];
		list 				= new ArrayList[N+1];
		visit				= new boolean[N+1];
		for(int i=1; i<=N; i++) 
			list[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			list[a].add(b);
			list[b].add(a);
		}
		// 노드 오름차순 정렬
		for(int i=1; i<=N; i++) 
			Collections.sort(list[i]);
		
		DFS(R);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(order[i]).append('\n');
		System.out.println(sb);
	}
}