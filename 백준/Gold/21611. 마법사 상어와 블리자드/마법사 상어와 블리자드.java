//https://www.acmicpc.net/problem/21611
//1초 1024MB
//7 1 // 맵크기(3<=49), 스킬반복횟수(1<=100)
//0 0 0 0 0 0 0 // 구슬의 정보가 주어지며 없으면 0이주어짐 상어칸은 항상 0임
//3 2 1 3 2 3 0
//2 1 2 1 2 1 0
//2 1 1 0 2 1 1
//3 3 2 3 2 1 2
//3 3 3 1 3 3 2
//2 3 2 2 3 2 3
//2 2 // 스킬의 방향(1<=4)과 거리(1<=(N-1)/2)
//답 : 28

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{0,-1},{1,0},{0,1},{-1,0}};// 달팽이 방향 순회시 사용
	static final int dxy2[][] = {{-1,0},{1,0},{0,-1},{0,1}};// 구슬 파괴시 사용
	static ArrayDeque<Integer> list;
	static boolean[][] visit;
	static boolean flag;
	static int[][] map;
	static int sy, sx;
	static int N, M;
	static int res;
	
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 맵 크기(3<=49)
		M = Integer.parseInt(st.nextToken());// 스킬 반복 횟수(1<=100)
		flag = false;
		sy = sx = (N + 1) / 2;
		list = new ArrayDeque<>();
		map = new int[N + 2][N + 2];
		visit = new boolean[N + 2][N + 2];
		
		for(int i=0; i<N + 2; i++)
			map[i][N + 1] = map[N + 1][i] = map[0][i] = map[i][0] = -1;
		
		for(int y=1; y<=N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int dist = Integer.parseInt(st.nextToken());
			
			delete(dir, dist); // 마법 시전

			moveAndExplosion();// 구슬 이동 및 폭발 반복

			groupChange();// 그룹 변경
		}
		
		System.out.print(res);
	}
	static void delete(int dir, int dist) {
		int ny = sy;
		int nx = sx;
		while(dist-->0)
		{
			ny += dxy2[dir][0];
			nx += dxy2[dir][1];
			
			if(map[ny][nx] < 0)
				return;
			
			map[ny][nx] = 0;
		}
	}
	static boolean moveAndExplosion() {
		list.clear();
		
		search(sy, sx, 3, 1);// 맵을 돌면서 0이 아닌 구슬의 숫자를 list에 넣음
		
		return explosion();// list에서 4개이상 연속된 구슬이 없을 때 까지 폭파 시키며 최종 결과가 list에 담겨 있음
	}
	static void groupChange() {
		// list에 담긴 값을 변환하여 list2에 담음
		ArrayDeque<Integer> dummy = new ArrayDeque<>();
		ArrayDeque<Integer> list2 = new ArrayDeque<>();
		int prev = -1;
		while(!list.isEmpty())
		{
			int n = list.poll();
			
			if(prev != n && dummy.size() > 0)
			{
				list2.add(dummy.size());
				list2.add(dummy.poll());
				dummy.clear();
			}
			
			dummy.add(n);
			prev = n;
		}
		if(dummy.size() > 0)
		{
			list2.add(dummy.size());
			list2.add(dummy.poll());
		}
		
		list = list2;
		
		search(sy, sx, 3, 2);// 맵을 돌면서 list값을 순차적으로 넣음
	}
	static boolean explosion() {
		boolean isContinue = false;
		int prevSize = -1;
		
		while(prevSize != list.size())
		{
			ArrayDeque<Integer> dummy = new ArrayDeque<>();
			ArrayDeque<Integer> list2 = new ArrayDeque<>();
			
			prevSize = list.size();
			
			int prevNum = prevSize != 0 ? list.peek() : 0;
			
			while(!list.isEmpty())
			{
				int n = list.poll();// 값을 꺼내옴
				
				if(n == prevNum)
				{
					dummy.add(n);// 이전과 같다면 dummy에 넣고 스킵
					continue;
				}
				
				
				if(dummy.size() >= 4)// 더미에 들어간 사이즈가 4개 이상이면 폭발시키고 결과에 플러스
				{
					res += dummy.size() * dummy.poll();
					isContinue = true;
				}
				else
					list2.addAll(dummy);// 사이즈가 3이하면 list2에 그대로 담음
				
				dummy.clear();
				dummy.add(n);
				prevNum = n;
			}
			if(dummy.size() >= 4)// 더미에 들어간 사이즈가 4개 이상이면 폭발시키고 결과에 플러스
			{
				res += dummy.size() * dummy.poll();
				isContinue = true;
			}
			else// 사이즈가 3이하면 list2에 그대로 담음
			{
				list2.addAll(dummy);
				dummy.clear();
			}
			
			list = list2;
		}
		return isContinue;
		
	}
	static void search(int y, int x, int dir, int excute) {
		flag = !flag;
		visit[y][x] = flag;
		
		while(map[y][x] >= 0)
		{
			// 시작하자마자 방향을 꺾을 수 있으면 꺾고 못먺으면 그대로 감
			int nextDir = (dir + 1) % 4;
			
			int ny = y + dxy[nextDir][0];
			int nx = x + dxy[nextDir][1];
			
			if(visit[ny][nx] != flag)// 갈 수 있다면
			{
				dir = nextDir;// 그 방향으로 감
			}
			// 해당 방향으로 감
			ny = y + dxy[dir][0];
			nx = x + dxy[dir][1];
			
			// 갈수 없는 방향이면 break
			if(map[ny][nx] < 0)
				break;
			
			visit[ny][nx] = flag;// 방문 처리
			
			y = ny;
			x = nx;
			
			if(excute == 1)
			{
				if(map[ny][nx] > 0)
					list.add(map[ny][nx]);
			}
			else if(excute == 2)
			{
				map[ny][nx] = list.isEmpty() ? 0 : list.poll();
			}
		}
	}
}