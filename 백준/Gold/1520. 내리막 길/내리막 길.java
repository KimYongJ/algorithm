// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, eY, eX, dp[][], map[][], result; 
	public static int DFS(int y, int x) {
		if(y==eY && x==eX)
			return 1;
		
		if(dp[y][x] != -1)
			return dp[y][x];
		
		dp[y][x] = 0;
		for(int xy[]: dxy) {
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(nextY >=0 && nextX >=0 && nextY<Y && nextX<X && map[nextY][nextX] < map[y][x])
				dp[y][x] += DFS(nextY, nextX);
		}
		return dp[y][x];
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y   	= Integer.parseInt(st.nextToken());
		X   	= Integer.parseInt(st.nextToken());
		eY  	= Y-1;
		eX  	= X-1;
		dp  	= new int[Y][X];
		map		= new int[Y][X];
		for(int y=0; y<Y; y++) {
			st 	= new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				dp[y][x] = -1;
			}
		}
		DFS(0,0);
		
		System.out.println(dp[0][0]);
	}
}