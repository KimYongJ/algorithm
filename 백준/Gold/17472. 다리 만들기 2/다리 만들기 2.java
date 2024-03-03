// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Main{
	static class Point{
		int y, x;
		Point(int y, int x){
			this.y = y; 
			this.x = x;
		}
	}
	static class Node{
		int y, x, dist;
		Node(int y, int x, int dist){
			this.y = y; 
			this.x = x; 
			this.dist = dist;
		}
	}
	static int Y, X, nextY, nextX, dist, nodeIndex, map[][];
	static int aParent, bParent, parent[];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean visit[][];
	static ArrayDeque<Point> q;
	static PriorityQueue<Node> pq;
	
    // 빠른 입력을 위해 만든 함수
    public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}

	public static void landmark_DFS(int y, int x)
	{
		map[y][x] = nodeIndex;
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(map[nextY][nextX] == -1)
				landmark_DFS(nextY, nextX);
		}
	}
	public static void boundarySearch_DFS(int y, int x) 
	{
		if(visit[y][x]) return;
		visit[y][x] = true;
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(!visit[nextY][nextX] && map[nextY][nextX] != -2) 
			{
				if(map[nextY][nextX] > 0) 
				{
					visit[nextY][nextX] = true;
					q.add(new Point(nextY, nextX));
				}
				else boundarySearch_DFS(nextY, nextX);
			}
		}
	}
	public static void make_edge() {
		int baseNode;
		while(!q.isEmpty()) {
			Point now = q.poll();
			baseNode = map[now.y][now.x];
			for(int xy[] : dxy) 
			{
				nextY = now.y;
				nextX = now.x;
				dist = 0;
				while(true) 
				{
					nextY += xy[0];
					nextX += xy[1];
					if(map[nextY][nextX] != 0) {
						break;
					}
					++dist;
				}
				if(dist>=2 && 0< map[nextY][nextX] && baseNode != map[nextY][nextX] ) {
					pq.add(new Node(baseNode, map[nextY][nextX], dist));
				}
			}
		}
	}
	public static int getParent(int x) {
		if(parent[x] == x) return x;
		return getParent(parent[x]);
	}
	public static void MST() {
		int nodeCnt = 0;
		int distSum = 0;
		parent = new int[nodeIndex+1];
		for(int i=1; i<=nodeIndex; i++)
			parent[i] = i;
		
		while(!pq.isEmpty() && nodeCnt != nodeIndex -1) 
		{
			Node now = pq.poll();
			aParent = getParent(now.y);
			bParent = getParent(now.x);
			
			if(aParent != bParent) {
				++nodeCnt;
				if(aParent > bParent) parent[aParent] = bParent;
				else parent[bParent] = aParent;
				distSum += now.dist;
			}
		}
		
		if(nodeCnt != nodeIndex -1) {
			distSum = -1;
		}
		System.out.println(distSum);
	}
	public static void main(String[] args)throws Exception{
		Y 					= read();
		X 					= read();
		map 				= new int[Y+2][X+2];
		visit 				= new boolean[Y+2][X+2];
		q 					= new ArrayDeque<>();
		pq 					= new PriorityQueue<Node>((a,b)-> a.dist-b.dist);
		// 패딩 삽입
		for(int y=0; y<Y+2; y++) map[y][0] = map[y][X+1] = -2;
		for(int x=0; x<X+2; x++) map[0][x] = map[Y+1][x] = -2;
		
		int num;
		for(int y=1; y<=Y; y++) 
			for(int x=1; x<=X; x++) 
			{
				num = read();
				map[y][x] = num == 1 ? -1 : num;
			}
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x] == -1) 
				{
					++nodeIndex;
					landmark_DFS(y,x);	// 각 섬에 숫자 마킹
				}
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x] == 0 && !visit[y][x]) 		// 섬의 외곽을 큐에 넣는다.
					boundarySearch_DFS(y,x);
				

		make_edge();					// 섬의 외곽에서 2이상떨어진 다른 섬을 만나면 그것을 기준으로 노드와 간선을만듦(최소 스패닝 트리를 진행할 간선을 우선순위 큐에 넣음)
		MST();							// 최소 스패닝 트리(Minumum Spanning Tree)를 구한다.
	}
}