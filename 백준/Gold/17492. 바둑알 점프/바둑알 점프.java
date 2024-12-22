//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17492
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}, {-1,-1},{-1,1},{1,-1},{1,1}};
	static boolean	visit[][];
	static int		map[][];
	static int		N, egg;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		map		= new int[N][N];
		visit	= new boolean[N][N];
		
		for(int y=0; y<N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken());
				if(map[y][x] == 2)
					++egg;
			}
		}
		
		back(egg);
		
		System.out.print("Impossible");
	}
	public static void back(int egg){
		if(egg == 1)
		{
			System.out.print("Possible");
			System.exit(0);
		}
		for(int y=1; y<N; y++) {
			for(int x=1; x<N; x++) {
				if(map[y][x] == 2) {
					
					for(int xy[] : dxy) {
						int nextY = y + xy[0];
						int nextX = x + xy[1];
						if(map[nextY][nextX] == 2) {
							int ny = nextY + xy[0];
							int nx = nextX + xy[1];
							if(1<=ny && 1<=nx && nx<N && ny<N && map[ny][nx] == 0) {
								map[y][x] = map[nextY][nextX] = 0;
								map[ny][nx] = 2;
								back(egg - 1);
								map[y][x] = map[nextY][nextX] = 2;
								map[ny][nx] = 0;
							}
						}
					}
					
				}
			}
		}
	}
}