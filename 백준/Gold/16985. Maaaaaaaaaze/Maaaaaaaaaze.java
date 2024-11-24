//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/16985
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{
	int z, y, x, cnt;
	Node(int z, int y, int x, int c){
		this.z=z; this.y=y; this.x=x;
		cnt=c;
	}
}
class Main{
	
	static final int dxyz[][] = {{0,1,0},{0,-1,0},{0,0,1},{0,0,-1},{1,0,0},{-1,0,0}};
	static int[][][] origin,map;
	static int arr[];
	static boolean visit[];
	static int min = 1<<30;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		map		= new int[6][7][7];
		origin	= new int[6][7][7];
		
		for(int z=0; z<5; z++)
			for(int y=1; y<=5; y++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int x=1; x<=5; x++)
					origin[z][y][x] = Integer.parseInt(st.nextToken());
			}
		
		bruteforce(0);
		
		System.out.print(min == 1<<30 ? -1 : min);
	}
	public static void per(int depth) {
		if(depth == 5)
		{
			for(int i=0; i<5; i++)
				map[i] = origin[arr[i]];
			BFS();
			return;
		}
		for(int i=0; i<5; i++)
			if(!visit[i])
			{
				visit[i] = true;
				arr[depth] = i;
				per(depth + 1);
				visit[i] = false;
			}
	}
	public static void bruteforce(int h) {
		if(h==5)
		{
			visit = new boolean[5];
			arr = new int[5];
			per(0);
			return;
		}
		bruteforce(h + 1);
		rotation(h);
		bruteforce(h + 1);
		rotation(h);
		bruteforce(h + 1);
		rotation(h);
		bruteforce(h + 1);
		rotation(h);
	}
	public static void rotation(int h)
	{
		int arr[][] = new int[7][7];
		for(int y=1; y<=5; y++)
			for(int x=1; x<=5; x++)
				arr[y][x] = origin[h][6-x][y];
		origin[h] = arr;
	}
	public static void BFS() {
		if(map[0][1][1] != 1 || map[4][5][5] != 1)
			return;
		
		boolean visit[][][] = new boolean[6][7][7];
		ArrayDeque<Node> q	= new ArrayDeque<>();
		q.add(new Node(0, 1, 1,0));
		visit[0][1][1] = true;
		
		while(!q.isEmpty())
		{
			Node now = q.poll();
			if(map[now.z][now.y][now.x] == 1 && now.z == 4 && now.y == 5 && now.x == 5)
			{
				min = Math.min(min, now.cnt);
				return;
			}
			
			int nextCnt = now.cnt + 1;
			
			for(int xyz[] : dxyz)
			{
				int nextZ = now.z + xyz[0];
				int nextY = now.y + xyz[1];
				int nextX = now.x + xyz[2];
				
				if(0 <= nextZ && !visit[nextZ][nextY][nextX] && map[nextZ][nextY][nextX] == 1)
				{
					visit[nextZ][nextY][nextX] = true;
					q.add(new Node(nextZ, nextY, nextX, nextCnt));
				}
			}
		}
	}
}