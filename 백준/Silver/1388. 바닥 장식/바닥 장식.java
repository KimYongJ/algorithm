// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int 		xy[][] = {{0,1},{1,0},{0,-1},{-1,0}};
	static int 		Y, X, cnt, nextY, nextX;
	static boolean 	map[][],	// - 는 true, |는 false
					visit[][];
	public static void DFS(int y, int x, boolean type) {
		for(int i=type?0:1;  i<4;  i+=2) 
		{
			nextY = y + xy[i][0];
			nextX = x + xy[i][1];
			if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X && !visit[nextY][nextX] && map[nextY][nextX] == type) 
			{
				visit[nextY][nextX] = true;
				DFS(nextY, nextX, type);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		map 	= new boolean[Y][X];
		visit 	= new boolean[Y][X];
		
		String str;
		for(int y=0; y<Y; y++) 
		{
			str = br.readLine();
			for(int x=0; x<X; x++)
				map[y][x] = str.charAt(x)=='-';
		}
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if(!visit[y][x]) 
				{
					visit[y][x] = true;
					cnt++;
					DFS(y,x,map[y][x]);
				}

		System.out.print(cnt);
	}
}