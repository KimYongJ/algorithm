//https://www.acmicpc.net/problem/1331
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean visit[][] = new boolean[6][6];
		String str = br.readLine();
		int y = str.charAt(0) - 'A';
		int x = (str.charAt(1) - '0')-1;
		int sy = y, sx = x;
		visit[y][x] = true;
		int T = 35;
		
		while(T-->0)
		{
			str = br.readLine();
			int ny = str.charAt(0) - 'A';
			int nx = (str.charAt(1) - '0')-1;
			
			if(visit[ny][nx]|| !validate(y ,x, ny, nx))
			{
				System.out.print("Invalid");
				return;
			}
			visit[ny][nx] = true;

			y = ny;
			x = nx;
		}
		
		System.out.print(!validate(sy, sx, y, x) ? "Invalid" : "Valid");
	}
	static boolean validate(int y1, int x1, int y2, int x2) {
		int y = Math.abs(y1 - y2);
		int x = Math.abs(x1 - x2);
		return (y == 2 && x == 1) || (y == 1 && x == 2);
	}
}