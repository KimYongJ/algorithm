//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17141
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node{int y, x; Node(int y, int x){this.y=y; this.x=x;}}

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int res, N, M, blankCnt, map[][];
	static Node pos[];
	static ArrayList<Node> posList;
	
	public static void BFS()
	{
		ArrayDeque<Node> q	= new ArrayDeque<>();
		boolean visit[][]	= new boolean[N+2][N+2];
		for(Node n : pos)
		{
			visit[n.y][n.x]= true; 
			q.add(n);
		}
		
		int bnkCnt	= M;
		int time	= 0;
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
		{
			BFS();
			return;
		}
		for(int i=idx; i<posList.size(); i++)
		{
			pos[depth] = posList.get(i);
			DFS(depth + 1, i + 1);
		}
	}
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N		= Integer.parseInt(st.nextToken()); // 5 ≤ N ≤ 50
		M		= Integer.parseInt(st.nextToken());	// 1 ≤ M ≤ 10
		map		= new int[N+2][N+2];
		pos		= new Node[M];
		posList = new ArrayList<>();
		res		= Integer.MAX_VALUE;
		
		for(int i=0; i<=N+1; i++)
			map[i][0] = map[i][N+1] = map[0][i] = map[N+1][i] = 1;
		// 0은 빈 칸, 1은 벽, 2는 바이러스를 놓을 수 있는 칸
		for(int y=1; y<=N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken());
				if(map[y][x] == 2)
					posList.add(new Node(y,x));
				if(map[y][x] != 1)
					blankCnt++;
			}
		}
		
		DFS(0, 0);

		if(res == Integer.MAX_VALUE)
			System.out.print(-1);
		else
			System.out.print(res - 1);
	}
}