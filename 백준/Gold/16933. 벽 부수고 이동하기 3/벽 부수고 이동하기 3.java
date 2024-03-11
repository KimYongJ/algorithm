// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{
	int y, x, cnt, k;
	Node(int y, int x, int cnt, int k){
		this.y=y; this.x=x; this.cnt=cnt; this.k=k;
	}
}
class Main
{
	static int Y, X, K, nextY, nextX, nextK, nextCnt, map[][];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean flag, visit[][][];
	static ArrayDeque<Node> q;
	public static void BFS() {
		q = new ArrayDeque<>();
		q.add(new Node(1,1,0,0));
		visit[0][1][1] = true; // 방문 처리
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(now.y == Y && now.x == X) 
			{
				System.out.println(now.cnt+1);
				return;
			}
			nextCnt 	= now.cnt 	+ 1;
			nextK 		= now.k 	+ 1;
			flag		= now.cnt % 2 == 0; // 나머지가 0이면 낮이다. 
			for(int xy[] : dxy) 
			{
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(map[nextY][nextX] == 0 && !visit[now.k][nextY][nextX])	// 가려는 곳이 그냥 갈 수 있는 경우
				{
					visit[now.k][nextY][nextX] = true;
					q.add(new Node(nextY, nextX, nextCnt, now.k));
				}
				else if(map[nextY][nextX] == 1 && now.k < K) // 가려는 곳이 벽일 경우 + 벽을 부술 수 있을 때 
				{
					if(flag) 			// 낮인 경우
					{
						if(!visit[nextK][nextY][nextX]) 
						{
							visit[nextK][nextY][nextX] = true;
							q.add(new Node(nextY, nextX, nextCnt, nextK));
						}
					}
					else 					// 갈 수 있는데 밤인 경우
						q.add(new Node(now.y, now.x, nextCnt, now.k));
				}
			}
		}
		System.out.println(-1);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		K 		= Integer.parseInt(st.nextToken());
		map 	= new int[Y+2][X+2];
		visit 	= new boolean[K+1][Y+2][X+2];
		// 패딩 넣기
		for(int y=0; y<Y+2; y++)map[y][0] = map[y][X+1] = -1;
		for(int x=0; x<X+2; x++)map[0][x] = map[Y+1][x] = -1;
		// 값 입력 받기
		for(int y=1; y<=Y; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=X; x++) {
				map[y][x] = str.charAt(x-1)-'0';
			}
		}
		BFS();
	}
}

