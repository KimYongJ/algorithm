//https://www.acmicpc.net/problem/3208
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{0,1},{1,0},{0,-1},{-1,0}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int idx = 0;
		int turn = 0;
		int x = 0;
		int y = 0;
		int cnt = Y * X - 1;
		
		boolean visit[][] = new boolean[Y][X];
		visit[0][0] = true;
		
		while(cnt>0)
		{
			int ny = y + dxy[idx][0];
			int nx = x + dxy[idx][1];
			
			if(ny < 0 || nx < 0 || Y <= ny || X <= nx || visit[ny][nx])
			{
				idx = (idx + 1) % 4;
				turn++;
				continue;
			}
			visit[ny][nx] = true;
			--cnt;
			y = ny;
			x = nx;
		}
		
		System.out.print(turn);
	}
}