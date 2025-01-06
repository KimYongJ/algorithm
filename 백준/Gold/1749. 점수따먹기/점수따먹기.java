//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1749
class Main{
	public static void main(String[] args)throws Exception{
		int Y		= read();
		int X		= read();
		int map[][] = new int[Y+2][X+2];
		
		// 카데인 알고리즘 사용을 위해 행단위로 누적합을 구함
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = read() + map[y][x-1];

		int ans = ~(1<<30);
		for(int x=1; x<=X; x++)				// 왼쪽 오른쪽 열을 고정해놓고 y를 움직이면서 직사각형 중 가장 큰 것을 구한다.
			for(int x1=x; x1<=X; x1++)		// 왼쪽 오른쪽 열을 고정해놓고 y를 움직이면서 직사각형 중 가장 큰 것을 구한다.
			{
				int sum = 0;
				for(int y=1; y<=Y; y++)		// y를 움직이면서 가장 큰 직사각형을 구함
				{
					int row = map[y][x1] - map[y][x-1];
					
					sum += row;
					if(sum < row)
						sum = row;
					
					ans = Math.max(ans, sum);
				}
			}
		
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
