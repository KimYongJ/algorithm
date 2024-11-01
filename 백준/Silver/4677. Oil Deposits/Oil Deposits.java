//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4677

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
	static int Y, X, cnt, map[][];
	public static void DFS(int y, int x) {
		map[y][x] = 0;
		for(int xy[] : dxy) {
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(0<=nextY && 0<=nextX && nextY < Y && nextX < X && map[nextY][nextX] == 1) {
				DFS(nextY, nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			Y		= Integer.parseInt(st.nextToken());
			X		= Integer.parseInt(st.nextToken());
			if(Y==0 || X == 0)
				break;
			map		= new int[Y][X];
			cnt		= 0;
			for(int y=0; y<Y; y++)
			{
				String str = br.readLine();
				for(int x=0; x<X; x++)
					if(str.charAt(x) == '@')
						map[y][x] = 1;
			}
			
			for(int y=0; y<Y; y++)
				for(int x=0; x<X; x++)
					if(map[y][x] == 1)
					{
						++cnt;
						DFS(y, x);
					}

			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}