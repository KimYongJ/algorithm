// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int result = 26;
	static int map[][];
	static int paper[] = {0,5,5,5,5,5};
	
	public static void change(int y, int x, int len, int before, int after) {
		int ylen = y + len;
		int xlen = x + len;
		for(int y1=y; y1<ylen; y1++) 
		{
			for(int x1=x; x1<xlen; x1++) 
			{
				if(map[y1][x1] == before) 
				{
					map[y1][x1] = after;
				}
			}
		}
	}
	public static boolean validate(int len, int y, int x) {
		int ylen = y + len;
		int xlen = x + len;
		if(ylen > 10 || xlen > 10) {
			return false;
		}
		for(int y1=y; y1<ylen; y1++) 
		{
			for(int x1=x; x1<xlen; x1++) 
			{
				if(map[y1][x1] != 1) 
				{
					return false;
				}
			}
		}
		return true;
	}
	public static void backtracking(int usepaper, int y, int x) {
		if(result < usepaper) 
			return;
		if(x == 10) 
		{
			x = 0;
			y +=1;
		}
		if(y < 10 && map[y][x] == 0)  // 0인 경우 0이 아닌 위치 까지 y, x를 변화 시킴
		{
			int nextY = y;
			int nextX = x;
			while(nextY < 10 && map[nextY][nextX] == 0) {
				nextX ++ ;
				if(nextX == 10) {
					nextX = 0;
					nextY ++;
				}
			}
			y = nextY;
			x = nextX;
		}
		if(y == 10) 
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
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[10][10];
		for(int y=0; y<10; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<10; x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		backtracking(0,0,0); // 순서 : 사용한 종이개수, y좌표, x좌표
		
		System.out.print(result == 26 ? -1 : result);
	}
}