//https://www.acmicpc.net/problem/16924
//2초 512MB
//6 8 // Y, X(3<=100)
//....*...
//...**...
//..*****.
//...**...
//....*...
//........
//답(십자가만을 이용해 만들 수 없다면 -1출력)
//3 // 필요한 십자가 수 출력
//3 4 1 // 그려야하는 십자가 정보 Y(중심행번호), X(중심열번호), S(십자가 크기)
//3 5 2
//3 5 1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int total;
	static int Y, X;
	static char[][] map;
	static boolean[][] visit;
	static List<int[]> result;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Y = Integer.parseInt(st.nextToken());// Y(3<=100)
		X = Integer.parseInt(st.nextToken());// X(3<=100)
		map = new char[Y + 2][X + 2];
		visit = new boolean[Y + 2][X + 2];
		result = new ArrayList<>();
		
		for(int y=0; y<map.length; y++)
			for(int x=0; x<map[y].length; x++)
				map[y][x] = '.';
		
		for(int y=1; y<=Y; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=X; x++)
			{
				map[y][x] = str.charAt(x-1);
				if(map[y][x] == '*')
					++total;
			}
		}
		
		for(int y=1; y<=Y; y++)
		{
			for(int x=1; x<=X; x++)
			{
				if(map[y][x] != '*')
					continue;
				
				int len = getCross(y,x);
				
				if(len == 0)
					continue;
				
				result.add(new int[] {y, x, len});
				mark(y, x, len);
			}
		}
		
		if(!validate())
		{
			System.out.print(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(result.size()).append('\n');
		
		for(int[] r : result)
			sb.append(r[0]).append(' ').append(r[1])
			.append(' ').append(r[2]).append('\n');
		
		System.out.print(sb);
	}
	static int getCross(int y, int x) {
		int len = 0;
		
		while(true)
		{
			++len;
			int cnt = 0;
			
			for(int xy[] : dxy)
			{
				int ny = y + xy[0] * len;
				int nx = x + xy[1] * len;
				
				if(map[ny][nx] == '*')
					++cnt;
			}
			
			if(cnt != 4)
				break;
		}
		
		return len - 1;
	}
	static void mark(int y, int x, int len) {
		for(int i=0; i<=len; i++)
		{
			for(int xy[] : dxy)
			{
				int ny = y + xy[0] * i;
				int nx = x + xy[1] * i;
				visit[ny][nx] = true;
			}
		}
	}
	static boolean validate()
	{
		int cnt = 0;
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(visit[y][x])
					++cnt;
		return cnt == total;
	}
}