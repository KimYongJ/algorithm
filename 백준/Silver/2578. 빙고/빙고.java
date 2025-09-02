//https://www.acmicpc.net/problem/2578
//1초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int map[][] = new int[5][5];
		
		for(int y=0; y<5; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<5; x++)
				map[y][x] = Integer.parseInt(st.nextToken());

		}
		
		int cnt = 0;
		
		while(cnt<25)
		{
			if(cnt % 5 == 0)
				st = new StringTokenizer(br.readLine());
			
			mark(map, Integer.parseInt(st.nextToken()));

			if(check(map))
			{
				System.out.print(cnt + 1);
				return;
			}

			++cnt;
		}
		System.out.print(0);
	}
	static void mark(int[][] map, int target) {
		for(int y=0; y<5; y++)
			for(int x=0; x<5; x++)
				if(map[y][x] == target)
				{
					map[y][x] = 0;
					return;
				}
	}
	static boolean check(int[][] map) {
		int line = 0;
		int dig = 0;
		for(int y=0; y<5; y++)
		{
			int row = 0;
			int col = 0;
			for(int x=0; x<5; x++)
			{
				if(map[y][x] == 0)row++;
				if(map[x][y] == 0)col++;
				if(y == x && (map[y][x] == 0))
					dig++;
			}
			if(row == 5) ++line;
			if(col == 5) ++line;
			
		}
		if(dig == 5) ++line;
		
		int sum = map[0][4] + map[1][3] + map[2][2] + map[3][1] + map[4][0];
		
		if(sum == 0)
			++line;
		
		return line>=3;
	}
}
