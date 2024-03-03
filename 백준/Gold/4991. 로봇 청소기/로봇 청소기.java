// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Node{
	int y, x, dist;
	Node(int y, int x, int dist){
		this.y = y;
		this.x = x;
		this.dist = dist;
	}
}

class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, idx, value, startY, startX, BITMASK;
	static int nextY, nextX, nextDist, nextDistSum, listIndex;
	static int map[][];
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static ArrayList<Node>[] list;
	static ArrayDeque<Node> q;
	static boolean visit[][];
	public static void DFS(int index, int bitmask, int distSum) 
	{
		if(bitmask == BITMASK) 
		{
			if(value > distSum) value = distSum;
			return;
		}
		
		for(Node next : list[index]) 
		{
			if((bitmask & (1<<(next.y-1))) == 0) 							// 방문하지 않았을 경우
			{
				nextDistSum = next.dist + distSum;
				if(nextDistSum < value)
					DFS(next.y, bitmask | (1<<(next.y-1)), nextDistSum);	// 다음노드, 비트마스크에 방문 체킹, 다음 거리 합
			}
		}
		
	}
	public static void BFS(int y, int x) {
		++listIndex;
		q 			= new ArrayDeque<>();
		visit 		= new boolean[Y][X];
		visit[y][x] = true;
		map[y][x] 	= 0;								                    // 방문한 장소는 0으로 만들어 다시 연결하지 않도록 함
		q.add(new Node(y,x,0));

		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			
			if(map[now.y][now.x] > 1) 
			{
				list[listIndex].add(new Node(map[now.y][now.x], 0, now.dist));	// 양방향 매핑
				list[map[now.y][now.x]].add(new Node(listIndex, 0, now.dist));	// 양방향 매핑
			}
			
			for(int xy[] : dxy) 
			{
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				nextDist = now.dist + 1;
				if(nextY >=0 && nextX >= 0 && nextY < Y && nextX < X && !visit[nextY][nextX] && map[nextY][nextX] != -1) 
				{
					visit[nextY][nextX] = true;
					q.add(new Node(nextY, nextX, nextDist));
				}
			}
		}
	}
	public static void main(String[] args)throws Exception
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String str;
		char c;
		while(true) 
		{
			st	= new StringTokenizer(br.readLine());
			X 	= Integer.parseInt(st.nextToken());
			Y 	= Integer.parseInt(st.nextToken());
			
			if(X==0 &&Y==0) break;		// 종료 조건
			
			listIndex 	= 0;
			map 		= new int[Y][X];
			idx 		= 1;
			
			for(int y=0; y<Y; y++) 
			{
				str = br.readLine();
				for(int x=0; x<X; x++) 
				{
					c = str.charAt(x);
					switch(c) 
					{
						case '.': value = 0; break;
						case 'x': value = -1; break;
						case '*': value = ++idx; break;
						default : 
							value = 1;					// 시작점은 항상 1이다.
							startY = y;
							startX = x;
					}
					map[y][x] = value;
				}
			}
			
			list = new ArrayList[idx+1];				// 인접 리스트
			for(int i=0; i<idx+1; i++)
				list[i] = new ArrayList<>();


			BFS(startY, startX);						// 시작노드가 모든 노드로 갈 수있는지 체크 + 인접 리스트 거리 저장
			if(list[1].size() != idx-1)					// 시작노드가 모든 *로 못간다면 종료
				sb.append(-1).append('\n');
			else 
			{
				for(int y=0; y<Y; y++)
					for(int x=0; x<X; x++)
						if(map[y][x] > 1)				// 각 노드의 연결 거리를 인접 리스트에 저장
							BFS(y,x);
				
				// DFS를 통해 1번 노드부터 모든 노드까지 방문하는데 걸리는 최소 합을 구함
				value = 10000;
				BITMASK = (1<<idx) -1;
				DFS(1, 1, 0);
				sb.append(value).append('\n');
			}
			
		}
		System.out.println(sb);
	}
	
}