// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main
{
	public static void main(String[] args)throws Exception{new Main().solution();}
	
	int N, M, map[][], node[][], parent[];
	int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	PriorityQueue<Point> pq;
	
	void solution()throws Exception
	{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		N 					= Integer.parseInt(st.nextToken());
		M 					= Integer.parseInt(st.nextToken())+2;
		map					= new int[N][N];						// 입력을 담을 map
		node				= new int[M][2];						// S와 K의 좌표 정보를 담을 2차원 배열 [노드인덱스][좌표]
		parent				= new int[M];							// 최소 스패닝 트리 구할 때 사용, 부모노드를 담는 배열
		pq					= new PriorityQueue<Point>((a,b)->a.cnt-b.cnt);
		
		int idx = 1, input;
		for(int i=0; i<N; i++) 
		{
			String str = br.readLine();
			for(int j=0; j<N; j++) 
			{
				char c = str.charAt(j);
				if(c=='1') 		input = -1;
				else if(c=='0') input = 0;
				else if(c=='S') 
				{
					input = 1;
					node[input][0] = i;
					node[input][1] = j;
				}
				else {
					input =	++idx;
					node[input][0] = i;
					node[input][1] = j;
				}
				map[i][j] = input;
			}
		}
		

		for(int m=1; m<M; m++) 
		{
			BFS(m,node[m][0], node[m][1]);		// 각 노드들의 거리를 구함
			parent[m] = m;						// 자기 자신으로 초기화
		}
		
		
		int sum = 0, aParent, bParent;
		while(M>2)
		{
			if(pq.size() != 0) {
				Point now = pq.poll();
				aParent = getParent(now.y);				// 부모 노드를 가져온다. 
				bParent = getParent(now.x);				// 부모 노드를 가져온다.
				
				if(aParent != bParent) 
				{
					M--;								// 연결 카운팅 개념.
					if(aParent > bParent)
						parent[aParent] = bParent; 		// 부모 노드의 부모를 바꾼다.
					else
						parent[bParent] = aParent;		// 부모 노드의 부모를 바꾼다.
					sum += now.cnt;
				}
			}else {
				System.out.println(-1); System.exit(0);
			}
			
		}
		
		if( countArea() ) 							// 최소 스패닝 트리 라면 구역이 1개인데, 구역이 2개이상일 경우 -1 출력
			sum = -1;
	
		System.out.println(sum);
			
	}
	public boolean countArea() 
	{
		int cnt = 0;
		for(int i=1; i<M; i++)
			if(parent[i]==i) {
				if(++cnt == 2) {
					return true;
				}
			}
		
		return false;
	}
	public int getParent(int x) 
	{
		if(parent[x] == x) return x;
		return getParent(parent[x]);
	}
	public void BFS(int baseIndex, int y, int x)
	{
		ArrayDeque<Point> q = new ArrayDeque<>();
		boolean visit[][] = new boolean[N][N];
		q.add(new Point(y,x,0));
		visit[y][x] = true;			// 방문 처리
		map[y][x] = 0;				// 구한 곳은 0으로 변경하여 다음 탐색에서 탐색되지 않도록 합니다. 
		int nextY, nextX, nextCnt;
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(map[now.y][now.x]>1) 
			{
				pq.add(new Point(baseIndex, map[now.y][now.x], now.cnt)); // 우선수위 큐에 데이터 삽입하여 최소 스패닝 트리를 탐색토록함
				continue;
			}
			
			for(int xy[] : dxy) 
			{
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				nextCnt = now.cnt + 1;
				if(map[nextY][nextX] >= 0 && !visit[nextY][nextX]) {
					visit[nextY][nextX] = true;
					q.add(new Point(nextY, nextX, nextCnt));
				}
			}
		}
	}

	class Point{
		int y, x, cnt;
		Point(int y, int x, int cnt){
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
}
