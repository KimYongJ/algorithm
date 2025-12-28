//https://www.acmicpc.net/problem/2072
//2초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int pos[][][] = {
			{{0,1},{0,-1}}, // 상하
			{{1,0},{-1,0}}, // 좌우
			{{-1,-1},{1,1}},// 왼쪽위에서 오른쪽 아래로
			{{-1,1},{1,-1}},// 왼쪽아래에서 오른쪽 위로
	};
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int map[][] = new int[22][22];
		int res = -1;
		boolean flag = true;
		int color = 0;
		
		for(int i=1; i<=N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			map[y][x] = color = flag ? 1 : 2;
			
			for(int dxy[][] : pos) // 상하좌우대각선을 탐색
			{
				int cnt = 1;
				for(int xy[] : dxy) // 각 방향으로 끝까지 가며 탐색
				{
					int nextX = x + xy[0];
					int nextY = y + xy[1];
					while(map[nextY][nextX] == color)
					{
						++cnt;
						nextX += xy[0];
						nextY += xy[1];
					}
				}
				if(cnt == 5) {
					System.out.print(i);
					return;
				}
			}
			
			flag = !flag;
		}
		System.out.print(res);
	}
}