// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	
	static final int 	dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 			Y, X, K, DEPTH, result, dp[][][];
	static char 		str[], map[][];
	// DFS는 자기 하위에 완성된 문자열을 몇개 찾을 수 있는지 반환한다.
	public static int DFS(int y, int x, int depth) {
		if(dp[depth][y][x] != -1)	// 기방문 했으면 
			return dp[depth][y][x];	// 해당 깊이의 해당 좌표로 왓을 때 몇개를 방문가능한지 반환
		if(depth == DEPTH)			// 문자열 끝가지 도달하면 1 반환
			return 1;
	
		dp[depth][y][x] = 0;
		int cnt = 0;
		int nextY, nextX, nextDepth = depth + 1;
		for(int xy[] : dxy) {
			for(int i=1; i<=K; i++) {
				nextY = y + (xy[0]*i);
				nextX = x + (xy[1]*i);
				if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X && map[nextY][nextX] == str[nextDepth]) {
					cnt += DFS(nextY, nextX, nextDepth);
				}
			}
		}
		return dp[depth][y][x] = cnt;
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 	= Integer.parseInt(st.nextToken());
		X 	= Integer.parseInt(st.nextToken());
		K 	= Integer.parseInt(st.nextToken());
		map = new char[Y][X];
		dp	= new int[81][Y][X];
		
		for(int y=0; y<Y; y++)
		map[y]	= br.readLine().toCharArray();	// 맵의 문자열 
		str		= br.readLine().toCharArray();	// 최종 문자열 
		DEPTH 	= str.length-1;					// 문자열의 길이
		
		for(int i=0; i<81; i++)
			for(int j=0; j<Y; j++)
				Arrays.fill(dp[i][j], -1);
		
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if(map[y][x] == str[0])
					result += DFS(y,x,0);
		
		System.out.print(result);
	}
}