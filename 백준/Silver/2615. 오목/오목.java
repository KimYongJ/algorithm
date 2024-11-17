//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2615
// 검은색 이긴 경우 1, 아니면 2, 무승부 0
// 둘째 줄에 해당라인의 가장 왼쪽위 출력

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{1,1},{0,1},{-1,1}};// 아래, 오른쪽아래대각선, 오른쪽, 오른쪽위 대각선
		final int rxy[][] = {{-1,0},{-1,-1},{0,-1},{1,-1}};
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		int N		= 19;
		int map[][] = new int[N + 2][N + 2];

		for(int y=1; y<=N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		for(int y=1; y<=N; y++)
		{
			for(int x=1; x<=N; x++)
			{
				if(0<map[y][x])
				{
					int flag = map[y][x];
					for(int i=0; i<4; i++)
					{
						int cnt = 1;						
						int nextY = y;
						int nextX = x;
						
						while(map[nextY + dxy[i][0]][nextX + dxy[i][1]] == flag)
						{
							++cnt;
							nextY += dxy[i][0];
							nextX += dxy[i][1];
						}
						
						int s = y;
						int e = x;
						boolean isContinue = false;
						while(map[s + rxy[i][0]][e + rxy[i][1]] == flag)
						{
							isContinue = true;
							break;
						}
						if(cnt == 5 && !isContinue)
						{

							
							StringBuilder sb = new StringBuilder();
							sb.append(flag).append('\n');
							sb.append(y).append(' ').append(x);
							System.out.print(sb);
							return;
						}
					}
				}
			}
		}
		System.out.print(0);
		
	}
}