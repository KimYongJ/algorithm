//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1749
class Main{
	public static void main(String[] args)throws Exception{
		int Y		= read();
		int X		= read();
		int map[][] = new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = read() + map[y-1][x] + map[y][x-1] - map[y-1][x-1];

		int ans = ~(1<<30);
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				for(int y1=0; y1<y; y1++)
					for(int x1=0; x1<x; x1++)
						ans = Math.max(ans, map[y][x] - map[y][x1] - map[y1][x] + map[y1][x1]);

		System.out.print(ans);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}
