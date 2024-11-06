//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/30702
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X;
	static int[][] map, origin;
	static ArrayList<int[]> part;
	public static void DFS(int y, int x, int flag) {
		map[y][x] = 0;
		part.add(new int[] {y,x});
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(map[nextY][nextX] == flag)
				DFS(nextY, nextX, flag);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<ArrayList<int[]>> groupList = new ArrayList<>();
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new int[Y+2][X+2];
		origin = new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++) {
			String str = br.readLine();
			for(int x=1; x<=X; x++)
				map[y][x] = str.charAt(x-1);
		}
		for(int y=1; y<=Y; y++){
			String str = br.readLine();
			for(int x=1; x<=X; x++)
				origin[y][x] = str.charAt(x-1);
		}
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(0 < map[y][x])
				{
					part = new ArrayList<>();
					DFS(y, x, map[y][x]);
					groupList.add(part);
				}
		
		for(ArrayList<int[]> part : groupList)
		{
			int flag = origin[part.get(0)[0]][part.get(0)[1]];
			for(int p[] : part)
				if(origin[p[0]][p[1]] != flag)
				{
					System.out.print("NO");
					return;
				}
		}
		System.out.print("YES");
	}
}