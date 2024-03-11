// https://github.com/KimYongJ/algorithm

class Main{
	static int nextY, nextX, cnt, map[][], dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean visit[];
	// 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void BACK(int depth, int y , int x, int sum) {
		if(depth == 5) 
		{
			if(!visit[sum]) 
			{
				visit[sum] = true;
				cnt++;
			}
			return;
		}
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(map[nextY][nextX] != -1)
				BACK(depth + 1, nextY, nextX, sum*10 + map[nextY][nextX]);
		}
		
	}
	public static void main(String[] args)throws Exception{
		map = new int[7][7];
		visit = new boolean[1000000];
		for(int i=0; i<7; i++)			// 패딩 넣기
			map[0][i] = map[i][0] = 
			map[i][6] = map[6][i] = -1;
		
		for(int y=1; y<=5; y++) 
			for(int x=1; x<=5; x++)
				map[y][x] = read();
		

		for(int y=1; y<=5; y++)
			for(int x=1; x<=5; x++) 
				BACK(0,y,x, map[y][x]);
		System.out.println(cnt);
	}
}