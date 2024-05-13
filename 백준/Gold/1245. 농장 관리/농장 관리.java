// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}, {-1,-1},{-1,1},{1,-1},{1,1}};
	static int Y, X, result, nextY, nextX, map[][];
	static boolean flag, visit[][];
	public static void DFS(int base,int y, int x) {
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(map[nextY][nextX] == base && !visit[nextY][nextX]) 
			{
				visit[nextY][nextX] = true;
				DFS(base, nextY, nextX);
			}else if(map[nextY][nextX] > base)
				flag = false;
			
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X		= Integer.parseInt(st.nextToken());
		map		= new int[Y+2][X+2];
		visit 	= new boolean[Y+2][X+2];
		for(int y=1; y<=Y; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++) 
			{
				map[y][x] = Integer.parseInt(st.nextToken());
				if(map[y][x]==0)
					visit[y][x] = true;
			}
		}
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(!visit[y][x]) 
				{
					flag = true;
					DFS(map[y][x],y,x);
					if(flag)
						result++;
				}

		System.out.print(result);
	}
}