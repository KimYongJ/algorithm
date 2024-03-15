// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Point{
	int node, dist;
	Point(int node, int dist){
		this.node = node; this.dist = dist;
	}
}
class Main{
	
	static int N, M, start, end, nextDist, pathsize, dp[];
	static ArrayList<Point> list[], reverse[];
	static boolean visit[];
	static ArrayDeque<Point> q;
	static ArrayDeque<Integer> q2;
	public static void BFS() {
		q.add(new Point(start, 0)); // start까지 가는데 걸린 거리 0 세팅
		while(!q.isEmpty()) {
			Point now = q.poll();
			for(Point next : list[now.node]) {
				nextDist = dp[now.node] + next.dist; 
				if(dp[next.node] < nextDist ) {
					dp[next.node] = nextDist;
					q.add(new Point(next.node, nextDist));
				}
			}
		}
	}
	public static void reverse_BFS() {
		q2 = new ArrayDeque<>();
		visit[end] = true;
		q2.add(end);
		while(!q2.isEmpty()) 
		{
			int now = q2.poll();
			for(Point next : reverse[now]) 
			{
				nextDist = dp[now] - next.dist;
				if(dp[next.node]== nextDist) 
				{
					pathsize++;
					if(!visit[next.node]) 
					{
						visit[next.node] = true;
						q2.add(next.node);
					}
				}
					
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		M 		= Integer.parseInt(br.readLine());
		dp 		= new int[N+1];							// 각 노드마다 start부터 해당 각 노드까지 걸리는 최대 거리를 담을 배열 
		list 	= new ArrayList[N+1];
		reverse = new ArrayList[N+1];
		visit	= new boolean[N+1];						// 역탐색시 중복 DFS를 막기 위한 방문 배열
		q 		= new ArrayDeque<>();
		q2		= new ArrayDeque<>();
		for(int i=0; i<=N; i++) 
		{
			list[i] 	= new ArrayList<>();
			reverse[i] 	= new ArrayList<>();
		}
		int a,b,c;
		for(int i=0; i<M; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list[a].add(new Point(b,c));			// a는 b로갈 수 있으며 c만큼 걸린다.
			reverse[b].add(new Point(a,c));			// b는 a로갈 수 있으며 c만큼 걸린다.
		}
		st 		= new StringTokenizer(br.readLine());
		start 	= Integer.parseInt(st.nextToken());
		end 	= Integer.parseInt(st.nextToken());
		
		BFS();										// start부터 end까지 가면서, 각 노드를 들릴 때마다 해당 노드에 도착하는데 까지 걸린 가장 긴 거리를 dp에 담는다.
		
		reverse_BFS();								// end부터 start까지 가면서 dp에 저장되어있는 값과 같을 때 가게 만든다. 그래야 가장 긴 곳을 방문하는 것이기 때문
		
		System.out.println(dp[end]);
		System.out.println(pathsize);
	}
}