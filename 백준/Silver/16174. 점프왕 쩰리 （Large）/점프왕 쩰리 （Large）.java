// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{1,0},{0,1}};
	static int N, nextY, nextX, map[][];
	static boolean visit[][];
	public static boolean DFS(int y, int x, int move) {
		if(map[y][x] == -1)	return true;
		if(visit[y][x])		return false;
		
		visit[y][x] = true;
		
		for(int xy[] : dxy) 
		{
			nextY = y + (xy[0]*move);
			nextX = x + (xy[1]*move);
			if(nextY>=0 && nextX>=0 && nextY<N && nextX<N 
				&& !visit[nextY][nextX] && DFS(nextY, nextX, map[nextY][nextX]))
				return true;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.print(DFS(0,0,map[0][0]) ? "HaruHaru" : "Hing");
	}
}