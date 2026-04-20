//https://www.acmicpc.net/problem/3254
//1초 128MB
//2 2 // 상근이의 열번호, 정인이의 열번호(던진 김밥은 아래로 떨어짐) 
//5 2
//3 7
//6 1
//4 6
//3 1
//3 5
//3 3
//6 3
//2 5
//4 1
//6 2
//2 5
//7 5
//1 7
//4 4
//4 1
//7 6
//1 7
//7 5
//6 4
//답 : sk 5 // 먼저던진사람이이기면 sk, 아니면 ji, 비기면 ss
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X;
	static int map[][];
	static int dxy[][] = {
			{1,-1}, // 왼쪽 대각선 아래
			{1,1}, // 오른쪽 대각선 아래
			{0,1}, // 오른쪽
			{1,0}, // 아래
	};
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Y = 6;
		X = 7;
		map = new int[Y + 1][X + 1];
		
		for(int i=1; i<=21; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int first = 1;
			
			for(int j=0; j<2; j++)
			{
				int x = Integer.parseInt(st.nextToken());
				
				int y = getY(x);
				
				map[y][x] = first;
				
				if(check(first)) {
					System.out.printf("%s %d", first == 1 ? "sk" : "ji", i);
					return;
				}
				
				first ^= 3;
			}
			
		}
		System.out.print("ss");
	}
	public static boolean check(int target) {
		
		for(int y=1; y<=Y; y++)
		{
			for(int x=1; x<=X; x++)
			{
				if(map[y][x] != target) continue;
				
				for(int xy[] : dxy)
				{
					int cnt = 1;
					int ny = y;
					int nx = x;
					
					for(int z=1; z<=3; z++)
					{
						ny += xy[0];
						nx += xy[1];
						
						if(ny < 1 || nx < 1 || ny > Y || nx > X || map[ny][nx] != target)
							break;
							
						++cnt;
					}
					
					if(cnt == 4)
						return true;
				}
				
			}
		}
		
		return false;
	}
	public static int getY(int x) {
		int y = Y;
		
		while(y >= 0)
		{
			if(map[y][x] == 0)
				return y;
			--y;
		}
		
		return 0;
	}
}