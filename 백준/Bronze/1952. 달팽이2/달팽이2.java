//https://www.acmicpc.net/problem/1952
//2초 128MB
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
		int repeat = Y * X;
		int y = 1;
		int x = 1;
		int dir = 0;
		int cnt = 0;
		boolean visit[][] = new boolean[Y+1][X+1];
		
		visit[1][1] = true;
		
		while(--repeat > 0)
		{
			int ny = y + dxy[dir][0];
			int nx = x + dxy[dir][1];
			
			if(ny < 1 || nx < 1 || Y < ny || X < nx || visit[ny][nx])
			{
				++cnt;
				
				dir = (dir + 1) % 4;

				ny = y + dxy[dir][0];
				nx = x + dxy[dir][1];
			}
			
			visit[ny][nx] = true;
			
			y = ny;
			x = nx;
		}
		System.out.print(cnt);
	}
}