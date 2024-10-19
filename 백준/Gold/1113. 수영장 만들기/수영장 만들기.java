//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1113
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int result, minHeight;
	static int Y, X, map[][];
	static boolean visit[][], check[][];
	static ArrayList<int[]> position;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean DFS(int y, int x, int h){
		if(visit[y][x])
			return true;
		visit[y][x] = true;
		position.add(new int[] {y, x});
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(map[nextY][nextX] < h)
				return false;
			if(h < map[nextY][nextX])
			{
				minHeight = Math.min(map[nextY][nextX], minHeight);
				continue;
			}
			
			if(!DFS(nextY, nextX, h))
				return false;
		}
		
		return true;
	}
	public static void main(String[] args)throws Exception{
		ArrayList<int[]> list	= new ArrayList<>();
		Y		= read();
		X		= read();
		map		= new int[Y+2][X+2];
		check	= new boolean[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			for(int x=1; x<=X; x++)
			{
				map[y][x] = System.in.read() - '0';
				list.add(new int[] {y, x, map[y][x]});
			}
			System.in.read();
		}
		
		Collections.sort(list,(a,b)-> a[2] - b[2]);
		
		for(int[] node : list)
			if(!check[node[0]][node[1]])
			{
				visit		= new boolean[Y+2][X+2];
				position	= new ArrayList<>();
				minHeight	= 10;

				if( DFS(node[0], node[1], node[2]) )
				{
					result += (minHeight - node[2]) * position.size();
					for(int[] p : position)
						map[p[0]][p[1]] = minHeight;
				}
				for(int[] p : position)
					check[p[0]][p[1]]= true;
			}

		System.out.print(result);
	}
}