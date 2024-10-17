//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/2933
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Node{int y, x; Node(int y, int x){this.y=y; this.x=x;}}

class Main{
	
	static int	dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int	Y, X;
	static char	map[][];
	
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
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new char[Y][X];
		
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
				map[y][x] = str.charAt(x);
		}
		boolean flag = true;// true : 왼쪽에서 오른쪽, false : 오른쪽에서 왼쪽
		int T = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while(T-->0)
		{
			int y = Y - Integer.parseInt(st.nextToken());	// 어느 위치에 창을 던지는지 체크
			int x = findX(y, flag);// 어느 x좌표인지 체크
			flag = !flag;
			if(x == -1)// 미네랄이 없다면 스킵
				continue;
			
			map[y][x] = '.';	// 해당 위치의 미네랄을 파괴
			// 파괴된 위치를 기준으로 상하좌우 미네랄이 있는지 체크, 미네랄이 있다면 그 클러스터를 bfs로 탐색해 땅에 닿지 않은 것을 파악함
			boolean visit[][] = new boolean[Y][X];
			for(int xy[] : dxy)
			{
				int nextY = y + xy[0];
				int nextX = x + xy[1];
				if(0<=nextY && 0<=nextX && nextY<Y && nextX<X && !visit[nextY][nextX] &&
						map[nextY][nextX] == 'x')
				{
					if(nextY == Y - 1)	// 좌표가 땅바닥에 닿았으면 스킵
						continue;
					// 해당 좌표(nextY,nextX)를 시작으로 bfs를 돌면서 가장 낮은 Y 값이 땅에 닿아있는지 체크한다.
					ArrayDeque<Node> q		= new ArrayDeque<>();	// bfs시 사용
					ArrayList<Node> list	= new ArrayList<>();	// 떨어뜨릴 클러스터 좌표들을 담음
					boolean isContinue		= false;
					
					list.add(new Node(nextY, nextX));
					q.add(new Node(nextY, nextX));
					visit[nextY][nextX] = true;
					
					while(!q.isEmpty())
					{
						Node now = q.poll();
						for(int xy1[] : dxy)
						{
							int nextY1 = now.y + xy1[0];
							int nextX1 = now.x + xy1[1];
							if(0<=nextY1 && 0<=nextX1 && nextY1<Y && nextX1<X && !visit[nextY1][nextX1]
								&& map[nextY1][nextX1] == 'x')
							{
								list.add(new Node(nextY1, nextX1));
								q.add(new Node(nextY1, nextX1));
								visit[nextY1][nextX1] = true;
								if(nextY1 == Y - 1)
									isContinue = true;
							}
						}
					}
					// 땅에 닿지 않은 좌표를 찾을 때 해당 좌표들 중
					if(!isContinue)
					{
						boolean check[] = new boolean[X];
						Collections.sort(list, (a,b)->b.y - a.y);
						// list의 좌표 중, 자기y좌표 + 1이 x가 아닌 좌표들 중 x를 만나기 까지 가장 짧은 이동거리
						int minDist = 101;
						for(Node now : list)
						{
							int nextY1 = now.y + 1;
							int dist = 1;
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