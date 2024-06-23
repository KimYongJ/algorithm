// https://github.com/kimyongj/algorithm

import java.util.ArrayList;
class Node{	int y, x;	Node(int y, int x){this.y=y;this.x=x;} }
class Main{
	
	static int result = Integer.MAX_VALUE;
	static int zeroCnt;
	static int Y, X;
	static int map[][];
	static ArrayList<Node> list = new ArrayList<>();	// 1~5번 카메라의 위치를 담을 리스트
	static int[][][][] position = {
			{{}},
			{		// 1번 카메라일 때
				{{0,1}},
				{{1,0}},
				{{-1,0}},
				{{0,-1}}
			},
			{		// 2번 카메라일 때
				{{0,1},{0,-1}},
				{{1,0},{-1,0}}
			},
			{		// 3번 카메라일 때
				{{-1,0},{0,1}},
				{{1,0},{0,1}},
				{{1,0},{0,-1}},
				{{-1,0},{0,-1}},
			},
			{		// 4번 카메라일 때
				{{0,-1},{0,1},{-1,0}},
				{{0,-1},{0,1},{1,0}},
				{{-1,0},{1,0},{0,-1}},
				{{1,0},{-1,0},{0,1}}
			},
			{		// 5번 카메라일 때
				{{0,1},{1,0},{-1,0},{0,-1}}
			}
	};
	// 빠른 입력을 위한 함수
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void changeTo(Node now, int[][] m, int from, int to) {
		boolean flag = from == 0;	// 0에서 숫자로 바꾸는건지 체크
		for(int xy[] : m) 
		{
			int nextY = now.y + xy[0];
            int nextX = now.x + xy[1];
			while(nextX>=0 && nextY>=0 && nextY < Y && nextX<X) // 유효범위 안이라면,  
            {
				if(map[nextY][nextX] == 6) 
                    break; // 벽을 만나면  while문 탈출
				if(map[nextY][nextX] == from) 
				{
					map[nextY][nextX] = to;
					if(flag) zeroCnt --;	// 0에서 숫자로 바꾸는것이면 0의 개수를 마이너스 시킨다.
					else zeroCnt++;			// 숫자에서 0으로 바꾸는 것이면 0의 개수를 플러스 시킨다.
				}
                nextY += xy[0];// 다음 좌표 세팅
				nextX += xy[1];// 다음 좌표 세팅
			}
		}
	}
	
	public static void backtracking(int depth) {
		if(result == 0) // 0인 경우 조기 종료  
			return;
		if(depth < 0) 	// 모든 카메라를 체크했으면 종료 
		{
			if(result > zeroCnt)
				result = zeroCnt;
			return;
		}
		
		Node nowPosition	= list.get(depth);						// 현재 좌표
		int cameraNum		= map[nowPosition.y][nowPosition.x];	// 현재 좌표의 카메라 번호
		int flagNum			= (depth + 1) * 100;					// 현재 카메라가 바라보는 방향에 넣을 숫자( 겹치지 않게 100을 곱함 )
		for(int[][] m : position[cameraNum]) 
		{
			changeTo(nowPosition,m, 0, flagNum);					// 현재 포지션에서 0을 flagNum으로 변경
			backtracking(depth - 1);
			changeTo(nowPosition,m, flagNum, 0);					// 현재 포지션에서 flagNum을 0으로 변경
		}
	}
	public static void main(String[] args)throws Exception{
		Y	= read();
		X	= read();
		map = new int[Y][X];
		
		for(int y=0; y<Y; y++) 
		{
			for(int x=0; x<X; x++) 
			{
				map[y][x] = read();
				if(map[y][x] == 0) {
					zeroCnt++;
				}
				else if(map[y][x]<6) {
					list.add(new Node(y,x)); // cctv인 경우 list에 위치를 담는다.
				}
			}
		}
		
		backtracking(list.size()-1);
		
		System.out.print(result);
	}
}