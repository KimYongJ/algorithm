//https://www.acmicpc.net/problem/17837
//0.5초 512MB
//4 4 // 체스판 크기 (4<=12), 말 개수(4<=10)
//0 0 2 0 // 체스판 정보(흰:0, 빨:1, 파:2)
//0 0 1 0
//0 0 1 2
//0 2 0 0
//2 1 1 // 말의 정보가 1번말부터 순서대로 주어짐
//3 2 3 // 행, 열번호는 1부터시작, 이동 방향은 1부터순서대로→, ←, ↑, ↓를 의미
//2 2 1
//4 1 2
//답 : -1

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	
	static final int[][] dxy = {{0,1},{0,-1},{-1,0},{1,0}};
	static ArrayDeque<Node>[][] map;
	static int[][] color;
	static Node[] pos;
	static int N, M;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 체스판 크기 (4<=12)
		M = Integer.parseInt(st.nextToken());// 말 개수(4<=10)
		color = new int[N + 2][N + 2];
		pos = new Node[M];
		map = new ArrayDeque[N + 2][N + 2];
		
		for(int i=0; i<N + 2; i++)
			color[i][N + 1] = color[N + 1][i] = color[0][i] = color[i][0] = 2;
		
		for(int y=1; y<=N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
			{
				color[y][x] = Integer.parseInt(st.nextToken());// 체스판 정보(흰:0, 빨:1, 파:2)
				map[y][x] = new ArrayDeque<>();
			}
		}
		
		for(int order=0; order<M; order++)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());// 행, 열번호는 1부터시작
			int x = Integer.parseInt(st.nextToken());// 행, 열번호는 1부터시작
			int dir = Integer.parseInt(st.nextToken()) - 1;// 이동 방향은 1부터 순서대로→, ←, ↑, ↓를 의미
			pos[order] = new Node(order, y, x, dir);
			map[y][x].add(pos[order]);
		}
		
		for(int round = 1; round<=1000; round++)
		{
			for(Node now : pos)
			{
				int ny = now.y + dxy[now.dir][0];
				int nx = now.x + dxy[now.dir][1];
				
				if(color[ny][nx] == 2)
				{
					// 파랑인경우 방향을 반대로 바꾸고 ny, nx를 갱신
					now.dir = now.dir ^ 1;
					ny = now.y + dxy[now.dir][0];
					nx = now.x + dxy[now.dir][1];
				}
				
				if(color[ny][nx] == 0 || color[ny][nx] == 1)// 흰색 or 빨강인 경우
				{
					if(whiteAndRed(now, ny, nx, color[ny][nx] == 0))
					{
						System.out.print(round);
						return;
					}
				}
				
			}
		}
		
		System.out.print(-1);
	}

	static boolean whiteAndRed(Node now, int ny, int nx, boolean isWhite) {
		ArrayDeque<Node> dummy = new ArrayDeque<>();
		
		int y = now.y;
		int x = now.x;
		// 현재 큐에서 자기보다 앞에 있는 애들은 dummy로 뺀다.
		while(!map[y][x].isEmpty() && map[y][x].peekFirst().order != now.order)
			dummy.add(map[y][x].pollFirst());
		// 현 위치에서 남은걸 다음 스탭으로 옮긴다.
		while(!map[y][x].isEmpty())
		{
			Node node = isWhite ? map[y][x].pollFirst() : map[y][x].pollLast();
			node.y = ny;
			node.x = nx;
			map[ny][nx].add(node);
		}
		// dummy를 현재에 다시 넣는다.
		while(!dummy.isEmpty())
			map[y][x].add(dummy.pollFirst());
		
		return map[ny][nx].size() >= 4;
	}
	static class Node{
		int order, y, x, dir;
		Node(int order, int y, int x, int dir){
			this.order = order;
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
}