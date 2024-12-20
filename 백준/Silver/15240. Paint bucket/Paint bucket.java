//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/15240
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Main{
	
    static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X;
	static int sy, sx;
	static char num, flag, map[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y	= Integer.parseInt(st.nextToken());
		X	= Integer.parseInt(st.nextToken());
		map	= new char[Y][X];
		
		for(int y=0; y<Y; y++)
			map[y] = br.readLine().toCharArray();
		
		st      = new StringTokenizer(br.readLine());
		sy		= Integer.parseInt(st.nextToken());
		sx		= Integer.parseInt(st.nextToken());
		num		= st.nextToken().charAt(0);
		flag	= map[sy][sx];
		
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
			sb.append(map[y]).append('\n');
		System.out.print(sb.toString());
	}
}