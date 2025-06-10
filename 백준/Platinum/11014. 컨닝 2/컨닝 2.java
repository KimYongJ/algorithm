//https://www.acmicpc.net/problem/11014
//2초 256MB
//1 // 테스트 케이스 개수가 주어짐
//10 10 // 교실 세로길이 N, 가로길이 M이 주어짐(1<=80)
//....x.....// N줄에 M개의 문자가 주어짐 '.'은 앉을 수 있는자리, 'x'는 못 앉는 자리
//..........
//..........
//..x.......
//..........
//x...x.x...
//.........x
//...x......
//........x.
//.x...x....
//교실에 앉을 수 있는 최대 학생 수 : 46
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{0,-1},{-1,-1},{1,-1},{-1,1},{0,1},{1,1}};
	static int MAX = 80;
	static int Y, X, blank;
	static int time;
	static int match[];
	static int visitTime[];
	static boolean map[][];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		init();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			clear();
			
			st = new StringTokenizer(br.readLine());
			Y = Integer.parseInt(st.nextToken());// 교실 세로길이 Y(1<=80)
			X = Integer.parseInt(st.nextToken());// 교실 가로길이 X(1<=80)
			blank = 0;
			for(int y = 1; y<=Y; y++)
			{
				String str = br.readLine();
				for(int x = 1; x <= X; x++)// 빈자리 일 때만 true
				{
					// 빈칸이 true
					map[y][x] = str.charAt(x - 1) == '.';
					if(map[y][x])// 
						++blank;
				}
			}
			
			// 인접 리스트 생성
			for(int y=1; y<=Y; y++)
			{
				for(int x=1; x<=X; x++)
				{
					if(!map[y][x])// 현재 칸이 막혀있으면 스킵
						continue;
					
					int v = getIdx(y, x);
					
					for(int xy[] : dxy)
					{
						int nextY = y + xy[0];
						int nextX = x + xy[1];
						if(1<=nextY && nextY<=Y && 1<=nextX && nextX<=X && map[nextY][nextX])
							adList[v].add(getIdx(nextY,nextX));
					}
				}
			}
			
			int cnt = 0;
			
			for(int y=1; y<=Y; y++)
			{
				for(int x=1; x<=X; x+=2)
				{
					if(!map[y][x])
						continue;
					
					++time;
					
					int idx = getIdx(y, x);
					
					if(dfs(idx))
						++cnt;
				}
			}
			
			sb.append(blank - cnt).append('\n');
		}
		System.out.print(sb);
	}
	static boolean dfs(int y)
	{
		for(int x : adList[y])
		{
			if(visitTime[x] == time)
				continue;
			
			visitTime[x] = time;
			
			if(match[x] == -1 || dfs(match[x]))
			{
				match[x] = y;
				return true;
			}
		}
		return false;
	}
	static int getIdx(int y, int x) {
		return y * MAX + x;
	}
	static void clear()
	{
		Arrays.fill(match, -1);
		for(int i=0; i<adList.length; i++)
			adList[i].clear();
	}
	static void init()
	{
		int len = (MAX + 1) * (MAX + 1);
		match = new int[len + 1];
		visitTime = new int[len + 1];
		map = new boolean[MAX + 2][MAX + 2];
		adList = new ArrayList[len + 1];
		
		for(int i=0; i<adList.length; i++)
			adList[i] = new ArrayList<>();
	}
}