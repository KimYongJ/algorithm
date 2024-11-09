//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/4993
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][]	= {{1,0},{0,1},{-1,0},{0,-1}};
		ArrayDeque<int[]> q	= new ArrayDeque<>();
		BufferedReader 	br	= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb	= new StringBuilder();
		StringTokenizer st;
		
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			
			int X			= Integer.parseInt(st.nextToken());
			int Y			= Integer.parseInt(st.nextToken());
			boolean map[][] = new boolean[Y+2][X+2];
			
			if(X == 0 && Y == 0)
				break;
			
			for(int y=1; y<=Y; y++)
			{
				String str = br.readLine();
				for(int x=1; x<=X; x++)
				{
					char c = str.charAt(x-1);
					map[y][x] = c=='.';
					if(c == '@')
						q.add(new int[] {y, x});
				}
			}
			
			int cnt = 1;
			
			while(!q.isEmpty())
			{
				int n[] = q.poll();
				for(int xy[] : dxy)
				{
					int nextY = n[0] + xy[0];
					int nextX = n[1] + xy[1];
					if(map[nextY][nextX])
					{
						++cnt;
						map[nextY][nextX] = false;
						q.add(new int[] {nextY, nextX});
					}
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}