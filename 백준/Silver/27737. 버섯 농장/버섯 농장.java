//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27737
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, M, K, depth;
	static int map[][];
	static boolean isUsed;
	public static void DFS(int y, int x) {
		map[y][x] = 0;
		--depth;
		for(int xy[] : dxy)
		{
			if(depth <= 0)
				break;
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(map[nextY][nextX] == 1)
				DFS(nextY, nextX);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 맵크기 (1<=100)
		M = Integer.parseInt(st.nextToken());	// 들고있는 버섯 포자(0<=백만)
		K = Integer.parseInt(st.nextToken());	// 하나의 포자가 번식할 칸의 수
		map = new int[N+2][N+2];
		isUsed = false;
		for(int y=1; y<=N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
				if(Integer.parseInt(st.nextToken()) == 0)
				{
					map[y][x] = 1;
					isUsed = true;
				}
		}
		
		if(!isUsed || K == 0)
		{
			System.out.print("IMPOSSIBLE");
			return;
		}
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(map[y][x] == 1)
				{
					if(M==0)
					{
						System.out.print("IMPOSSIBLE");
						return;
					}
					--M;
					depth = K;
					DFS(y, x);
				}

		System.out.println("POSSIBLE");
		System.out.print(M);
	}
}