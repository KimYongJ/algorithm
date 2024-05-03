// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, Q, L, area, sum, nextY, nextX, map[][];
	static boolean visit[][];
	public static int DFS(int y, int x) {
		int cnt = 1;
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(nextY>=0 && nextX>=0 && nextY<N && nextX<N &&map[nextY][nextX] >0 && !visit[nextY][nextX]) 
			{
				visit[nextY][nextX] = true;
				sum += map[nextY][nextX];
				cnt += DFS(nextY,nextX);
			}
		}
		return cnt;
	}
	public static int[][] rotate() {
		int result[][] = new int[N][N];
		for(int y=0; y<N; y+=L)
			for(int x=0; x<N; x+=L)
				for(int i=0; i<L; i++)
					for(int j=0; j<L; j++)
						result[y+j][x+L-i-1] = map[y+i][x+j];
		return result;
	}
	public static int[][] melt(){
		int result[][] = new int[N][N];
		for(int i=0; i<N; i++)
			result[i] = Arrays.copyOf(map[i], N);
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++) 
			{
				int cnt = 0;
				for(int xy[] : dxy) 
				{
					nextY = y + xy[0];
					nextX = x + xy[1];
					if(nextY>=0 && nextX>=0 && nextY<N && nextX<N &&map[nextY][nextX] >0)
						cnt++;
				}
				if(cnt <3)
					result[y][x] -= 1;
			}
		return result;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 	= (int)Math.pow(2, Integer.parseInt(st.nextToken())); // 크기 
		Q 	= Integer.parseInt(st.nextToken()); // 횟수
		map = new int[N][N];
		visit = new boolean[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; i++) 
		{
			L 	= (int)Math.pow(2,Integer.parseInt(st.nextToken()));
			map = rotate();
			map = melt();
		}
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				if(!visit[y][x] && map[y][x] > 0) {
					visit[y][x] = true;
					sum += map[y][x];
					area = Math.max(area,DFS(y,x));
				}

		StringBuilder sb = new StringBuilder();
		sb.append(sum).append('\n').append(area);
		System.out.println(sb);
	}
}