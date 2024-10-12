//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17142

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Node{
	int y, x; Node(int y, int x){this.y=y; this.x=x;}
}
class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, M, virusLen, res, blank, map[][];
	static Node[] pos, dummy;
	
	public static void BFS() {
		boolean visit[][]	= new boolean[N+2][N+2];
		ArrayDeque<Node> q	= new ArrayDeque<>();
		int time			= 0;
		int blankCnt		= blank;
		
		for(Node now : dummy)
		{
			visit[now.y][now.x] = true;
			q.add(now);
		}
		
		while(!q.isEmpty())
		{
			++time;
			int size = q.size();
			while(size-->0)
			{
				Node now = q.poll();
				for(int xy[] : dxy)
				{
					int nextY = now.y + xy[0];
					int nextX = now.x + xy[1];
					if(map[nextY][nextX] != 1 && !visit[nextY][nextX])
					{
						if(map[nextY][nextX] == 0)
							blankCnt--;
						if(blankCnt == 0) {
							res = Math.min(res, time);
							return;
						}
						visit[nextY][nextX] = true;
						q.add(new Node(nextY, nextX));
					}
				}
			}
		}
	}
	public static void DFS(int depth, int idx) {
		if(depth == M)
		{
			BFS();
			return;
		}
		for(int i=idx; i<virusLen; i++)
		{
			dummy[depth] = pos[i];
			DFS(depth + 1, i + 1);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());		// 연구소크기 (4<=50)
		M		= Integer.parseInt(st.nextToken());		// 바이러스의 개수 (1<=10)
		res		= 2501;
		dummy	= new Node[M];
		pos		= new Node[10];
		map		= new int[N+2][N+2];
		
		// 패딩 삽입
		for(int i=0; i<=N+1; i++)
			map[0][i] = map[N+1][i] = map[i][0] = map[i][N+1] = 1;
		
		for(int y=1; y<=N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken());
				if(map[y][x] == 2)
					pos[virusLen++] = new Node(y,x);
				else if(map[y][x] == 0)
					blank++;
			}
		}
		
		if(blank == 0) {
			System.out.print(0);
			return;
		}
		
		DFS(0, 0);
		
		if(res == 2501)
			res = -1;
		
		System.out.print(res);
	}
}