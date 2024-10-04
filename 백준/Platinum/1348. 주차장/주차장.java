//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1348
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node{
	int y, x, time; 
	Node(int y, int x){this.y=y; this.x=x;}
	Node(int y, int x, int time){
		this.y=y; this.x=x; this.time=time;
	}
}
class Time{int t, idx; Time(int t,int idx){this.t=t; this.idx=idx;}}

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	
	public static void BFS(int map[][], int Y, int X, Node start, int idx, int dist[][], ArrayList<Integer> link){
		ArrayDeque<Node> q		= new ArrayDeque<>();

		boolean[][] visit		= new boolean[Y][X];
		visit[start.y][start.x]		= true;
		
		q.add(new Node(start.y, start.x, 0));
		
		while(!q.isEmpty())
		{
			Node now = q.poll();
			
			if(map[now.y][now.x]== 2) {
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

	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y		= Integer.parseInt(st.nextToken());
		int X		= Integer.parseInt(st.nextToken());
		int map[][] = new int[Y][X];				// 1은 차, 2는 주차장, 3은 벽
		int carCnt	= 0;
		int pkCnt	= 0;
		ArrayList<Node> car = new ArrayList<>();
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
			{
				char c = str.charAt(x);
				if(c == 'C')
				{
					map[y][x] = 1;
					carCnt++;
					car.add(new Node(y, x));
				}
				else if(c == 'P') {
					map[y][x] = 2;
					pkCnt++;
				}
				else if(c == 'X')
					map[y][x] = 3;
			}
		}
		
		if(carCnt == 0) {
			System.out.print(0);
			return;
		}
		
		if(pkCnt < carCnt) {
			System.out.print(-1);
			return;
		}
		
		int len = Y * X;
		ArrayList<Integer> link[] = new ArrayList[carCnt];// 이분매칭을 위한 유형 그래프로 변경
		int dist[][] = new int[carCnt][len];	// 자동차의 인덱스별 주차장의 위치에 시간이 담겨있다.
		for(int i=0; i<carCnt; i++) {
			link[i] = new ArrayList<>();
			BFS(map, Y, X, car.get(i), i, dist, link[i]);
		}
		
		int s = 1;
		int e = len;
		int res = -1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;				// 해당 초에 모두 방문이 가능 한지 체크

			if(check(mid, len, carCnt, link, dist))
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		System.out.print(res);
	}
	public static boolean check(int maxTime, int len, int carSize, ArrayList<Integer> link[], int dist[][]) {
		int[] parkOwner = new int[len];
		Arrays.fill(parkOwner, -1);
		int matched = 0;
		for(int i=0; i<carSize; i++)
		{
			boolean[] visit = new boolean[len];
			if(DFS(link, i, maxTime, parkOwner, visit, dist))
				matched++;
		}
		
		return matched == carSize;
	}
	public static boolean DFS(ArrayList<Integer> link[], int idx, int maxTime,int[] parkOwner, boolean visit[], int dist[][])
	{
		for(int parkIndex : link[idx]) {
			if(!visit[parkIndex] && dist[idx][parkIndex] <= maxTime) {
				visit[parkIndex] = true;
				if(parkOwner[parkIndex] == -1 || DFS(link, parkOwner[parkIndex], maxTime, parkOwner, visit, dist)) {
					parkOwner[parkIndex] = idx;
					return true;
				}
			}
		}
		return false;
	}
}