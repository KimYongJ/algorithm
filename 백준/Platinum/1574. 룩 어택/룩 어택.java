//https://www.acmicpc.net/problem/1574
//2초 128MB
//3 3 6// 체스판의 크기 R(1<=300),C(1<=300), 빈칸의 개수N(0<=600)
//1 1//N개의 줄에 빈칸의 좌표가 주어짐 (행,열) 가장 위는 1임
//2 1
//2 2
//3 1
//3 2
//3 3
//최대 몇 개의 록이 서로 공격하지 않게 놓을 수 있는가 출력
//답 : 2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, N;
	static boolean pass[][];
	static List<Integer> adList[];
	// 이분 매칭시 사용
	static int time;// 방문 체크 변수
	static int match[];// 매칭된 노드를 담을 배열
	static int visitTime[];// 방문 시간 체크로 중복 방문 제고
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());// 체스판의 크기 행 Y(1<=300)
		X = Integer.parseInt(st.nextToken());// 체스판의 크기 열 X(1<=300)
		N = Integer.parseInt(st.nextToken());// 빈칸의 개수N(0<=600)
		pass = new boolean[Y + 1][X + 1];
		adList = new ArrayList[Y + 1];
		
		// 인접 리스트 초기화
		for(int y=0; y<=Y; y++)
			adList[y] = new ArrayList<>();
		
		// 빈칸을 입력 받는다.
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			pass[y][x] = true;// 빈칸은 좌표 연산을 하지 못하게 하기 위해 미리 마킹
		}
		
		// 모든 맵을 순회하며, 빈칸을 제외하고 정점을 연결한다.
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(!pass[y][x])// 빈칸이 아닌 경우 간선 연결
					adList[y].add(x);
		
		match = new int[X + 1];
		visitTime = new int[X + 1];
		
		int cnt = 0;
		
		for(int y=1; y<=Y; y++)
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