//https://www.acmicpc.net/problem/2578
//1초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		HashMap<Integer, int[]> idx = new HashMap<>();
		for(int y=0; y<5; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<5; x++)
			{
				int num = Integer.parseInt(st.nextToken());
				idx.put(num, new int[] {y,x});
			}
		}
		
		boolean map[][] = new boolean[5][5];
		int cnt = 0;
		
		Loop:
		while(cnt<25)
		{
			if(cnt % 5 == 0)
				st = new StringTokenizer(br.readLine());

			int xy[] = idx.get(Integer.parseInt(st.nextToken()));
			map[xy[0]][xy[1]] = true;
			if(check(map))
				break Loop;

			++cnt;
		}
		System.out.print(cnt + 1);
	}
	static boolean check(boolean[][] map) {
		int line = 0;
		int dig = 0;
		for(int y=0; y<5; y++)
		{
			int row = 0;
			int col = 0;
			for(int x=0; x<5; x++)
			{
				if(map[y][x])row++;
				if(map[x][y])col++;
				if(y == x && map[y][x])
					dig++;
			}
			if(row == 5) ++line;
			if(col == 5) ++line;
			
		}
		if(dig == 5) ++line;
		
		if(map[0][4] && map[1][3] && map[2][2] && map[3][1] && map[4][0])
			++line;
		
		return line>=3;
	}
}
