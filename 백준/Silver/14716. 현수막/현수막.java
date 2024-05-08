// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}, {-1,-1},{-1,1},{1,-1},{1,1}};
	static int Y, X, cnt, nextY, nextX;
	static boolean visit[][];
	public static void DFS(int y, int x) {
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(visit[nextY][nextX]) 
			{
				visit[nextY][nextX] = false;
				DFS(nextY, nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		visit 	= new boolean[Y+2][X+2];
		
		for(int y=1; y<=Y; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++) {
				visit[y][x] = Integer.parseInt(st.nextToken()) == 1;
			}
		}
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(visit[y][x]) 
				{
					cnt++;
					visit[y][x] = false;
					DFS(y,x);
				}

		System.out.println(cnt);
	}
}