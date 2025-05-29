//https://www.acmicpc.net/problem/1760
//2초 128MB
//3 4	// 격자 크기 행Y,열X(1<=100)가 주어짐
//2 0 0 0
//2 2 2 1
//0 1 0 2// 0은 빈격자, 1은 구덩이, 2는 벽
//첫째 줄에 배치할 수 있는 Rook의 최대 개수를 출력, 답 : (2)
//힌트 : 1행 2열과 3행 3열에 하나씩, 총 두 개의 Rook을 배치할 수 있다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int MAX = 100 * 100;
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y = Integer.parseInt(st.nextToken());// 격자 크기 행Y(1<=100)가 주어짐
		int X = Integer.parseInt(st.nextToken());// 격자 크기 열X(1<=100)가 주어짐
		int map[][] = new int[Y + 1][X + 1];
		int yIdx[][] = new int[Y + 1][X + 1];
		int xIdx[][] = new int[Y + 1][X + 1];
		int yValue = 0;
		int xValue = 0;
		
		adList = new ArrayList[MAX + 1];
		for(int i=0; i<adList.length; i++)
			adList[i] = new ArrayList<>();
		
		for(int y=1; y<=Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken());
				// 0은 빈격자, 1은 구덩이, 2는 벽
				if(map[y][x] == 0)// 빈 격자인 경우
				{
					yIdx[y][x] = yIdx[y-1][x] == 0 ? ++yValue : yIdx[y-1][x];
					xIdx[y][x] = xIdx[y][x-1] == 0 ? ++xValue : xIdx[y][x-1];
					adList[yIdx[y][x]].add(xIdx[y][x]);
				}
				// 구덩이인 경우, 인덱스를 그대로 복사해서 갖고와야 다음연산이 가능하다.
				else if(map[y][x] == 1)
				{
					yIdx[y][x] = yIdx[y-1][x];
					xIdx[y][x] = xIdx[y][x-1];
				}
			}
		}
		
		match = new int[xValue + 1];
		visitTime = new int[xValue + 1];
		int cnt = 0;
		for(int y=1; y<=yValue; y++)
		{
			++time;
			if(dfs(y))
				++cnt;
		}
		System.out.print(cnt);
	}
	static boolean dfs(int y) {
		for(int x : adList[y])
		{
			if(visitTime[x] == time)
				continue;
			
			visitTime[x] = time;
			if(match[x] == 0 || dfs(match[x]))
			{
				match[x] = y;
				return true;
			}
		}
		return false;
	}
}