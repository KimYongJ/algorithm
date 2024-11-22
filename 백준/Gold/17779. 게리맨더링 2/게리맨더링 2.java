//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17779
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int min = 1<<30;
	static int N, map[][];
	static int total, zon[];
	static boolean visit[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int y=0; y<N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
				total += map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				for(int d1=1; d1<N; d1++)
				{
					if(y < d1) break;
					for(int d2=1; d2<=N; d2++)
					{
						if(x + d1 + d2 >= N)break;
						if(y + d2 >= N)		break;
						cal(y, x, d1, d2);
					}
				}

		System.out.print(min);
	}
	public static void cal(int Y, int X, int d1, int d2) {
		visit	= new boolean[N][N];
		zon		= new int[6];
		// 경계 세팅
		for(int i=0; i<= d1; i++)
			visit[X+i][Y-i] = visit[X+d2+i][Y+d2-i] = true;
		for(int i=0; i<= d2; i++)
			visit[X+i][Y+i] = visit[X+d1+i][Y-d1+i] = true;

		// 1구역
		for (int i = 0; i < X + d1; i++)
    	   	for (int j = 0; j <= Y; j++)
			{
				if(visit[i][j])
					break;
				zon[1] += map[i][j];
			}
		// 2구역
       	for (int i = 0; i <= X + d2; i++)
       		for (int j = N - 1; j > Y; j--)
			{
				if(visit[i][j]) break;
				zon[2] += map[i][j];
			}
		// 3구역
       	for (int i = X + d1; i < N; i++)
       		for (int j = 0; j < Y - d1 + d2; j++)
			{
				if(visit[i][j]) break;
				zon[3] += map[i][j];
			}
		// 4구역
       	for (int i = X + d2 + 1; i < N; i++)
       		for (int j = N - 1; j >= Y - d1 + d2; j--)
			{
				if(visit[i][j])break;
				zon[4] += map[i][j];
			}
		
		zon[5] = total - (zon[1]+zon[2]+zon[3]+zon[4]);
		
		min = Math.min(min, minZone());		
	}
	public static int minZone() {
		int min = 1<<30;
		int max = 0;
		for(int i=1; i<=5; i++)
		{
			min = Math.min(min, zon[i]);
			max = Math.max(max, zon[i]);
		}
		return max-min;
	}
}