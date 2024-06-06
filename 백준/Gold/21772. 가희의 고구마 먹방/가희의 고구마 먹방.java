//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int result;
	static int	Y, X, T;
	static char map[][];
	
	public static void backtracking(int depth, int cnt, int y, int x) {
		if(depth == 0) {
			if(result < cnt) result = cnt;
			return;
		}
		int ny, nx;
		for(int xy[] : dxy) {
			ny = y + xy[0];
			nx = x + xy[1];
			if(ny>=0 && nx>=0 && ny<Y && nx<X && map[ny][nx] !='#') {
				if(map[ny][nx] == 'S') {
					map[ny][nx] = '.';
					backtracking(depth-1, cnt + 1, ny, nx);
					map[ny][nx] = 'S';
				}
				else
					backtracking(depth-1, cnt, ny,nx);
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		T 		= Integer.parseInt(st.nextToken());
		map		= new char[Y][X];
		
		int sy=0, sx=0;
		for(int y=0; y<Y; y++) {
			String str = br.readLine();
			for(int x=0; x<X; x++) {
				map[y][x] = str.charAt(x);
				if(map[y][x] == 'G') {
					sy = y;
					sx = x;
				}
			}
		}
		
		backtracking(T, 0, sy, sx);
		
		System.out.print(result);
	}
}