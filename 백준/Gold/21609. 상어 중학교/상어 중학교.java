// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Block{
	int size, zeroCnt, baseY, baseX;
	ArrayList<int[]> list;
	Block(int size, int zeroCnt, int baseY, int baseX, ArrayList<int[]> list){
		this.size 	= size;		this.zeroCnt 	= zeroCnt;
		this.baseY 	= baseY;	this.baseX 		= baseX;
		this.list 	= list;
	}
}
class Point{
	int y, x;
	Point(int y, int x){
		this.y = y; this.x = x;
	}
}
class Main{
	
	static int N, M, max, size, zeroCnt, nextY, nextX, nextNum, result, map[][];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean visit[][][];
	static Block block;
	static ArrayDeque<Point> q;
	public static void saveBlock(int size, int zeroCnt, int baseY, int baseX, ArrayList<int[]>list) {
		block.size 	= size; 	block.zeroCnt 	= zeroCnt; 
		block.baseX = baseY; 	block.baseX 	= baseX;
		block.list 	= list;
	}
	public static void print() {
		for(int i=0; i<max; i++) {
			for(int j=0; j<max; j++) {
				System.out.print((map[i][j] <0 ? " ":"")+map[i][j]+"   ");
			}System.out.println("");
		}
		System.out.println("");
	}
	public static void BFS(int baseNum, int y, int x) {
		ArrayList<int[]>list 	= new ArrayList<>();
		size 	= 1;
		zeroCnt = 0;
		q 		= new ArrayDeque<>();
		visit[baseNum][y][x] = true;	// 해당 숫자에 대한 yx좌표 마킹
		q.add(new Point(y,x));
		list.add(new int[] {y,x});
		
		while(!q.isEmpty()) 
		{
			Point now = q.poll();
			for(int xy[] : dxy) 
			{
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				nextNum = map[nextY][nextX];
				if((nextNum==0 || nextNum==baseNum) && !visit[baseNum][nextY][nextX]) 
				{
					visit[baseNum][nextY][nextX] = true;		// 방문 처ㄹ
					if(nextNum == 0) 							// 무지개의 개수를 센다
						zeroCnt++;
					size++;
					q.add(new Point(nextY,nextX));
					list.add(new int[] {nextY,nextX});
				}
			}
		}
		if(size > 1) 
		{
			if(block.size < size)
				saveBlock(size, zeroCnt, y, x, list);
			else if(block.size == size) 
			{
				if(block.zeroCnt < zeroCnt)
					saveBlock(size, zeroCnt, y, x, list);
				else if(block.zeroCnt == zeroCnt) 
				{
					if(block.baseY < y)
						saveBlock(size, zeroCnt, y, x, list);
					else if(block.baseY == y) 
					{
						if(block.baseX < x)
							saveBlock(size, zeroCnt, y, x, list);
					}
				}
			}
		}
	}
	public static void rotate() {
		int map2[][] = new int[max][max];
		for(int y=0; y<max; y++)
			for(int x=0; x<max; x++) 
				map2[max-1-x][y] = map[y][x];
		map = map2;
	}
	public static void DFS(int y, int x) 
	{
		if(map[y+1][x] != -2) return;
		int dummy = map[y+1][x];
		map[y+1][x] = map[y][x];
		map[y][x] = dummy;
		DFS(y+1, x);
	}
	public static void gravity() {
		for(int x=1; x<max; x++)
			for(int y=max-2; y>0; y--)
				if(map[y][x] >= 0)
					DFS(y, x);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());		// 해당 사각형의 크기
		M = Integer.parseInt(st.nextToken());		// 일반블록의 종류
		max = N+2;
		map = new int[max][max];
		
		for(int n=0; n<max; n++)
			map[n][0] = map[n][max-1] =
			map[0][n] = map[max-1][n] = -1; 			// 패딩 넣기
		
		for(int y=1; y<=N; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}

		while(true) 
		{
			block = new Block(0,0,0,0,null);
			visit = new boolean[M+1][max][max];
			for(int y=1; y<=N; y++)
				for(int x=1; x<=N; x++)
					if(map[y][x] > 0 && !visit[map[y][x]][y][x]) 
						BFS(map[y][x], y, x);		// 기준 블록 숫자 , y좌표, x좌표
					
				
			
			if(block.list == null)
				break;
			// 블록 삭제 
			for(int xy[] : block.list) 
				map[xy[0]][xy[1]] = -2;				// -2 는 이동가능한 공간

			gravity();
			rotate();
			gravity();
			result += block.size * block.size;
		}
		
		System.out.println(result);
	}
}