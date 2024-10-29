//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3482
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, map[][];
	static int maxY, maxX, maxDist;
	static boolean visit[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			X		= Integer.parseInt(st.nextToken());
			Y		= Integer.parseInt(st.nextToken());
			maxY	= -1;
			maxX	= -1;
			maxDist = 0;
			map		= new int[Y][X];
			visit	= new boolean[Y][X];
			
			for(int y=0; y<Y; y++)
			{
				String str = br.readLine();
				for(int x=0; x<X; x++)
				{
					if(str.charAt(x) == '#')
						map[y][x] = 1;	// 1은 바위, 0은 갈 수 있는 곳
				}
			}
			
			for(int y=0; y<Y; y++) {
				for(int x=0; x<X; x++) {
					if(map[y][x] == 0 && !visit[y][x]) {
						if(maxY == -1) {
							maxY = y;
							maxX = x;
						}
						visit[y][x] = true;
						DFS(y, x, 0);
					}
				}
			}
			maxDist = 0;
			visit	= new boolean[Y][X];
			DFS(maxY, maxX, 0);
			sb.append("Maximum rope length is ").append(maxDist).append(".\n");
		}
		System.out.print(sb.toString());
	}
	public static void DFS(int y, int x, int dist) {
		if(maxDist < dist)
		{
			maxDist = dist;
			maxY = y;
			maxX = x;
		}
		for(int xy[] : dxy) {
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(!visit[nextY][nextX] && map[nextY][nextX] == 0) {
				visit[nextY][nextX] = true;
				DFS(nextY, nextX, dist + 1);
			}
		}
	}
}