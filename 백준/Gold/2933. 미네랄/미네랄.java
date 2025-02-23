//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/2933
import java.util.ArrayList;
import java.util.Collections;

class Node{int y, x; Node(int y, int x){this.y=y; this.x=x;}}

class Main{
	
	static int	dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int	Y, X;
	static char	map[][];
	static boolean isContinue, visit[][];
	static ArrayList<Node> list;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int findX(int y, boolean flag) {
		int x = -1;
		if(flag){
			for(int x1=0; x1<X; x1++)
				if(map[y][x1] == 'x')
				{
					x = x1;
					break;
				}
		}else {
			for(int x1=X-1; x1>=0; x1--)
				if(map[y][x1] == 'x')
				{
					x = x1;
					break;
				}
		}
		return x;
	}
	public static void DFS(int y, int x) {
		for(int xy1[] : dxy)
		{
			int nextY = y + xy1[0];
			int nextX = x + xy1[1];
			if(0<=nextY && 0<=nextX && nextY<Y && nextX<X && !visit[nextY][nextX]
				&& map[nextY][nextX] == 'x')
			{
				list.add(new Node(nextY, nextX));
				visit[nextY][nextX] = true;
				if(nextY == Y - 1)
					isContinue = true;
				DFS(nextY, nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		Y	= read();
		X	= read();
		map	= new char[Y][X];
		
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
				map[y][x] = (char)System.in.read();
			System.in.read();
		}
		boolean flag = true;// true : 왼쪽에서 오른쪽, false : 오른쪽에서 왼쪽
		int T = read();
		while(T-->0)
		{
			int y	= Y - read();		// 어느 위치에 창을 던지는지 체크
			int x	= findX(y, flag);	// 어느 x좌표인지 체크
			flag	= !flag;
			
			if(x == -1)					// 미네랄이 없다면 스킵
				continue;
			
			map[y][x] = '.';			// 해당 위치의 미네랄을 파괴
			// 파괴된 위치를 기준으로 상하좌우 미네랄이 있는지 체크, 미네랄이 있다면 그 클러스터를 DFS로 탐색해 땅에 닿지 않은 것을 파악함
			visit = new boolean[Y][X];
			for(int xy[] : dxy)
			{
				int nextY = y + xy[0];
				int nextX = x + xy[1];
				if(0<=nextY && 0<=nextX && nextY<Y && nextX<X && !visit[nextY][nextX] &&
						map[nextY][nextX] == 'x')
				{
					if(nextY == Y - 1)	// 좌표가 땅바닥에 닿았으면 스킵
						continue;
					// 해당 좌표(nextY,nextX)를 시작으로 DFS를 돌면서 가장 낮은 Y 값이 땅에 닿아있는지 체크한다.
					list		= new ArrayList<>();// 떨어뜨릴 클러스터 좌표들을 담음리스트
					isContinue	= false;
					
					list.add(new Node(nextY, nextX));
					visit[nextY][nextX] = true;
					
					DFS(nextY, nextX);// 같은 클러스터 들은 list에 담음
					
					// 땅에 닿지 않은 좌표를 찾을 때 해당 좌표들 중 x를만나기 까지 가장 짧은 길이를 찾음
					if(!isContinue)
					{
						Collections.sort(list, (a,b)->b.y - a.y);
						
						boolean check[] = new boolean[X];// 중복 탐색을 방지해야 하기 때문에 x좌표는 딱 한번만 탐색함
						int minDist		= 101;// list의 좌표 중, 자기y좌표 + 1이 x가 아닌 좌표들 중 x를 만나기 까지 가장 짧은 이동거리
						
						for(Node now : list)
						{
							int nextY1	= now.y + 1;
							int dist	= 1;
							
							if(nextY1 < Y && map[nextY1][now.x] == '.')
							{
								if(check[now.x])
									continue;
								check[now.x] = true;
								while(nextY1 + 1 < Y && map[nextY1+1][now.x]=='.')
								{
									++nextY1;
									++dist;
								}
								minDist = Math.min(minDist, dist);
							}
						}
						
						// minDist만큼 y값에 플러스하여 내린다.
						for(Node now : list)
						{
							map[now.y][now.x] = '.';
							map[now.y+minDist][now.x]= 'x'; 
						}
						
						break;// 두 개 또는 그 이상의 클러스터가 동시에 떨어지는 경우도 없다하였으므로 하나만 떨어뜨리면 끝 
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
				sb.append(map[y][x]);
			if(y != Y-1)
				sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}