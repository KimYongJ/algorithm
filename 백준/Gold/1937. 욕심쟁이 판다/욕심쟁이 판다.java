// https://github.com/KimYongJ/algorithm

class Main{

	static int N, MAX, map[][], dp[][];
	static int dxy[][] = {{0,1},{1,0},{0,-1},{-1,0}};

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32)
	        n = (n << 3) + (n << 1) + (c & 15);
	    if (c == 13) System.in.read();
	    return n;
	}
	
	public static int DFS(int i, int j) {
		if(dp[i][j] != 0) // 이미 구한 곳이라면 스킵
			return dp[i][j];

		dp[i][j] = 1;// 첫 방문이면 한칸을 방문한 것이기 때문에 1 체크 
		
		int y, x;
		for(int[] xy : dxy) 
		{
			y = i + xy[0]; // 다음 좌표 
			x = j + xy[1]; // 다음 좌표
			
			// 범위를 벗어나는 경우 연산하지 않음( 입력시 패딩을 넣었기에 가능한 코드 )
			if(map[y][x] == 0) continue;
			
			// 다음 이동하는 곳이 대나무가 더 많아야 함
			if(map[i][j] < map[y][x])  
				// DFS를 계속 돌면서 가장 큰 값을 dp[i][j]에 담아 간다. 이 때 DFS에 1을 더하는 이유는, DFS를 재귀 호출하는 함수 자체를 방문한 것이기 때문에 +1을 해주는것이다.
				// 즉, DFS(y,x)는 y,x에서 시작해 이동할 수 있는 최대 칸수를 반환한다. 하지만 구해야 하는건 (i,j)위치에서 칸수를 구하는 것이기 때문에 +1을 해줌
				dp[i][j] = Math.max( dp[i][j], DFS(y,x)+1);  
		}
		return dp[i][j];
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		map 	= new int[N+2][N+2]; 	// 맵정보 입력받기
		dp 		= new int[N+2][N+2]; 	// 동서남북에 대해 뻗아나갈 수 있는 최고의 칸수 찾기
		
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++) 
				map[i][j] = read();

		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++) 
				MAX = Math.max(MAX, DFS(i,j));

		System.out.println(MAX);
		
	}
}