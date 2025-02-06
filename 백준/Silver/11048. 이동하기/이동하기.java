//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11048
//1초 / 256MB
class Main{
	public static void main(String[] args)throws Exception{
		int Y = read();// 1<=천
		int X = read();// 1<=천
		int map[][] = new int[Y+1][X+1];
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = read()
						+ Math.max(map[y-1][x], Math.max(map[y][x-1], map[y-1][x-1])); 

		System.out.print(map[Y][X]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
