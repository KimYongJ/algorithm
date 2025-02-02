//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17484
//1초 / 256mb

class Main{
	public static void main(String[] args)throws Exception{
		int Y		= read();
		int X		= read();
		int dp[][][]= new int[3][Y+1][X+1];

		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
			{
				dp[0][y][x] = dp[1][y][x] = dp[2][y][x] = read();
				// x가 1이면 왼쪽위로 이동 불가하므로, 왼쪽 위에서 온 값인 dp[0][y][x]를 큰 값으로 설정
				if(x==1) dp[0][y][x] = 1_000;
				// x가 X이면 오른쪽위로 이동이 불가하므로, 오른쪽 위에서 온 값인 dp[2][y][x]를 큰 값으로 설정
				if(x==X) dp[2][y][x] = 1_000;
			}

		
		for(int y=2; y<=Y; y++)
			for(int x=1; x<=X; x++)
			{
				// dp[0]은 왼쪽 위에서 온것이다.
				if(x != 1)
					dp[0][y][x] += Math.min(dp[1][y-1][x-1], dp[2][y-1][x-1]);
				// dp[1]은 자기 바로 위에서 온것이다.
				dp[1][y][x] += Math.min(dp[0][y-1][x], dp[2][y-1][x]);
				// dp[2]는 오른쪽 위에서 온것이다.
				if(x + 1 <= X)
					dp[2][y][x] += Math.min(dp[0][y-1][x+1], dp[1][y-1][x+1]);
			}

		
		int min = 1_000;
		for(int x=1; x<=X; x++)
			for(int i=0; i<3; i++)
				min = Math.min(dp[i][Y][x], min);
		
		System.out.print(min);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}