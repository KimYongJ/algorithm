//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5549
class Main{
	public static void main(String[] args)throws Exception{
		int Y = read();	// 1<=천
		int X = read();	// 1<=천
		int K = read();	// 1<=십만
		int psum[][][] = new int[3][Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			for(int x=1; x<=X; x++)
			{
				int idx = 0;
				switch(System.in.read())
				{
				case 'O': idx = 1; break;
				case 'I': idx = 2;
				}
				
				psum[idx][y][x] = 1;
				
				psum[0][y][x] += psum[0][y-1][x] + psum[0][y][x-1] - psum[0][y-1][x-1];
				psum[1][y][x] += psum[1][y-1][x] + psum[1][y][x-1] - psum[1][y-1][x-1];
				psum[2][y][x] += psum[2][y-1][x] + psum[2][y][x-1] - psum[2][y-1][x-1];
			}
			System.in.read();
		}
		
		StringBuilder sb = new StringBuilder();
		while(K-->0)
		{
			int y1	= read();
			int x1	= read();
			int y2	= read();
			int x2	= read();
			int a	= psum[0][y2][x2] - psum[0][y2][x1-1] - psum[0][y1-1][x2] + psum[0][y1-1][x1-1];
			int b	= psum[1][y2][x2] - psum[1][y2][x1-1] - psum[1][y1-1][x2] + psum[1][y1-1][x1-1];
			int c	= psum[2][y2][x2] - psum[2][y2][x1-1] - psum[2][y1-1][x2] + psum[2][y1-1][x1-1];
			sb.append(a).append(' ').append(b).append(' ').append(c).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}