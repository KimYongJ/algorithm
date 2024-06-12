// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int		result = 0;
	static int		dxy[][] = {{0,-1,-1,0},{0,1,-1,0},{0,-1,1,0},{0,1,1,0}};
	static int		Y, X;
	static int		map[][];
	static boolean	visit[][];
	
	public static void backtracking(int y, int x, int sum) {
		if(x > X) 
		{
			x = 1;
			y +=1;
		}
		if(y > Y) {
			if(result < sum)
				result = sum;
			return;
		}
		if(!visit[y][x]) 
		{
			int y1,x1,y2,x2;
			for(int i=0; i<4; i++)
			{
				y1 = y + dxy[i][0];
				x1 = x + dxy[i][1];
				y2 = y + dxy[i][2];
				x2 = x + dxy[i][3];
				if(!visit[y1][x1] && !visit[y2][x2]) 
				{
					visit[y][x]		= true;
					visit[y1][x1]	= true;
					visit[y2][x2]	= true;
					backtracking(y, x + 1, sum + (map[y][x]<<1) + map[y1][x1] + map[y2][x2] );
					visit[y][x]		= false; 
					visit[y1][x1]	= false;
					visit[y2][x2]	= false;
				}
			}
		}
		backtracking(y, x + 1, sum);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y		= Integer.parseInt(st.nextToken());
		X		= Integer.parseInt(st.nextToken());
		map		= new int[Y+2][X+2];
		visit	= new boolean[Y+2][X+2];
		// 외곽 방문 처리 
		for(int y=0; y<Y+2; y++) visit[y][0] = visit[y][X+1] = true;
		for(int x=0; x<X+2; x++) visit[0][x] = visit[Y+1][x] = true;
		
		for(int y=1; y<=Y; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		backtracking(1,1,0);
		
		System.out.print(result);
	}
}