// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Main{
	
	static int X, Y, result1=-1, result2, cnt, cnt_one, arr[][];
	static int nextX, nextY;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static boolean visit[][];
	static ArrayDeque<int[]> q1, q2;

	
	// 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
    // BFS를 돌 때 유효성 검사
	public static boolean validate(int x, int y) {
		return x>=0 && y>=0 && x<X && y<Y && !visit[y][x];
	}
	public static void BFS(ArrayDeque<int[]> q, boolean useQ1) {
		
		cnt = 0;
		
		while(!q.isEmpty()) 
		{
			int[] now = q.poll();
			
			for(int i=0; i<4; i++) 
			{
				nextX = now[0] + dx[i];
				nextY = now[1] + dy[i];
				if(validate(nextX, nextY)) 
				{
					visit[nextY][nextX] = true; 		// 방문처리
					if(arr[nextY][nextX] == 0) 
					{
						q.add(new int[] {nextX,nextY}); // 0인 경우만 큐에 넣는다.
					}else if(arr[nextY][nextX] == 1) 
					{
						cnt ++; 						// 1의 갯수 카운팅
						if(useQ1) 
						{ 
							// q1를 현재 BFS에서 쓰고있다면, 다음 BFS할 때 q2을써야 함으로 q2에 다음 데이터를 담는다.
							q2.add(new int[] {nextX,nextY});							
						}else 
						{
							// q2를 현재 BFS에서 쓰고있다면, 다음 BFS할 때 q1을써야 함으로 q1에 다음 데이터를 담는다.
							q1.add(new int[] {nextX,nextY});
						}
						
					}
				}
			}
		}
		
		cnt_one = cnt; // 이전 1의 갯수를 구하기 위한 코드 
	}
	public static void main(String[] args)throws Exception{
		Y 		= read();
		X 		= read();
		arr 	= new int[Y][X];
		visit 	= new boolean[Y][X];
		q1 		= new ArrayDeque<>();
		q2 		= new ArrayDeque<>();
		
		for(int i=0; i<Y; i++) 
			for(int j=0; j<X; j++) 
			{
				arr[i][j] = read();
				if(arr[i][j] == 1) cnt_one++;
			}

		q1.add(new int[] {0,0}); 			// 큐 2개중 먼저쓸 q1에 0,0을 입력
		
		while(cnt_one != 0)					// BFS를 통해 확인 후 1이였던 값이 하나도 없는 경우 종료 
		{				
			result1++; 						// 총 카운팅 갯수
			result2 = cnt_one; 				// 종료직전의 1의 갯수
			if(q1.size() == 0 ) 
			{
				BFS(q2, false);				// 비어있지 않은 큐를 전달하고, 어떤 큐가 비어있지 않은지를 true false로 알 수 있게 전달함
			}else 
			{
				BFS(q1, true);				// 비어있지 않은 큐를 전달하고, 어떤 큐가 비어있지 않은지를 true false로 알 수 있게 전달함
			}
		}
		System.out.println(result1);
		System.out.println(result2);
	}
}