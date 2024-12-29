//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/29718
class Main{
	public static void main(String[] args)throws Exception{
		int Y		= read();
		int X		= read();
		int map[][] = new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = read() + map[y][x-1] + map[y-1][x] - map[y-1][x-1];

		int max = 0;
		
		for(int x1=read(),x2=0; x1<=X; x1++,x2++)
			max = Math.max(max, map[Y][x1] - map[Y][x2]);

		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}