// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Point{
	int y, x, max, min;
	Point(int y, int x){
		this.y=y; this.x=x;
	}
}
class Main{
	
	static int N, left, right, mid, map[][];
	static int nextY, nextX;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean flag,visit[][];
	static ArrayDeque<Point> q;
	public static boolean BFS(int startNum, int endNum) {
		q = new ArrayDeque<>();
		visit = new boolean[N+2][N+2];
		visit[1][1] = true; 			// 첫 방문처리 
		q.add(new Point(1,1));			// y,x좌표 max,min 전부 첫시작으로 세팅
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(now.y==N && now.x==N) 	// 종료 조건 또한 범위 체크에 포함되어야 하므로 체크로직 추가
				return true;

			for(int xy[] : dxy) 
			{
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(startNum<= map[nextY][nextX] && map[nextY][nextX] <= endNum
						&& !visit[nextY][nextX])
				{
					visit[nextY][nextX] = true;
					q.add(new Point(nextY, nextX));
				}
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		map 	= new int[N+2][N+2];
		int max = -1;
		int min = 201;
		
		for(int i=0; i<N+2; i++)		// 패딩 넣기 
			map[i][0] = map[i][N+1] = 
			map[0][i] = map[N+1][i] = -1;
		
		for(int y=1; y<=N; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++) 
			{
				map[y][x] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[y][x]);		// 배열에서 가장 큰 값을 찾는다. 
				min = Math.min(min, map[y][x]);		// 배열에서 가장 작은 값을 찾는다.
			}
		}
		
		left = 0;
		right = max - min;				// 2분탐색의 기준은 두 숫자의 차이이다.
		while(left < right) 			// left가 더 커질 때 종료 
		{
			mid = (left + right) / 2; 	// BFS를 돌면서 차이가 날 수 있는 최소 값인 mid
			int i = map[1][1]-mid;		 // bfs를 돌 때 건너갈 수 있는 숫자의 범위를 정해준다. 차이가 mid만큼 나야하기 때문에 i의 초기값은 (인덱스1,1)에저장된 숫자에 마이너스 mid를 해준다.
			for(i= i<0 ? 0 : i; i<=map[1][1]; i++) // i값이 0보다 작으면 0으로 셋팅  
			{
				if(flag = BFS(i, i+mid))	// BFS를 돌면서 건너갈 수 있는 숫자는 i와 i+mid 사이 숫자만 건너갈 수 있다.
					break;
			}
			if(flag)					// mid차이만큼 나는 숫자들이 있어서 true가 반환되면 right에 mid 저장
				right = mid;
			else
				left = mid + 1;			// 차이가 mid만큼 일 때 갈 수 없다면 left를 mid+1로하여 기존 mid는 더 이상 탐색되지 않도록 한다.
		}
		System.out.println(right);		// right는 가장 최소값이다.
	}
}