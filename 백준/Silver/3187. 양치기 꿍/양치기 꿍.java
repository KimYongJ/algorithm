//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, s, w, sCnt, wCnt, nextY, nextX;
	static char map[][];
	public static boolean check(int y, int x) {
		if(y>=0 && x>=0 && y<Y && x<X && map[y][x] != '#') 
		{
			if(map[y][x] == 'v') wCnt++;
			if(map[y][x] == 'k') sCnt++;
			map[y][x] = '#';
			return true;
		}
		return false;
	}
	public static void DFS(int y, int x) {
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(check(nextY, nextX))
				DFS(nextY, nextX);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 	= Integer.parseInt(st.nextToken());
		X 	= Integer.parseInt(st.nextToken());
		map = new char[Y][X];
		
		for(int y=0; y<Y; y++)
			map[y] = br.readLine().toCharArray();
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if(map[y][x] != '#') 
				{
					check(y,x);
					DFS(y,x);
					if(sCnt > wCnt)	s += sCnt;
					else			w += wCnt;
					sCnt = wCnt = 0;
				}

		System.out.printf("%d %d", s,w);
	}
}

