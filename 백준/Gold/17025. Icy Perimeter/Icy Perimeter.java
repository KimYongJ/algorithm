//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17025
// 가장큰 덩어리의 #수, #의 둘레
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, map[][];
	static int maxArea, minPerimeter;
	static int area, perimeter;
	static boolean visit[][];
	
	public static void DFS(int y, int x) {
		visit[y][x] = true;
		++area;
		// 둘레 계산
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(map[nextY][nextX] != 1)
				++perimeter;
		}
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(map[nextY][nextX] == 1 && !visit[nextY][nextX])
				DFS(nextY, nextX);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		map		= new int[N+2][N+2];
		minPerimeter = Integer.MAX_VALUE;
		visit = new boolean[N+2][N+2];
		for(int y=1; y<=N; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=N; x++)
				if(str.charAt(x-1)=='#')
					map[y][x] = 1;
		}
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(map[y][x] == 1 && !visit[y][x])
				{
					area = 0;
					perimeter = 0;
					DFS(y, x);
					if(maxArea < area)
					{
						maxArea	= area;
						minPerimeter = perimeter;
					}
					else if(maxArea == area && perimeter < minPerimeter)
						minPerimeter = perimeter;
				}
		System.out.print(new StringBuilder().append(maxArea).append(' ').append(minPerimeter));
	}
}