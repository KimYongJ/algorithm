//https://www.acmicpc.net/problem/1491
//2초 128MB
//6 4 답 : 1 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{0,1},{1,0},{0,-1},{-1,0}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int dir = 0;
		int y = 0;
		int x = 0;
		boolean end = false;
		
		boolean visit[][] = new boolean[Y][X];
		visit[0][0] = true;
		
		while(true)
		{
			int ny = y + dxy[dir][0];
			int nx = x + dxy[dir][1];
			
			if(ny<0 || nx<0 || ny==Y || nx==X || visit[ny][nx])
			{
				if(end) break;
				dir = (dir + 1) % 4;
				end = true;
				continue;
			}
			visit[ny][nx] = true;
			y = ny;
			x = nx;
			end = false;
		}
		System.out.printf("%d %d",x, y);
	}
}