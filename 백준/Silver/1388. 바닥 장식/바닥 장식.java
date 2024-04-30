// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int 		xy[] = {1,-1};
	static int 		Y, X, cnt, nextY, nextX;
	static boolean 	map[][],	// - 는 true, |는 false
					visit[][];
	public static void DFS(int y, int x, boolean type) {
		if(type) // true는 '-'모양, 가로로 탐색 
		{
			for(int p : xy) {
				nextX = p + x;
				if(nextX>=0 && nextX<X && !visit[y][nextX] && map[y][nextX] == type) {
					visit[y][nextX] = true;
					DFS(y, nextX, type);
				}
			}
		}else {
			for(int p : xy) {
				nextY = p + y;
				if(nextY>=0 && nextY<Y && !visit[nextY][x] && map[nextY][x] == type) {
					visit[nextY][x] = true;
					DFS(nextY, x, type);
				}
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