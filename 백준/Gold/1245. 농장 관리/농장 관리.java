// https://github.com/kimyongj/algorithm
class Main{
	
	static int 		dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}, {-1,-1},{-1,1},{1,-1},{1,1}};
	static int 		Y, X, result, nextY, nextX, map[][];
	static boolean 	flag, visit[][];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int base,int y, int x) {
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(map[nextY][nextX] == base && !visit[nextY][nextX]) 
			{
				visit[nextY][nextX] = true;
				DFS(base, nextY, nextX);
			}else if(map[nextY][nextX] > base)
				flag = false;
			
		}
	}
	public static void main(String[] args)throws Exception{
		Y 		= read();
		X		= read();
		map		= new int[Y+2][X+2];
		visit 	= new boolean[Y+2][X+2];
		for(int y=1; y<=Y; y++) 
			for(int x=1; x<=X; x++) 
				if((map[y][x] = read()) == 0)
					visit[y][x] = true;
			
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(!visit[y][x]) 
				{
					flag = true;
					DFS(map[y][x],y,x);
					if(flag)
						result++;
				}

		System.out.print(result);
	}
}