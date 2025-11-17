//https://www.acmicpc.net/problem/12759
//1초 128MB
//2// 게임을 먼저 시작할 플레이어의 번호
//1 3 // 행, 열 번호
//1 1
//3 1
//2 2
//3 3
//2 3
//3 2
//1 2
//2 1
//답 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int start = Integer.parseInt(br.readLine());
		int map[][] = new int[3][3];
		
		for(int i=0; i<9; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			map[y][x] = start;
			
			if(validate(map, y, x))
			{
				System.out.print(start);
				return;
			}
			
			start = 3 - start;
		}
		System.out.print(0);
	}
	static boolean validate(int[][] map, int y, int x) {
		int now = map[y][x];
		
		if(map[y][0] == now && map[y][1] == now && map[y][2] == now) return true;
		if(map[0][x] == now && map[1][x] == now && map[2][x] == now) return true;
		if(y == x && map[0][0] == now && map[1][1] == now && map[2][2] == now) return true;
		if(y + x == 2 && map[2][0] == now && map[1][1] == now && map[0][2] == now) return true;
		
		return false;
	}
}