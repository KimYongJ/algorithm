// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, nextY, nextX;
	static boolean map[][];
	public static void DFS(int y, int x) {
		for(int xy[]:dxy) {
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(map[nextY][nextX]) {
				map[nextY][nextX] = false;
				DFS(nextY,nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br	= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder 	sb	= new StringBuilder();
		StringTokenizer st;
		int cnt, T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			cnt	= 0;
			st 	= new StringTokenizer(br.readLine());
			Y 	= Integer.parseInt(st.nextToken());
			X	= Integer.parseInt(st.nextToken());
			map = new boolean[Y+2][X+2];
			
			for(int y=1; y<=Y; y++) 
			{
				String str = br.readLine();
				for(int x=1; x<=X; x++) 
					map[y][x] = str.charAt(x-1) == '#';
			}
			
			for(int y=1; y<=Y; y++) 
				for(int x=1; x<=X; x++) 
					if(map[y][x]) 
					{
						cnt++;
						map[y][x] = false;
						DFS(y,x);
					}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}
}