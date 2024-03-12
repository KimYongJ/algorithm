// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node{
	int y, x, time;
	Node(int y, int x,int time){
		this.y=y; this.x=x; this.time = time;
	}
}
class Main
{
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, R, G, RG, size, result, dummymap[][], map[][];
	static int nextY, nextX, nextTime, qsize, flower, p[];
	static int visit[];
	static boolean green_time[][], red_time[][];
	static ArrayDeque<Node> red_q, green_q;
	static ArrayList<int[]> gList;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());	// 세로
		X 		= Integer.parseInt(st.nextToken());	// 가로
		R 		= Integer.parseInt(st.nextToken());	// 빨강 배양액 개수
		G 		= Integer.parseInt(st.nextToken());	// 녹색 배양액 개수
		RG 		= R+G;								// 배양액의 총 개수
		dummymap= new int[Y+2][X+2];				// 입력을 담을 배열
		gList 	= new ArrayList<>();				// 황토땅의 좌표를 담을 배열

		for(int y=1; y<=Y; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++) 
			{
				dummymap[y][x] = Integer.parseInt(st.nextToken());
				if(dummymap[y][x] == 2)					// 배양액을 뿌릴 수 있는 땅을 리스트에 담아 놓는다.
					gList.add(new int[] {y,x});
			}
		}
		
		size 	= gList.size();						// 배양액을 뿌릴 수 있는 당의 개수를 담아 놓는다.
		visit 	= new int[size];					// 배양액을 놓을 위치를 담을 배열
		DFS_RED(1,0,0);								// 순서 : 배양액의 색(1은 Red,2은 Green , 깊이
		System.out.println(result);
	}
	public static void copy() {
		map = new int[Y+2][X+2];
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = dummymap[y][x];
	}
	public static void BFS() {
		copy();
		flower 		= 0;
		green_time	= new boolean[Y+2][X+2];
		red_time 	= new boolean[Y+2][X+2];
		red_q		= new ArrayDeque<>();				// 빨강 배양액담을 큐
		green_q		= new ArrayDeque<>();				// 녹색 배양액담을 큐
		for(int i=0; i<size; i++) 
		{
			if(visit[i] != 0) 
			{
				p = gList.get(i);
				if(visit[i] == 1) {
					red_q.add(new Node(p[0],p[1],3));	// 큐에 배양액 좌표, 시간을 담는다.(map에 있는 값의 최대가 2이기 때문에 3으로 기본값 세팅)
					red_time[p[0]][p[1]] = true;
				}
				else if(visit[i]==2) {
					green_q.add(new Node(p[0],p[1],3));	// 큐에 배양액 좌표, 시간을 담는다.(map에 있는 값의 최대가 2이기 때문에 3으로 기본값 세팅)
					green_time[p[0]][p[1]] = true;
				}
			}
		}
		
		while(!red_q.isEmpty() && !green_q.isEmpty()) 
		{
			qsize = red_q.size();
			while(qsize-->0) {
				Node now = red_q.poll();
				
				if(map[now.y][now.x] == -1) continue; 						// -1인 경우 방문하지 않음, green_q에서 flower가 핀곳임.
				
				for(int xy[] : dxy) {
					nextY = now.y + xy[0];
					nextX = now.x + xy[1];
					nextTime = now.time + 1;
					if(map[nextY][nextX] > 0 && !red_time[nextY][nextX] && !green_time[nextY][nextX]) 	// 지나갈 수 있고, 방문한 적이 없어야 함
					{
						red_time[nextY][nextX] = true;						// 현재 배양액에 지금까지 걸린 시간을 넣어줌
						map[nextY][nextX] = nextTime;
						red_q.add(new Node(nextY, nextX, nextTime));
					}
				}
			}
			qsize = green_q.size();
			while(qsize-->0) {
				Node now = green_q.poll();
				for(int xy[] : dxy) {
					nextY = now.y + xy[0];
					nextX = now.x + xy[1];
					nextTime = now.time + 1;
					if(map[nextY][nextX] > 0 && !green_time[nextY][nextX])	// 지나갈 수 있고, 방문한 적이 없어야 함 
					{
						green_time[nextY][nextX] = true;
						if(map[nextY][nextX] == nextTime)
						{
							map[nextY][nextX] = -1;						// green_q에서 red_q에 표시를 해줌으로써 해당 좌표는 더 이상 연산하지 않도록 한다
							flower++;
						}
						else if(!red_time[nextY][nextX]) {
							map[nextY][nextX] = nextTime;
							green_q.add(new Node(nextY, nextX, nextTime));
						}
					}
				}
			}
		}
		if(result < flower)
			result = flower;
	}
	public static void DFS_GREEN(int type,int depth, int now) 
	{
		if(depth == G) 
		{
			BFS(); 
			return;
		}
		for(int i=now; i<size; i++) 
		{
			if(visit[i]==0) 
			{
				visit[i] = type;
				DFS_GREEN(type, depth + 1,i+1);
				visit[i] = 0;
			}
		}
	}
	public static void DFS_RED(int type, int depth, int now) 
	{
		if(depth == R) 
		{
			DFS_GREEN(2,0,0); 						// 빨간색 탐색 후 초록색 탐색 시작
			return;
		}
		for(int i=now; i<size; i++) 
		{
			if(visit[i]==0) 
			{
				visit[i] = type;					// i번째에 Green인지, Red인지 담아 놓는다.
				DFS_RED(type, depth+1,i+1);				// DFS로 개수만큼 탐색
				visit[i] = 0;						// 탐색 후 다시 0으로 되돌린다.
			}
		}
	}
}

