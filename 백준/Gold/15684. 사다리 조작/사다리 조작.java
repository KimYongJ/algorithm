// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int result = Integer.MAX_VALUE;
	static int Y, X, H;
	static boolean map[][];
	// 사다리가 유효한지 체크
	public static void validate(int cnt) {
		// x부터 시작
		for(int x=1; x<X; x++) {
			int startX = x;
			for(int y=0; y<H; y++) {
				if(map[y][startX]) 
				{
					startX++;
				}else if(map[y][startX-1]) 
				{
					startX--;
				}
			}
			if(x != startX)
				return;
		}
		result = Math.min(cnt, result);
	}
	public static void backtracking(int depth, int y, int x) {
		validate(3-depth); // 유효성 검증
		if(x==X) 
		{
			y += 1;
			x = 1;
		}
		if(y==H || depth == 0) {
			return;
		}
		
		for(; y<H; y++) 
		{
			for(x=1; x<X; x++) 
			{
				if(!map[y][x] && !map[y][x-1] && !map[y][x+1]) {
					map[y][x] = true;
					backtracking(depth - 1, y, x+1);
					map[y][x] = false;
				}
			}
		}
	}

	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken())+1; // 세로선(가로인덱스)
		Y = Integer.parseInt(st.nextToken());	// 가로선(세로인덱스)
		H = Integer.parseInt(st.nextToken());	// 하나의 세로선에 있어야 하는 다리 개수 최대 값
		map = new boolean[H][X+1];
		
		for(int y=0; y<Y; y++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 세로 인덱스
			int b = Integer.parseInt(st.nextToken()); // 가로 인덱스
			map[a-1][b] = true;	// 사다리 체크
		}
		// 조합
		backtracking(3,0,1); // 순서 : depth, y, x
		
		System.out.print(result == Integer.MAX_VALUE ? -1 : result);
	}
	
}