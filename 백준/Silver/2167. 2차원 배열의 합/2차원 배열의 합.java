//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2167
class Main{
    static int read() throws Exception {
        int c, n = System.in.read();
        boolean minus = true;
        if (n > 47) {minus = false;
        n &= 15;} else n = 0;
        while ((c = System.in.read()) > 47) n = (n << 3) + (n << 1) + (c & 15);
        return minus? -1 * n: n;
    }
	public static void main(String[] args)throws Exception{
		int Y		= read();
		int X		= read();
		int psum[][]= new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				psum[y][x] += psum[y][x-1] + psum[y-1][x] - psum[y-1][x-1] + read();
		
		StringBuilder sb = new StringBuilder();
		int N = read();
		while(N-->0)
		{
			int y1 = read();
			int x1 = read();
			int y2 = read();
			int x2 = read();
			sb.append(psum[y2][x2] - psum[y2][x1-1] - psum[y1-1][x2] + psum[y1-1][x1-1]).append('\n');
		}
		System.out.print(sb);
	}
}