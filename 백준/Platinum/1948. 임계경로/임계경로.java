// https://github.com/KimYongJ/algorithm

import java.util.ArrayDeque;
import java.util.ArrayList;

class Point{
	int node, dist;
	Point(int node, int dist){
		this.node = node; this.dist = dist;
	}
}

class Main{
	
	static int N, M, start, end, nextDist, pathsize, in[], dp[];
	static ArrayList<Point> list[], reverse[];
	static boolean visit[];
	static ArrayDeque<Point> q;
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void BFS() {
		q = new ArrayDeque<>();
		q.add(new Point(start, 0)); // start까지 가는데 걸린 거리 0 세팅
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(Point next : list[now.node]) {
				in[next.node]--;						// ★인접 노드로 한번 방문했기 때문에 방문한 노드 진입차수에서 -1을 해줍니다. 
				nextDist = dp[now.node] + next.dist;	// 기존 저장된 nextNode까지 가는것과 nowNode를 거쳐 nextNode로 가는것이 더 큰지 체크하기 위한 변수
				if(dp[next.node] < nextDist ) 
					dp[next.node] = nextDist;
				if(in[next.node]== 0)					// ★ 진입 차수가 0일 때 큐에 넣어주어 불필요한 탐색을 줄여줍니다
					q.add(new Point(next.node, nextDist));
			}
		}
	}
	public static void reverse_DFS(int node, int dist) {
		visit[node] = true;
		if(node == start)
			return;
		
		for(Point next : reverse[node]) 
		{
			nextDist = dist - next.dist;
			if(dp[next.node] == nextDist) 
			{
				pathsize++;						// nextNode까지 경로는 카운팅하게 해줌
				if(!visit[next.node]) 			// 하지만 DFS를 하는건 이미 방문한 노드라면 하지 못하게 해야 한다.
				{
					reverse_DFS(next.node, nextDist);
				}
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		dp 		= new int[N+1];							// 각 노드마다 start부터 해당 각 노드까지 걸리는 최대 거리를 담을 배열
		in		= new int[N+1];							// ★위상 정렬을 위한 변수 선언 
		list 	= new ArrayList[N+1];
		reverse = new ArrayList[N+1];
		visit	= new boolean[N+1];						// 역탐색시 중복 DFS를 막기 위한 방문 배열
		for(int i=0; i<=N; i++) 
		{
			list[i] 	= new ArrayList<>();
			reverse[i] 	= new ArrayList<>();
		}
		
		int a,b,c;
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			c = read();
			list[a].add(new Point(b,c));			// a는 b로갈 수 있으며 c만큼 걸린다.
			reverse[b].add(new Point(a,c));			// b는 a로갈 수 있으며 c만큼 걸린다.
			in[b] += 1;								// a에서 b로가기 때문에 b로 가는 진입차수에 +1을 함
		}

		start 	= read();
		end 	= read();
		
		BFS();										// start부터 end까지 가면서, 각 노드를 들릴 때마다 해당 노드에 도착하는데 까지 걸린 가장 긴 거리를 dp에 담는다.
		
		reverse_DFS(end, dp[end]);					// end부터 start까지 가면서 dp에 저장되어있는 값과 같을 때 가게 만든다. 그래야 가장 긴 곳을 방문하는 것이기 때문
		
		System.out.println(dp[end]);
		System.out.println(pathsize);
	}
}