//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, sY, sX;
	static char map[][];
	static boolean flag, visit[][];
	public static void DFS(char base, int beforeY, int beforeX, int y, int x) {
		for(int xy[] : dxy) {
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X && map[nextY][nextX] == base && !flag) {
				if(!visit[nextY][nextX]) {
					visit[nextY][nextX] = true;
					DFS(base, y,x, nextY,nextX);
					visit[nextY][nextX] = false;
				}else if(beforeY != nextY && beforeX != nextX 
						&& sY==nextY && sX==nextX) {
					flag = true;
				}
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		flag	= false;
		map		= new char[Y][X];
		visit 	= new boolean[Y][X];
		
		for(int y=0; y<Y; y++)
			map[y] = br.readLine().toCharArray();
		Loop:
		for(int y=0; y<Y; y++) {
			for(int x=0; x<X; x++) {
				visit[y][x] = true;
				DFS(map[y][x], y,x,sY = y,sX = x);
				visit[y][x] = false;
				if(flag) 
					break Loop;
					
			}
		}
		
		System.out.print(flag ? "Yes" : "No");
	}
}