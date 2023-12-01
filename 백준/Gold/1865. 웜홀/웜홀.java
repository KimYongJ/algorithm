// https://github.com/KimYongJ/algorithm

import java.util.ArrayList;
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		new Solution().solution();
	}
}
class Solution{
	
	final int INF = 4_990_001;	 			// 최소로 나올 수 있는 간선의 거리 
	
	ArrayList<Node> list[]; 				// 양방향 저장이 가능해야 하기 때문에 list로 담는다.
	int T, N, M, W, dist[];
	
    int read() throws Exception { 			// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    } 
	
	boolean bellman_ford() { 
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		for(int i=0; i<N; i++) {
			boolean notUpdate = true;
			for(int j=1; j<=N; j++) {
				for(Node node : list[j]) {
					int start = j;
					int end = node.end;
					int distance = node.dist;
					int newEndDist = dist[start] + distance;
					if(dist[end] > newEndDist) {
						notUpdate = false;
						dist[end] = newEndDist;
						if(i+1==N)
							return true;
					}
				}
			}
			if(notUpdate)
				break;
		}
		return false;
	}
	
	
	void solution() throws Exception{

		T = read();
		
		while(T-->0) {
			N 		= read(); 					// 노드 갯수(최대 100개)
			M 		= read(); 					// 양의 간선 갯수
			W 		= read(); 					// 음의 간선 갯수
			list 	= new ArrayList[N+1];		// 간선 정보를 담을 list배열
			
			for(int i=1; i<N+1; i++)			// 리스트 초기화
				list[i] = new ArrayList<Node>();
			
			for(int i=0; i<M+W; i++) {			// 간선에 대해 입력을 받는다.
				int a = read();
				int b = read();
				int d = read();
				if(i<M) {
					list[a].add(new Node(b,d)); // 양의 간선은 양방향 그래프
					list[b].add(new Node(a,d)); // 양의 간선은 양방향 그래프
				}else {
					list[a].add(new Node(b,-d));// 음의 간선은 단방향 그래프이다. 
				}
			}
			if( bellman_ford() ) {				// 벨만포드알고리즘 진행시 음의 사이클이 있다면 true반환
				System.out.write(new byte[]{'Y', 'E', 'S', '\n'});
			}else {
				System.out.write(new byte[]{'N', 'O', '\n'});
			}			
		}

	}
}
class Node{
	int end, dist;
	public Node(int end, int dist){
		this.end = end;
		this.dist = dist;
	}
}

