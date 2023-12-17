// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


class Main{
	static class Node{
		int y, x, time;
		boolean isfire;
		Node(int y, int x, int time, boolean isfire){
			this.y 		= y;
			this.x 		= x;
			this.time 	= time;
			this.isfire = isfire;
		}
	}
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static int T, Y, X, startY=0, startX=0;
	static char c, map[][];
	static String str;
	static ArrayDeque<Node> q;
	static StringTokenizer st;
	static BufferedReader br;
	static StringBuilder sb;
	
	public static void BFS() {
		q.add(new Node(startY, startX, 1, false)); // isfire를 false로하여 상근이라는 것을 인식, time은 시작부터 1로한다.
		
		while(!q.isEmpty()) 
		{
			Node now = q.poll(); // 큐데이터를 꺼내온다.
			
			if((now.y == Y-1 || now.x == X-1 || now.y==0 || now.x==0) && !now.isfire) 
			{// 종료 조건 : 테두리에 도달했고, 상근이일 경우
				sb.append(now.time).append('\n');
				return;
			}
			for(int i=0; i<4; i++) 
			{
				int y = now.y + dy[i];
				int x = now.x + dx[i];
				
				// 좌표 유효성 검증 및 벽이 아닐 때 연산실행
				if(x>=0 && y>=0 && x<X && y<Y && map[y][x] !='#') 
				{
					if(now.isfire && map[y][x] != '*') 
					{ // 불일 경우
						map[y][x] = '*';
						q.offer(new Node(y, x, 0, true));
					}else if(!now.isfire && map[y][x] == '.') 
					{ // 불이 아니고 좌표가 이동가능할 경우
						q.offer(new Node(y,x, now.time +1, false));
						map[y][x] = '*'; // 상근이가 방문해서 큐에 넣은 좌표는 불로 처리해 두번 다시 방문하지 못하게 합니다. 
					}
				}
			}
		}
		// 여기까지 왔다는 것은 상근이가 테두리에 못도달했다는 것.
		sb.append("IMPOSSIBLE").append('\n');
	}
	public static void main(String[] args)throws Exception{
		sb						= new StringBuilder();
		br 						= new BufferedReader(new InputStreamReader(System.in));
		T 						= Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			st 					= new StringTokenizer(br.readLine());
			q 					= new ArrayDeque<Node>();
			X 					= Integer.parseInt(st.nextToken());
			Y 					= Integer.parseInt(st.nextToken());
			map 				= new char[Y][X];
			
			for(int y=0; y<Y; y++) 
			{
				str 			= br.readLine();
				for(int x=0; x<X; x++) 
				{
					c 			= str.charAt(x);
					map[y][x] 	= c;
					if(c=='*') 
					{ // 불일 경우 큐에 먼저 넣어 먼저 연산되도록 한다.
						q.offer(new Node(y, x, 0, true));
					}else if(c=='@') 
					{ // 상근이일 경우 마지막에 큐에 넣어야 한다 빠른 연산을 위해 상근이 자리도 불로 처리해서 방문 하지 못하도록함 
						startY 	= y;
						startX 	= x;
						map[y][x] = '*';
					}
				}
			}
			/**************** BFS **********************/
			BFS();
			/*******************************************/
		}
		System.out.println(sb);
	}
}