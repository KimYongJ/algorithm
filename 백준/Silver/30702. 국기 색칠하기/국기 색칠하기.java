//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/30702
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X;
	static int[][] map, origin;

	public static boolean DFS(int y, int x, int flag, int base) {
		map[y][x] = 0;

		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(map[nextY][nextX] == flag)
			{
				if(origin[nextY][nextX] == base)
				{
					if(DFS(nextY, nextX, flag, base))
						return true;
				}
				else
					return true;
			}
		}
		
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Y		= Integer.parseInt(st.nextToken());
		X		= Integer.parseInt(st.nextToken());
		map		= new int[Y+2][X+2];
		origin	= new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=X; x++)
				map[y][x] = str.charAt(x-1);
		}
		for(int y=1; y<=Y; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=X; x++)
				origin[y][x] = str.charAt(x-1);
		}
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(0 < map[y][x] && DFS(y, x, map[y][x], origin[y][x]))
				{
					System.out.print("NO");
					return;
				}
				
		
		System.out.print("YES");
	}
}