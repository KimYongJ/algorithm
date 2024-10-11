//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16236
import java.util.PriorityQueue;
class Node{
	int y, x, time;
	Node(int y, int x, int time){this.y=y; this.x=x; this.time=time;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
		
		int N		= read();
		int map[][] = new int[N][N];
		int Sy		= 0;	// 상어 좌표
		int Sx		= 0;	// 상어 좌표
		int lev     = 2;	// 상어의 크기
		int cnt     = 0;	// 상어가 먹은 물고기수
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
			{
				int num = read();
				if(num != 9)
					map[y][x] = num;
				else {

					Sy = y;
					Sx = x;
				}
			}
		
		int resultTime		= 0;
		boolean isContinue	= true;
		
		while(isContinue)
		{
			isContinue			= false;
			boolean visit[][]	= new boolean[N][N];
			PriorityQueue<Node> fish = new PriorityQueue<>((a,b)-> a.time!=b.time ? a.time - b.time : a.y!=b.y ? a.y-b.y : a.x-b.x);
			
			fish.add(new Node(Sy, Sx,0));
			
			while(!fish.isEmpty())
			{
				Node now = fish.poll();
				// 먹을 수 있는 물고기라면 우선순위 큐에 담는다.
				if(0 < map[now.y][now.x] && map[now.y][now.x] < lev)
				{
					resultTime += now.time;
					Sy			= now.y;
					Sx			= now.x;
					isContinue	= true;
					
					if(++cnt == lev)
					{
						lev +=1 ;
						cnt = 0;
					}
					map[now.y][now.x] = 0;
					break;
				}
				
				int nextTime = now.time + 1;
				
				for(int xy[] : dxy)
				{
					int nextY = now.y + xy[0];
					int nextX = now.x + xy[1];
					if(0<=nextY && 0<=nextX && nextY<N && nextX<N 
						&& !visit[nextY][nextX] && map[nextY][nextX]<=lev)
					{
						visit[nextY][nextX] = true;
						fish.add(new Node(nextY, nextX, nextTime));
					}
				}
				
			}
		}
		System.out.print(resultTime);
	}
}