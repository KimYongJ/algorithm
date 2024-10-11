//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16236
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int y, x;
	Node(int y, int x){this.y=y; this.x=x;}
}
class Shark{
	int y, x, lev, cnt;
	Shark(int y, int x, int lev, int cnt){
		this.y=y; this.x=x; this.lev=lev;
		this.cnt=cnt;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int map[][] = new int[N][N];
		Shark shark = null;
		
		for(int y=0; y<N; y++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
			{
				int num = Integer.parseInt(st.nextToken());
				if(num != 9)
					map[y][x] = num;
				else
					shark = new Shark(y, x, 2, 0);
			}
		}
		
		int resultTime = 0;
		
		while(true)
		{
			int time					= 0;
			Node nextShark				= null;
			boolean visit[][]			= new boolean[N][N];
			ArrayDeque<Node> q			= new ArrayDeque<>();
			PriorityQueue<Node> fish	= new PriorityQueue<>((a,b)-> a.y!=b.y ? a.y-b.y : a.x-b.x);
			
			q.add(new Node(shark.y, shark.x));
			
			while(!q.isEmpty())
			{
				++time;
				int size = q.size();
				while(size-- >0)
				{
					Node now = q.poll();
					// 먹을 수 있는 물고기라면 우선순위 큐에 담는다.
					if(0 < map[now.y][now.x] && map[now.y][now.x] < shark.lev)
					{
						fish.add(now);
						continue;
					}
					for(int xy[] : dxy)
					{
						int nextY = now.y + xy[0];
						int nextX = now.x + xy[1];
						if(0<=nextY && 0<=nextX && nextY<N && nextX<N 
							&& !visit[nextY][nextX] && map[nextY][nextX]<=shark.lev)
						{
							visit[nextY][nextX] = true;
							q.add(new Node(nextY, nextX));
						}
					}
				}
				// 위탐색에서 물고기를 찾았으면, 물고기를 지우고 최종 시간에 걸린 시간을 추가한다.
				if(!fish.isEmpty())
				{
					nextShark = fish.poll();
					resultTime += time-1;
					map[nextShark.y][nextShark.x] = 0; 
					fish.clear();
					break;
				}
			}
			// 물고기를 찾았으면 상어의 위치를 그 물고기로 바꾸고, 크기와 먹은 물고기 수를 조정함
			if(nextShark != null)
			{
				shark.y = nextShark.y;
				shark.x = nextShark.x;
				if(++shark.cnt == shark.lev)
				{
					shark.lev +=1 ;
					shark.cnt = 0;
				}
			}
			else
				break;
		}
		System.out.print(resultTime);
	}
}

