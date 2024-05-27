// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static final int 	MAX = 5;
	static final int 	dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 			sy, sx, map[][];
	
	public static boolean backtracking(int depth, int y, int x, int cnt) {
		if(cnt >= 2)	return true;
		if(depth == 3) 	return false;
		
		int ny, nx, tmp;
		for(int xy[] : dxy) 
		{
			ny = y + xy[0];
			nx = x + xy[1];
			tmp= map[ny][nx];
			if(tmp != -1) 
			{
				map[ny][nx] = -1;
				if(backtracking(depth + 1, ny, nx, cnt + tmp)) return true;
				map[ny][nx] = tmp;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[MAX+2][MAX+2];
		
		// 외부를 벽으로 막음
		for(int i=0; i<7; i++)
			map[i][0] = map[0][i] = map[i][6] = map[6][i] = -1;
		
		for(int y=1; y<=MAX; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=MAX; x++)
				map[y][x] = Integer.parseInt(st.nextToken());	
		}
		
		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken())+1;
		sx = Integer.parseInt(st.nextToken())+1;
		
		int cnt = map[sy][sx] == 1 ? 1 : 0;
		int result = 0;
		
		map[sy][sx] = -1;
		
		if(backtracking(0,sy,sx,cnt))
			result = 1;
		
		System.out.print(result);
	}
}