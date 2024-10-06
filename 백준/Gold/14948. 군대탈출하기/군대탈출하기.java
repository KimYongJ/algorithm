//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14948
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{int y, x, isUsed;Node(int y, int x,int i){this.y=y; this.x=x; this.isUsed=i;}}

class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	
	public static boolean check(int map[][], int Y, int X, int limit)
	{
		if(limit < map[1][1])
			return false;
		
		ArrayDeque<Node> q = new ArrayDeque<>();
		boolean visit[][][] = new boolean[2][Y+2][X+2];
		visit[0][1][1] = true;
		q.add(new Node(1,1, 0));
		while(!q.isEmpty())
		{
			Node now = q.poll();
			if(now.y==Y && now.x==X)
				return true;
			
			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(map[nextY][nextX] < 0 || visit[now.isUsed][nextY][nextX])
					continue;
				
				if(map[nextY][nextX] <= limit)
				{
					visit[now.isUsed][nextY][nextX] = true;
					q.add(new Node(nextY, nextX, now.isUsed));
				}
				if(now.isUsed == 0)
				{
					nextY += xy[0];
					nextX += xy[1];
					if(0 <= map[nextY][nextX] && map[nextY][nextX] <= limit && !visit[1][nextY][nextX] )
					{
						visit[1][nextY][nextX] = true;
						q.add(new Node(nextY, nextX, 1));
					}
				}
				
			}
			
		}
		return false;
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int map[][] = new int[Y+2][X+2];
		int s = Integer.MAX_VALUE;
		int e = 0;
		
		for(int y=0; y<=Y+1; y++)
			map[y][0] = map[y][X+1] = -1;	// 패딩 넣기
		for(int x=0; x<=X+1; x++)
			map[0][x] = map[Y+1][x] = -1;	// 패딩 넣기
		
		for(int y=1; y<=Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken());
				s = Math.min(map[y][x], s);
				e = Math.max(map[y][x], e);
			}
		}
		
		int res = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(check(map, Y, X, mid))
			{
				e = mid - 1;
				res = mid;
			}else
				s = mid + 1;
		}
		System.out.print(res);
	}
}