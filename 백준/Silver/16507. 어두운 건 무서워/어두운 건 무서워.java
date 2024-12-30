//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16507
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int Y		= read();	// 1<=1000
		int X		= read();	// 1<=1000
		int Q		= read();	// 질의개수 1<=만
		int map[][] = new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = read() + map[y-1][x] + map[y][x-1] - map[y-1][x-1];
		
		while(Q-->0)
		{
			int y1	= read();
			int x1	= read();
			int y2	= read();
			int x2	= read();
			int cnt	= (y2-y1+1) * (x2-x1+1);
			sb.append((map[y2][x2] - map[y2][x1-1] - map[y1-1][x2] + map[y1-1][x1-1])/cnt).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
