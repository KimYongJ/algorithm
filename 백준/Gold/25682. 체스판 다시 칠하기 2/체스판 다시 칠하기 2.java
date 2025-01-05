//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25682

class Main{
	
	static int Y, X, K;
	static int board[][];
	
	public static void main(String[] args)throws Exception{
		Y		= read();
		X		= read();
		K		= read();
		board	= new int[Y+1][X+1];
		
		for(int y=1; y<=Y; y++) {
			for(int x=1; x<=X; x++) {
				board[y][x] = System.in.read();
			}
			System.in.read();
		}
		
		System.out.print(Math.min(find('B'), find('W')));
	}
	public static int find(char flag)
	{
		int psum[][] = new int[Y+1][X+1];

		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
			{
				if((y + x)%2 == 0)
				{
					if(flag != board[y][x])psum[y][x] = 1;
				}
				else
				{
					if(flag == board[y][x])psum[y][x] = 1;
				}
				psum[y][x] += psum[y-1][x] + psum[y][x-1] - psum[y-1][x-1];
			}

		int count = 1<<30;

		for(int y=1; y+K-1<=Y; y++)
			for(int x=1; x+K-1<=X; x++)
			{
				int y1 = y + K - 1;
				int x1 = x + K - 1;
				int value = psum[y1][x1] - psum[y1][x-1] - psum[y-1][x1] + psum[y-1][x-1];
				count = Math.min(count, value);
			}

		return count;
	}
    static int read() throws Exception {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}