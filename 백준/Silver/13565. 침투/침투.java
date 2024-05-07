// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, nextY, nextX, end;
	static boolean map[][];
	public static void DFS(int y, int x) {
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X && !map[nextY][nextX]) 
			{
				map[nextY][nextX] = true;
				if(nextY == end) 
				{
					System.out.println("YES");
					System.exit(0);
				}
				DFS(nextY, nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 	= Integer.parseInt(st.nextToken());
		X 	= Integer.parseInt(st.nextToken());
		end = Y-1;
		map = new boolean[Y][X];
		String str;
		for(int y=0; y<Y; y++) 
		{
			str = br.readLine();
			for(int x=0; x<X; x++) 
			{
				map[y][x] = str.charAt(x) == '1';
			}
		}
		
		for(int x=0; x<X; x++)
			if(!map[0][x]) 
			{
				map[0][x] = true;
				DFS(0,x);
			}
		
		System.out.println("NO");
	}
}