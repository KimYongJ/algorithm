// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	static int result;
	static int N;
	static int map[][];
	static int original[][];
	static int order[];
	
	public static void move(int m) {
		// 블록을 내린다.
		if(m == 0) {
			for(int x=0; x<N; x++) {
				for(int y=N-2; y>= 0; y--) {
					int nextY = y;
					while(nextY < N-1 &&map[nextY][x] > 0 && map[nextY+1][x] == 0) {
						map[nextY+1][x] = map[nextY][x];
						map[nextY][x] = 0;
						nextY++;
					}
				}
			}
		}
		// 블록을 올린다.
		else if(m == 1) {
			for(int x=0; x<N; x++) {
				for(int y=1; y<N; y++) {
					int nextY = y;
					while(nextY > 0 && map[nextY][x] > 0 && map[nextY-1][x] == 0) {
						map[nextY-1][x] = map[nextY][x];
						map[nextY][x] = 0;
						nextY--;
					}
				}
			}
		}
		// 블록을 왼쪽으로 보낸다.
		else if(m == 2) {
			for(int y=0; y<N; y++) {
				for(int x=1; x<N; x++) {
					int nextX = x;
					while(nextX > 0 && map[y][nextX] > 0 && map[y][nextX-1] == 0) {
						map[y][nextX-1] = map[y][nextX];
						map[y][nextX] = 0;
						nextX--;
					}
				}
			}
		}
		// 블록을 오른쪽으로 보낸다.
		else {
			for(int y=0; y<N; y++) {
				for(int x=N-2; x>=0; x--) {
					int nextX = x;
					while(nextX <N-1 && map[y][nextX] > 0 && map[y][nextX+1] == 0) {
						map[y][nextX+1] = map[y][nextX];
						map[y][nextX] = 0;
						nextX++;
					}
				}
			}
		}
	}
	public static void sum(int m) {
		// 블록을 내렸을 때 
		if(m == 0) { 
			for(int x=0; x<N; x++) {
				for(int y=N-1; y>0; y--) {
					if(map[y][x] > 0 && map[y][x] == map[y-1][x]) {
						map[y][x] *=2;
						map[y-1][x]=0;
					}
					
				}
			}
		}
		// 블록을 올렸을 때 
		else if(m == 1) {
			for(int x=0; x<N; x++) {
				for(int y=0; y<N-1; y++) {
					if(map[y][x] > 0 && map[y][x] == map[y+1][x]) {
						map[y][x] *=2;
						map[y+1][x]=0;
					}
				}
			}
		}
		// 블록을 왼쪽으로 보냈을 때 
		else if(m==2) {
			for(int y=0; y<N; y++) {
				for(int x=0; x<N-1; x++) {
					if(map[y][x] > 0 && map[y][x] == map[y][x+1]) {
						map[y][x] *= 2;
						map[y][x+1]=0;
					}
				}
			}
		}
		// 블록을 오른쪽으로 보냈을 때 
		else {
			for(int y=0; y<N; y++) {
				for(int x=N-1; x>0; x--) {
					if(map[y][x] > 0 && map[y][x] == map[y][x-1]) {
						map[y][x] *=2;
						map[y][x-1]=0;
					}
				}
			}
		}
	}
	public static void cal() {
		map = new int[N][N];
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				map[y][x] = original[y][x];

		for(int m : order) 
		{
			move(m);	// 해당 방향으로 이동
			sum(m);		// 붙어있는 것들에 대해 하나로 합친다.
			move(m);	// 해당 방향으로 이동 
		}
		
		// 이하 결과 계산
		for(int y=0; y<N; y++) 
			for(int x=0; x<N; x++)
				result = Math.max(map[y][x], result);
	}
	public static void backtracking(int depth) {
		if(depth < 0) {
			cal();
			return;
		}
		for(int i=0; i<4; i++) // 순서 : 내리고, 올리고, 왼쪽, 오른쪽 
		{
			order[depth] = i;
			backtracking(depth - 1);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N			= Integer.parseInt(br.readLine());
		order		= new int[5];
		original	= new int[N][N];
		
		for(int y=0; y<N; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) 
			{
				original[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		backtracking(4);
		
		System.out.print(result);
	}
}