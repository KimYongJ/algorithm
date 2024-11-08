//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/6080
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y		= Integer.parseInt(st.nextToken());
		int X		= Integer.parseInt(st.nextToken());
		int[][]map	= new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x] != 0)
				{
					++cnt;
					ArrayDeque<int[]> q = new ArrayDeque<>();
					q.add(new int[] {y,x});
					map[y][x] = 0;
					while(!q.isEmpty())
					{
						int[] now = q.poll();
						for(int xy[] : dxy)
						{
							int nextY = now[0] + xy[0];
							int nextX = now[1] + xy[1];
							if(map[nextY][nextX] != 0) {
								map[nextY][nextX] = 0;
								q.add(new int[] {nextY, nextX});
							}
						}
					}
					
				}

		System.out.print(cnt);
	}
}