//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/6031
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		boolean map[][] = new boolean[Y+2][X+2];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		for(int y=1; y<=Y; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=X; x++)
				map[y][x] = str.charAt(x-1) == '.';
		}
		
		int max = 0;
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x])
				{
					int cnt = 1;
					map[y][x] = false;
					q.add(new int[] {y,x});
					while(!q.isEmpty())
					{
						int now[] = q.poll();
						for(int xy[] : dxy)
						{
							int nextY = now[0] + xy[0];
							int nextX = now[1] + xy[1];
							if(map[nextY][nextX])
							{
								++cnt;
								map[nextY][nextX] = false;
								q.add(new int[] {nextY, nextX});
							}
						}
					}
					max = Math.max(max, cnt);
				}
		System.out.print(max);
	}
}