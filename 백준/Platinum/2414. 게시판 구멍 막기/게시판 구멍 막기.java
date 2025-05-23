//https://www.acmicpc.net/problem/2414
//2초 128MB
//4 4// N,M 1<=50
//*.*.// N개의 줄에 M개의 문자로 게시판이 추어짐, 구멍은 *, 아니면 .으로 주어짐
//.***
//***.
//..*.
////테이프를 끊어내는 최솟값 출력 : 4

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int MAX = 2500;
	static int Y, X;
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		match = new int[MAX + 1];
		visitTime = new int[MAX + 1];
		adList = new ArrayList[MAX + 1];
		
		for(int i=0; i<adList.length; i++)
			adList[i] = new ArrayList<>();
		
		// 구멍이 연속되지 않으면 새로운 간선을 생성한다.
		// 그럼 행, 열을 의미할 값을 for문 안의 증가 변수를 사용하는게 아니라
		// 별도의 행 열 값을 의미할 변수를 생성해놓고 증가 변수와 분리해서 써야한다.
		int rowCnt = 0;
		int colCnt = 0;
		int rowIdx[][] = new int[Y + 1][X + 1];
		int colIdx[][] = new int[Y + 1][X + 1];
		char map[][] = new char[Y + 1][X + 1];
		for(int y=1; y<=Y; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=X; x++)
			{
				map[y][x] = str.charAt(x-1);// 현재 위치의 상태 입력
				// 구멍일 경우 연산진행
				if(map[y][x] == '*')
				{
					// 위 쪽이 구멍이 아닌 경우, 새로운 행 번호 시작
					if(map[y-1][x] != '*')
						rowIdx[y][x] = ++rowCnt;
					else// 위 쪽이 구멍인 경우, 이전 위치에 있던 행번호를 그대로 복사해옴
						rowIdx[y][x] = rowIdx[y-1][x];
					// 왼쪽이 구멍이 아닌 경우 새로운 열 번호 시작
					if(map[y][x-1] != '*')
						colIdx[y][x] = ++colCnt;
					else// 왼쪽이 구멍인 경우 그 구멍의 열 번호를 그대로 복사해서 가져옴
						colIdx[y][x] = colIdx[y][x-1];
					// 행이 열을 갖도록 리스트 연결
					adList[rowIdx[y][x]].add(colIdx[y][x]);
				}
			}
		}
		
		int cnt = 0;
		
		for(int y=1; y<=rowCnt; y++)
		{
			++time;
			if(dfs(y))
				++cnt;
		}
		System.out.print(cnt);
	}
	
	static boolean dfs(int y)
	{
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