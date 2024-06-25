// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int result = 26;
	static int oneCnt;
	static int map[][];
	static int paper[] = {0,5,5,5,5,5};
	
	public static void change(int y, int x, int len, int before, int after) {
		for(int y1=y; y1<y+len; y1++) 
		{
			for(int x1=x; x1<x+len; x1++) 
			{
				if(map[y1][x1] == before) 
				{
					map[y1][x1] = after;
				}
			}
		}
	}
	public static boolean validate(int len, int y, int x) {
		if(y+len > 10 || x+len > 10) {
			return false;
		}
		for(int y1=y; y1<y+len; y1++) 
		{
			for(int x1=x; x1<x+len; x1++) 
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
		if(x == 10) {
			x = 0;
			y +=1;
		}
		if(y == 10) {
			result = Math.min(result,  usepaper);
			return;
		}
		if(map[y][x] == 0) 
		{
			backtracking(usepaper, y, x+1);
		}else {
			for(int i=5; i>=1; i--) 
			{
				if(validate(i,y,x) && paper[i] > 0) {
					paper[i]--;
					change(y,x,i,1,0);
					backtracking(usepaper+1, y, x+1);
					change(y,x,i,0,1);
					paper[i]++;
				}
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
				if(map[y][x] == 1)
				{
					oneCnt++;
				}
			}
		}
		
		backtracking(0,0,0); // 순서 : 사용한 종이개수, y좌표, x좌표
		
		System.out.print(result == 26 ? -1 : result);
	}
}