//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20002
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		int map[][] = new int[N+2][N+2];
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				map[y][x] = read() + map[y][x-1] + map[y-1][x] - map[y-1][x-1];

		int max = ~(1<<30);

		for(int len = 0; len<N; len++)
			for(int y=1; y+len<=N; y++)
				for(int x=1; x+len<=N; x++)
				{
					int y1 = y+len;
					int x1 = x+len;
					max = Math.max(max, map[y1][x1] - map[y1][x-1] - map[y-1][x1] + map[y-1][x-1]);
				}

		System.out.print(max);
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
