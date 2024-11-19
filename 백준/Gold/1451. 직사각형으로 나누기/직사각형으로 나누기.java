//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1451
class Main{
	
	static long max;
	static int Y, X;
	static int[][] map, prevSum;

	public static void main(String[] args)throws Exception{
		Y		= read();
		X		= read();
		map		= new int[Y+2][X+2];
		prevSum	= new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			for(int x=1; x<=X; x++)
			{
				map[y][x] = System.in.read() - '0';
				prevSum[y][x] = map[y][x] + prevSum[y-1][x] + prevSum[y][x-1] - prevSum[y-1][x-1]; 
			}
			System.in.read();
		}
		
		// 수평 분할
		for(int y1=1; y1<=Y-2; y1++)
			for(int y2=y1+1; y2<=Y-1; y2++)
			{
				int sum1 = getSum(1, 1, y1, X);
				int sum2 = getSum(y1+1, 1, y2, X);
				int sum3 = getSum(y2+1, 1, Y, X);
				max = Math.max(max, (long)sum1*sum2*sum3);
			}
		// 수직 분할
		for(int x1=1; x1<=X-2; x1++)
			for(int x2=x1+1; x2<=X-1; x2++)
			{
				int sum1 = getSum(1, 1, Y, x1);
				int sum2 = getSum(1, x1+1, Y, x2);
				int sum3 = getSum(1, x2+1, Y, X);
				max = Math.max(max, (long)sum1*sum2*sum3);
			}
		
		//수평 나누고, 가로로 나눔
		for(int y=1;y<=Y-1; y++)
		{
			// 수평 나누고, 밑에 공간을 2개로 나눔
			int sum1 = getSum(1, 1, y, X);
			// 수평 나누고, 위에 공간을 2개로 나눔
			int sum11= getSum(y+1, 1, Y, X);
			for(int x=1; x<=X-1; x++)
			{
				// 밑에 두공간
				int sum2 = getSum(y+1, 1, Y, x);
				int sum3 = getSum(y+1, x+1, Y, X);
				max = Math.max(max, (long)sum1*sum2*sum3);
				// 위에 두공간
				int sum22= getSum(1, 1, y, x);
				int sum33= getSum(1, x+1, y, X);
				max = Math.max(max, (long)sum11*sum22*sum33);
			}
		}
		// 수직으로 나누고, 좌우에 공간을 반으로 가름
		for(int x=1; x<=X-1; x++)
		{
			// 수직 나누고 오른쪽을 분할함
			int sum1 = getSum(1,1,Y,x);
			// 수직 나누고 왼쪽을 분할함
			int sum11= getSum(1,x+1,Y,X);
			for(int y=1; y<=Y-1; y++)
			{
				int sum2 = getSum(1,x+1,y,X);
				int sum3 = getSum(y+1,x+1, Y, X);
				max = Math.max(max, (long)sum1*sum2*sum3);
				
				int sum22= getSum(1,1,y, x);
				int sum33= getSum(y+1,1, Y, x);
				max = Math.max(max, (long)sum11*sum22*sum33);
			}
		}
		
		System.out.print(max);
	}
	public static int getSum(int y1, int x1, int y2, int x2) {
		return prevSum[y2][x2] - prevSum[y2][x1-1] - prevSum[y1-1][x2] + prevSum[y1-1][x1-1];
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}