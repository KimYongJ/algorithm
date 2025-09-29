//https://www.acmicpc.net/problem/20061
//1초 512MB
//2 // 블록을 놓을 횟수(1<=10,000)
//1 1 1 // 블록크기(1:1칸, 2:가로2개, 3:세로2개), x(행), y(열)
//2 3 0
//답
//0
//6

class Main{
	
	static boolean map[][] = new boolean[10][10];
	static int score;
	
	public static void main(String[] args)throws Exception{
		int T = read();
		while(T-->0)
		{
			int s = read();// 블록크기(1:1칸, 2:가로2개, 3:세로2개)
			int y = read();// 행
			int x = read();// 열
			
			dropGreen(s, y, x); // 초록 보드에 떨어뜨린다.
			dropBlue(s, y, x);	// 파란 보드에 떨어드린다.

			deleteCol();// 파란 보드에 한열이 모두 차있으면 제거
			deleteRow();// 초록 보드에 한행이 모두 차있으면 제거

			// 연한 부분이 빌 때 까지 마지막행을 지운다.
			while(checkGreen())moveRow(9, 4);
			while(checkBlue()) moveCol(9, 4);
		}
		System.out.print(new StringBuilder().append(score).append('\n').append(cal()).toString());
	}
	static void dropGreen(int s, int y, int x) {
		if(s == 1) {
			int r = 4;
			while(r<=9 && !map[r][x]) ++r;
			map[r - 1][x] = true;
		}
		else if(s == 2) {
			int r = 4;
			while(r<=9 && !map[r][x] && !map[r][x + 1]) ++r;
			map[r - 1][x] = map[r - 1][x + 1] = true;
		}
		else if(s == 3) {
			int r = 5;
			while(r<=9 && !map[r][x]) ++r;
			map[r - 1][x] = map[r - 2][x] = true;
		}
	}
	static void dropBlue(int s, int y, int x) {
		if(s == 1) {
			int c = 4;
			while(c<=9 && !map[y][c]) ++c;
			map[y][c - 1] = true;
		}
		else if (s == 2)
		{
			 int c = 5;
			 while (c <= 9 && !map[y][c]) c++;
			 map[y][c - 1] = map[y][c - 2] = true;
		} else
		{
			 int c = 4;
			 while (c <= 9 && !map[y][c] && !map[y + 1][c]) c++;
			 map[y][c - 1] = map[y + 1][c - 1] = true;
		}
	}
	static boolean checkBlue() {
		for(int x=4; x<=5; x++)
			for(int y=0; y<=3; y++)
				if(map[y][x])
					return true;
		return false;
	}
	static boolean checkGreen() {
		for(int y=4; y<=5; y++)
			for(int x=0; x<=3; x++)
				if(map[y][x])
					return true;
		return false;
	}
	static void deleteCol() {
		// 행0~3, 열6~9
		for(int x=9; x>=4; x--)
			while(colCheckAndDel(x))// 해당 열 체크
			{
				moveCol(x, 4);// 해당 열 움직임, x에 대해 다시 한번 체크해야 하므로 x에 +1을 처리함
				score++;
			}
	}
	static void deleteRow() {
		// 행6~9 열 0~3
		for(int y=9; y>=4; y--)
		{
			while(rowCheckAndDel(y))
			{
				moveRow(y,4);
				score++;
			}
		}
	}
	static void moveRow(int y, int limit) {
		while(y!=limit)
		{
			for(int x=0; x<=3; x++)
				map[y][x] = map[y - 1][x];
			--y;
		}
		for(int x=0; x<=3; x++)
			map[limit][x] = false;
	}

	static void moveCol(int x, int limit) {
		while(x!=limit)
		{
			for(int y=0; y<=3; y++)
				map[y][x] = map[y][x - 1];
			--x;
		}
		for(int y=0; y<=3; y++)
			map[y][limit] = false;
	}
	static boolean rowCheckAndDel(int y) {
		boolean isBlock = true;
		
		for(int x=0; x<=3; x++)
			isBlock = isBlock && map[y][x];
		
		if(!isBlock) return false;
		
		for(int x=0; x<=3; x++)
			map[y][x] = false;
		
		return true;
	}
	static boolean colCheckAndDel(int x) {
		boolean isBlock = true;
		
		for(int y=0; y<=3; y++)// 해당 열에 블록이 있는지 체크
			isBlock = isBlock && map[y][x];
		
		if(!isBlock)return false;
		
		for(int y=0; y<=3; y++)
			map[y][x] = false;// 해당열 모두 false로
		
		return true;
	}
	static int cal() {
		int cnt = 0;
		for(int i=0; i<=3; i++)
		{
			for(int j=6; j<=9; j++)
			{
				if(map[i][j])++cnt;
				if(map[j][i])++cnt;
			}
		}
		return cnt;
	}
	static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}