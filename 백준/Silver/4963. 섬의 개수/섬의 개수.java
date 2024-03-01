
//https://github.com/KimYongJ/algorithm

class Main{
	public static void main(String[] args)throws Exception{new Main().solution();}
	
	int X,Y, nextX, nextY, land, map[][];
	int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1},  {-1,-1},{-1,1},{1,-1},{1,1}};
	StringBuilder sb = new StringBuilder();
	
    // 빠른 입력을 위해 만든 함수
    public static int read() throws Exception 
    {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}

	public void DFS(int y, int x) 
	{
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(map[nextY][nextX] == 1) 
			{
				map[nextY][nextX] = 0;
				DFS(nextY, nextX);
			}
		}
	}
	public void solution() throws Exception
	{

		while(true) 
		{
			X = read();
			Y = read();
			land = 0;
			
			if(X==0 && Y==0)
				break;
			
			map = new int[Y+2][X+2];
			
			for(int y=1; y<=Y; y++) 
				for(int x=1; x<=X; x++) 
					map[y][x] = read();
			
			for(int y=1; y<=Y; y++)
				for(int x=1; x<=X; x++)
					if(map[y][x] == 1) 
					{
						land++;
						map[y][x] = 0;
						DFS(y,x);
					}

			sb.append(land).append('\n');
			
		}
		System.out.println(sb);
	}
}