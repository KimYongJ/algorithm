// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int dxy[][] = {{0,1},{1,0}};
	static int N, map[][], nextY, nextX;
	static boolean visit[][];
	public static void endCondition() {
		if(map[nextY][nextX]==-1) {
			System.out.println("HaruHaru");
			System.exit(0);
		}
	}
	public static void DFS(int y, int x) {
		for(int xy[] : dxy) {
			nextY = y + (xy[0] * map[y][x]);
			nextX = x + (xy[1] * map[y][x]);
			if(nextY<N && nextX<N && !visit[nextY][nextX]) {
				endCondition();
				visit[nextY][nextX] = true;
				DFS(nextY, nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		map 	= new int[N][N];
		visit 	= new boolean[N][N];
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		visit[0][0] = true;
		DFS(0,0);
		System.out.println("Hing");
	}
}