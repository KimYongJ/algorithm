// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, sheep, wolf, nextY, nextX;
	static char map[][];
	static boolean visit[][];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void DFS(int y, int x) {
		visit[y][x] = true;
		if(map[y][x] == 'o') 		sheep++;
		else if(map[y][x] == 'v')	wolf++;
		for(int xy[] : dxy) {
			nextY = xy[0] + y;
			nextX = xy[1] + x;
			if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X && !visit[nextY][nextX] && map[nextY][nextX] != '#')
				DFS(nextY, nextX);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		map 	= new char[Y][X];
		visit 	= new boolean[Y][X];
		
		for(int y=0; y<Y; y++) 
			map[y] = br.readLine().toCharArray();
		
		int cntS=0, cntW=0;
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if(!visit[y][x] && map[y][x] != '#') 
				{
					sheep = wolf = 0;
					DFS(y,x);
					if(sheep > wolf)
						cntS += sheep;
					else
						cntW += wolf;
				}
		System.out.printf("%d %d",cntS, cntW);
	}
}
