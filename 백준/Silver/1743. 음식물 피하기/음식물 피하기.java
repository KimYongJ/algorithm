// https://github.com/KimYongJ/algorithm

class Main{
	static int y, x, Y, X, K, MAX;
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static boolean visit[][];
	
	// 빠른 입력을 위한 함수
	public static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
	public static int DFS(int i, int j) {
		
		visit[i][j] = false;
		int cnt = 1;
		
		for(int n=0; n<4; n++) 
		{
			y = i + dy[n];
			x = j + dx[n];
			if(y>=1 && x>=1 && y<Y && x<X && visit[y][x]) 
				cnt += DFS(y,x);
			
		}
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		Y 				= read()+1;
		X 				= read()+1;
		K 				= read();
		visit 			= new boolean[Y][X];
		for(int i=0; i<K; i++) 
		{
			y 			= read();
			x 			= read();
			visit[y][x] = true;
		}
		
		for(int i=1; i<Y; i++)
			for(int j=1; j<X; j++)
				if(visit[i][j]) 
				{
					int num = DFS(i,j);
					if(MAX < num)
						MAX = num;
				}

		System.out.println(MAX);
	}
}