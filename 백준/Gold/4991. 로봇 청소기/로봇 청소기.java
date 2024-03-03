// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;


class Main{
	public static void main(String[] args)throws Exception{new Main().solution();}
	
	class Node{
		int y, x, dist;
		Node(int y, int x, int dist){
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
	
	int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	int Y, X, idx, value, startY, startX, BITMASK;
	int map[][], visit[][][];
	BufferedReader br;
	StringTokenizer st;
	StringBuilder sb;
	ArrayList<Node>[] list;
	void solution()  throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String str;
		while(true) 
		{
			st	= new StringTokenizer(br.readLine());
			X 	= Integer.parseInt(st.nextToken());
			Y 	= Integer.parseInt(st.nextToken());
			map = new int[Y][X];
			idx = 1;
			if(X==0 &&Y==0) 							// 종료 조건
				break;
			
			for(int y=0; y<Y; y++) 
			{
				str = br.readLine();
				for(int x=0; x<X; x++) 
				{
					char c = str.charAt(x);
					if(c == '.') value = 0;
					else if(c == 'x') value = -1;
					else if(c == 'o') {
						value = 1;		// 시작점은 항상 1이다.
						startY = y;
						startX = x;
					}
					else if(c == '*') value = ++idx;
					map[y][x] = value;
				}
			}
			
			list = new ArrayList[idx+1];				// 인접 리스트
			for(int i=0; i<idx+1; i++)
				list[i] = new ArrayList<>();

			int listIndex = 1;
			BFS(listIndex, startY, startX);				// 시작노드가 모든 노드로 갈 수있는지 체크 + 인접 리스트 거리 저장
			if(list[1].size() != idx-1)					// 시작노드가 모든 *로 못간다면 종료
				sb.append(-1).append('\n');
			else 
			{
				for(int y=0; y<Y; y++)
					for(int x=0; x<X; x++)
						if(map[y][x] > 1)				// 각 노드의 연결 거리를 인접 리스트에 저장
							BFS(++listIndex,y,x);
				
				// DFS를 통해 1번 노드부터 모든 노드까지 방문하는데 걸리는 최소 합을 구함
				value = 10000;
				BITMASK = (1<<idx) -1;
				DFS(1, 1, 0);
				sb.append(value).append('\n');
			}
			
		}
		System.out.println(sb);
	}
	public void BFS(int listIndex, int y, int x) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		boolean visit[][] = new boolean[Y][X];
		int nextY, nextX, nextDist;
		q.add(new Node(y,x,0));
		visit[y][x] = true;
		map[y][x] = 0;								// 방문한 장소는 0으로 만들어 다시 연결하지 않도록 함
		
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
	public void DFS(int index, int bitmask, int distSum) 
	{
		if(bitmask == BITMASK) 
		{
			if(value > distSum) value = distSum;
			return;
		}
		
		for(Node next : list[index])
			if((bitmask & (1<<(next.y-1))) == 0) 								// 방문하지 않았을 경우 
				DFS(next.y, bitmask | (1<<(next.y-1)), next.dist + distSum);	// 다음노드, 비트마스크에 방문 체킹, 다음 거리 합
	}
	
}