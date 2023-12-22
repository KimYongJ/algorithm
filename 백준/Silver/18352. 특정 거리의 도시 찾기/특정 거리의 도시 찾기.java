//https://github.com/KimYongJ/algorithm

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

class Main{
	static class Node{
		int node, dist;
		Node(int node, int dist){
			this.node = node;
			this.dist = dist;
		}
	}
	static final int INF = 300_000;
	static int N, M, K, X, dist[];
	static int a, b;
	static boolean visit[];
	static StringBuilder sb;
	static ArrayDeque<Node> q;
	static ArrayList<Integer> result;
	static ArrayList<Integer>[] list;
	
	// 빠른 숫자 입력을 위한 함수
    private static int read() throws Exception 
    {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
    // 다익스트라 함수
	public static void Dijkstra() 
	{
		q = new ArrayDeque<Node>() {{
			add(new Node(X,0)); // 큐 초기화와 동시에 X번 노드까지 가는데 걸린 거리 0 셋팅
			}};
		dist[X] = 0; // 출발 도시 값은 0 으로 초기화 
		
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			
			if(visit[now.node]) continue;
			
			visit[now.node] = true; // 빠른 연산을 위해 방문한 노드는 2번 방문하지 않도록함
			
			for(int next : list[now.node]) 
			{
				int distSum = now.dist + 1;
				if(dist[next] > distSum) 
				{
					dist[next] = distSum;
					q.add(new Node(next, distSum));
					if(distSum == K) 
					{ // 최단거리가 K와 같다면 결과에 플러스
						result.add(next);
					}
				}
			}
		}
		
	}
	public static void main(String[] args)throws Exception
	{
		N 			= read(); // 노드 개수
		M 			= read(); // 도로의 개수
		K 			= read(); // 결과 거리 정보
		X 			= read(); // 출발 도시 번호
		
		dist 		= new int[N+1];
		visit		= new boolean[N+1];
		list 		= new ArrayList[N+1];
		result 		= new ArrayList<>();
		sb 			= new StringBuilder();
		
		for(int i=1; i<=N; i++) 
		{
			dist[i] = INF; // 최단거리를 담을 배열 초기화 
			list[i] = new ArrayList<Integer>();// 간선 정보를 담을 리스트 초기화
		}
		
		for(int i=0; i<M; i++) 
		{
			a 		= read();
			b 		= read();
			list[a].add(b); // 단방향 연결
		}
		
		Dijkstra();// 최단 거리 구하는 함수
		
		if(result.size()==0) 
		{ // 최단거리가 K인 노드가 없을 경우 -1 출력
			System.out.println(-1);
			return;
		}
		
		Collections.sort(result); // 결과 노드 오름차순 정렬

		for(int r : result)
			sb.append(r)
			.append('\n');
		
		System.out.println(sb);
	}
}