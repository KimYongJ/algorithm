//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1348
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

class Node{
	int y, x, time; 
	Node(int y, int x){this.y=y; this.x=x;}
	Node(int y, int x, int time){
		this.y=y; this.x=x; this.time=time;
	}
}
class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void BFS(int map[][], int Y, int X, Node start, int idx, int dist[][], ArrayList<Integer> link){
		ArrayDeque<Node> q		= new ArrayDeque<>();

		boolean[][] visit		= new boolean[Y][X];
		visit[start.y][start.x]		= true;
		
		q.add(new Node(start.y, start.x, 0));
		
		while(!q.isEmpty())
		{
			Node now = q.poll();
			
			if(map[now.y][now.x]== 2)
			{
				dist[idx][now.y * X + now.x] = now.time;
				link.add(now.y * X + now.x);
			}

			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				int nextTime = now.time+1;
				if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X && !visit[nextY][nextX] && map[nextY][nextX] != 3)
				{
					visit[nextY][nextX] = true;
					q.add(new Node(nextY, nextX, nextTime));
				}
			}
		}
	}
	static boolean DFS(ArrayList<Integer> link[], int carIdx, int maxTime,int[] parkOwner, boolean visit[], int dist[][])
	{
		for(int parkIndex : link[carIdx])								// 자동차별 접근 가능한 주차장을 순회
			if(!visit[parkIndex] && dist[carIdx][parkIndex] <= maxTime)	// 주차장을 처음 방문하고, 해당 주차장이 정해진 시간(maxTime)안에 갈 수 있다면
			{
				visit[parkIndex] = true;								// 방문처리

				// 주차장이 비어있거나, 비어있지 않다면, 현재 점유중인 차량(parkOwner[parkIndex])을 옮길 수 있는지 확인
				if(parkOwner[parkIndex] == -1 || DFS(link, parkOwner[parkIndex], maxTime, parkOwner, visit, dist))
				{
					parkOwner[parkIndex] = carIdx;
					return true;
				}
			}

		return false;
	}
	public static void main(String[] args)throws Exception{
		int Y		= read();
		int X		= read();
		int map[][] = new int[Y][X];				// 1은 차, 2는 주차장, 3은 벽
		int carCnt	= 0;
		
		ArrayList<Node> car = new ArrayList<>();
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
			{
				char c = (char)System.in.read();
				if(c == 'C')
				{
					map[y][x] = 1;
					carCnt++;
					car.add(new Node(y, x));
				}
				else if(c == 'P')
					map[y][x] = 2;
				else if(c == 'X')
					map[y][x] = 3;
			}
			System.in.read();
		}
		
		if(carCnt == 0) {
			System.out.print(0);
			return;
		}
		
		int len = Y * X;
		ArrayList<Integer> link[] = new ArrayList[carCnt];	// 이분매칭을 위한 유형 그래프로 변경, 각 자동차가 갈 수 있는 주차장의 위치가 담겨있다.
		int dist[][] = new int[carCnt][len];				// 자동차 순서대로 주차장의 위치를 2차원배열로 담고 값은 거기까지 가는 시간을 듬음
		for(int i=0; i<carCnt; i++)
		{
			link[i] = new ArrayList<>();
			BFS(map, Y, X, car.get(i), i, dist, link[i]);
		}
		
		int s	= 1;
		int e	= len;
		int res = -1;
		
		while(s <= e)
		{
			int mid = (s + e) >> 1;				// 해당 초에 모두 방문이 가능 한지 체크
		
			int[] parkOwner = new int[len];		// 각 주차장이 어떤 차량에 의해 차지하고 있는지 표시, 배열인덱스는 주차장, 값은 해당 주차장의 차량인덱스
			
			Arrays.fill(parkOwner, -1);			// 모든 주차장이 아직 아무 차량도 없다는 것을 알도록 -1로 초기화
			
			int matched = 0;
			for(int i=0; i<carCnt; i++)
			{
				boolean[] visit = new boolean[len];
				if(DFS(link, i, mid, parkOwner, visit, dist))
					matched++;
			}
			
			if(matched == carCnt)
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		System.out.print(res);
	}
}