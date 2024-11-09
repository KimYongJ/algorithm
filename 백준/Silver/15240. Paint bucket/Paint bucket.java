//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/15240
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		char map[][] = new char[Y][X];
		for(int y=0; y<Y; y++) {
			String str = br.readLine();
			for(int x=0; x<X; x++)
				map[y][x] = str.charAt(x);
		}
		st = new StringTokenizer(br.readLine());
		int sy = Integer.parseInt(st.nextToken());
		int sx = Integer.parseInt(st.nextToken());
		char num= st.nextToken().charAt(0);
		char flag= map[sy][sx];
		if(num != flag)
		{
			map[sy][sx] = num;
			ArrayDeque<int[]> q = new ArrayDeque<>();
			q.add(new int[] {sy, sx});
			while(!q.isEmpty())
			{
				int now[] = q.poll();
				for(int xy[] : dxy)
				{
					int nextY = now[0] + xy[0];
					int nextX = now[1] + xy[1];
					if(0<=nextY && 0<=nextX && nextY<Y && nextX<X
							&& map[nextY][nextX] == flag)
					{
						map[nextY][nextX] = num;
						q.add(new int[] {nextY, nextX});
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
				sb.append(map[y][x]);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}