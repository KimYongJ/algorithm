// https://github.com/KimYongJ/algorithm

import java.util.ArrayDeque;
import java.util.ArrayList;

class Main{
	
	static int N, K, M, map[][];
	static boolean visit[], visit_station[];
	static ArrayDeque<Node> q;
	static ArrayList<Integer>[] list;
	
    // 빠른 입력을 위해 만든 함수
    public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void BFS() 
	{
		q.add(new Node(1,1));
		visit[1] = true;
		
		int nextDist , nextNode;
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			
			if(now.node == N) 
			{
				System.out.println(now.dist);
				System.exit(0);
			}
			
			nextDist = now.dist+1;
			for(int index : list[now.node]) 
				if(!visit_station[index])
				{
					visit_station[index] = true;
					for(int i=0; i<K; i++) 
					{
						nextNode = map[index][i];
						
						if(!visit[nextNode]) 
						{
							visit[nextNode] = true;
							q.add(new Node(nextNode, nextDist));
						}
					}
				}
		}
		
	}
	public static void main(String[] args)throws Exception{
		N 		= read(); 				// 역의 수 
		K 		= read(); 				// 하나의 튜브가 연결하는 최대 역의 수 
		M 		= read(); 				// 하이퍼튜브의 개수
		map 	= new int[M][K];		// 연결을 담을 좌표
		list 	= new ArrayList[N+1];	// 역의 번호마다 연결된 하이퍼튜브의 idx
		q 		= new ArrayDeque<>();	// 현재 역의 정보와 현재 역까지 오는데 걸린 갯수를 담는 큐
		visit 	= new boolean[N+1];		// 방문한 노드를 체크할 배열
		visit_station = new boolean[M]; // 방문한 역은 2번이상 방문할 필요가 없음
		
		for(int i=0; i<=N; i++)
			list[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) 
			for(int j=0; j<K; j++) 
			{
				map[i][j] = read();
				list[map[i][j]].add(i);
			}
	
		BFS();
		System.out.println(-1);
	}
}
class Node{
	int node, dist;
	Node(int node, int dist){
		this.node = node;
		this.dist = dist;
	}
}