//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17141
import java.util.ArrayDeque;
import java.util.ArrayList;

class Node{int y, x; Node(int y, int x){this.y=y; this.x=x;}}

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int res, N, M, blankCnt, map[][];
	static Node pos[];
	static ArrayList<Node> posList;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void BFS()
	{
		ArrayDeque<Node> q	= new ArrayDeque<>();
		boolean visit[][]	= new boolean[N+2][N+2];
		
		for(Node n : pos)
		{
			visit[n.y][n.x]= true; 
			q.add(n);
		}
		
		int bnkCnt	= M;	// 빈칸을 채운 개수 시작은 M개로
		int time	= 0;	// 걸리는 시간
		
		while(!q.isEmpty())
		{
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
						visit[nextY][nextX] = true;
						q.add(new Node(nextY,nextX));
						++bnkCnt;
					}
				}
			}
			++time;
		}
		
		if(bnkCnt == blankCnt)
			res = Math.min(res, time);
	}
	public static void DFS(int depth, int idx) {
		if(depth == M)
			BFS();
		else
			for(int i=idx; i<posList.size(); i++)
			{
				pos[depth] = posList.get(i);
				DFS(depth + 1, i + 1);
			}
	}
	public static void main(String args[]) throws Exception{
		N		= read();	// 5 ≤ N ≤ 50
		M		= read();	// 1 ≤ M ≤ 10
		map		= new int[N+2][N+2];
		pos		= new Node[M];
		posList = new ArrayList<>();
		res		= Integer.MAX_VALUE;
		
		// 패딩 삽입
		for(int i=0; i<=N+1; i++)
			map[i][0] = map[i][N+1] = map[0][i] = map[N+1][i] = 1;
		
		// 0은 빈 칸, 1은 벽, 2는 바이러스를 놓을 수 있는 칸
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
			{
				map[y][x] = read();
				if(map[y][x] == 2)
					posList.add(new Node(y,x));
				if(map[y][x] != 1)
					blankCnt++;
			}
		
		DFS(0, 0);

		if(res == Integer.MAX_VALUE)
			System.out.print(-1);
		else
			System.out.print(res - 1);
	}
}
