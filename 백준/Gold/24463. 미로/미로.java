//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24463
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Node{
	int y, x;
	Node(int y, int x){this.y=y; this.x=x;}
}
class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, map[][];
	static int pos[][];
	static boolean visit[][];
	static Node position[][];
	
	public static void DFS(int y, int x) {
		visit[y][x] = true;
		if(position[y][x] != null) {
			Node before = position[y][x];
			DFS(before.y, before.x);
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());	// 3<=N,M<=2001(홀수)
		X		= Integer.parseInt(st.nextToken());	// 3<=N,M<=2001(홀수)
		map		= new int[Y+2][X+2];
		pos		= new int[2][2];
		visit	= new boolean[Y+2][X+2];
		position= new Node[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=X; x++)
				map[y][x] = str.charAt(x-1);
		}
		int idx = 0;
		for(int y=1; y<=Y; y++)
			if(map[y][1] == '.')
			{
				pos[idx][0] = y;
				pos[idx++][1] = 1;
			}
			else if(map[y][X] == '.')
			{
				pos[idx][0] = y;
				pos[idx++][1] = X;
			}
		
		for(int x=1; x<=X; x++)
			if(map[1][x] == '.')
			{
				pos[idx][0] = 1;
				pos[idx++][1] = x;
			}
			else if(map[Y][x] == '.')
			{
				pos[idx][0] = Y;
				pos[idx++][1] = x;
			}
		
		ArrayDeque<Node> q = new ArrayDeque<>();
		visit[pos[0][0]][pos[0][1]] = true;
		q.add(new Node(pos[0][0], pos[0][1]));
		while(!q.isEmpty())
		{
			Node now = q.poll();
			if(now.y == pos[1][0] && now.x == pos[1][1])
				break;
			for(int xy[] : dxy) {
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(map[nextY][nextX] == '.' && !visit[nextY][nextX])
				{
					visit[nextY][nextX] = true;
					position[nextY][nextX] = now;
					q.add(new Node(nextY, nextX));
				}
			}
			
		}
		
		visit = new boolean[Y+2][X+2];
		DFS(pos[1][0], pos[1][1]);
		
		StringBuilder sb = new StringBuilder();
		for(int y=1; y<=Y; y++)
		{
			for(int x=1; x<=X; x++)
			{
				if(map[y][x] == '+')
					sb.append('+');
				else if(visit[y][x])
					sb.append('.');
				else
					sb.append('@');
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}