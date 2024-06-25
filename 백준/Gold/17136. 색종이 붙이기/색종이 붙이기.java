// https://github.com/kimyongj/algorithm

class Main{
	
	static int result = 26;
	static int map[][];
	static int paper[] = {0,5,5,5,5,5};
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void change(int y, int x, int len, int before, int after) {
		int ylen = y + len;
		int xlen = x + len;
		while(y<ylen) {
			for(int x1=x; x1<xlen; x1++) 
			{
				if(map[y][x1] == before) 
				{
					map[y][x1] = after;
				}
			}
			y++;
		}
	}
	public static boolean validate(int len, int y, int x) {
		int ylen = y + len;
		int xlen = x + len;
		if(ylen > 10 || xlen > 10) 
		{
			return false;
		}
		while(y<ylen) {
			for(int x1= x; x1<xlen; x1++) 
			{
				if(map[y][x1] == 0) 
				{
					return false;
				}
			}
			y++;
		}
		return true;
	}
	public static void backtracking(int usepaper, int y, int x) {
		if(result < usepaper) { return; } // 조기 종료
		if(x == 10) 
		{
			x = 0;
			y +=1;
		}
		if(y < 10 && map[y][x] == 0)  // 0인 경우 0이 아닌 위치 까지 y, x를 변화 시킴
		{
			while(y < 10 && map[y][x] == 0) 
			{
				x ++ ;
				if(x == 10) 
				{
					x = 0;
					y ++;
				}
			}
		}
		if(y == 10) // 마지막 도달시 값 저장 후 종료
		{
			result = Math.min(result,  usepaper);
			return;
		}
		for(int i=5; i>=1; i--) 
		{
			if(validate(i,y,x) && paper[i] > 0) 
			{
				paper[i]--;
				change(y,x,i,1,0);
				backtracking(usepaper+1, y, x+1);
				change(y,x,i,0,1);
				paper[i]++;
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		map = new int[10][10];
		
		for(int y=0; y<10; y++)
		{
			for(int x=0; x<10; x++)
			{
				map[y][x] = read();
			}
		}
		
		backtracking(0,0,0); // 순서 : 사용한 종이개수, y좌표, x좌표
		
		System.out.print(result == 26 ? -1 : result);
	}
}