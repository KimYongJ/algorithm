//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/11370
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer	st;
		ArrayDeque<int[]> q	= new ArrayDeque<>();
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			int X				= Integer.parseInt(st.nextToken());
			int Y				= Integer.parseInt(st.nextToken());
			char map[][]		= new char[Y+2][X+2];
			boolean visit[][]	= new boolean[Y+2][X+2];
			if(Y == 0 && X == 0)
				break;
			
			for(int y=1; y<=Y; y++)
			{
				String str = br.readLine();
				for(int x=1; x<=X; x++)
					map[y][x] = str.charAt(x-1);
			}
			
			for(int y=1; y<=Y; y++)
				for(int x=1; x<=X; x++)
					if(!visit[y][x] && map[y][x] == 'S')
					{
						visit[y][x] = true;
						q.add(new int[] {y, x});
						while(!q.isEmpty())
						{
							int now[] = q.poll();
							for(int xy[] : dxy)
							{
								int nextY = now[0] + xy[0];
								int nextX = now[1] + xy[1];
								if(!visit[nextY][nextX] && map[nextY][nextX] == 'T')
								{
									visit[nextY][nextX] = true;
									map[nextY][nextX] = 'S';
									q.add(new int[] {nextY, nextX});
								}
							}
						}
					}
			
			for(int y=1; y<=Y; y++)
			{
				for(int x=1; x<=X; x++)
					sb.append(map[y][x]);
				sb.append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}