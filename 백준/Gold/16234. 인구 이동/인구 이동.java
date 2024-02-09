// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.ArrayList;

class Point{
	int i, j;
	Point(int i, int j){
		this.i = i;
		this.j = j;
	}
}
class Main{
	
	static final int BLOCK = 100_000_000;
	static int N, L, R, cnt, value, arr[][];
	static int population, areacnt;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean change, visit[][];
	static ArrayDeque<Point> q;
	static ArrayList<int[]> position_list;
	// 빠른 입력을 위한 함수
    static int read() throws Exception 
    {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
    // 합친 지역의 인구수를 변경하는 함수
	public static void changeArr() 
	{
		for(int i=0; i<position_list.size(); i++) 
		{
			int[] p = position_list.get(i);
			arr[p[0]][p[1]] = value;
		}
	}
    // 합칠 수 있는지 역을 BFS로 찾은 후 합친 지역 좌표를 list에 담아 준다.
	public static void BFS(int i, int j, ArrayList<int[]> position_list) 
	{
		population 		= 0;
		areacnt 		= 0;
		q 				= new ArrayDeque<>();
		position_list.add(new int[] {i,j});
		q.add(new Point(i,j));
		
		while(!q.isEmpty()) 
		{
			Point now = q.poll();
			
			if(visit[now.i][now.j]) continue; // 방문했던 곳은 연산 스킵
			visit[now.i][now.j] 	= true;
			population 				+= arr[now.i][now.j];
			areacnt++;
			
			for(int xy[] : dxy) 
			{
				int nextI = now.i + xy[0];
				int nextJ = now.j + xy[1];
				
				if(!visit[nextI][nextJ]) 
				{
					int diff = Math.abs(arr[now.i][now.j] - arr[nextI][nextJ]);
					if(L<=diff && diff<=R) 
					{
						position_list.add(new int[] {nextI, nextJ});
						q.add(new Point(nextI, nextJ));
					}
				}
			}
			
		}
		
		if(position_list.size()>1) 				// 국경을 합친 지역이 하나라도 있다면
			value = population / areacnt;		// 인구를 합쳐 바꿔야할 값
	}
	
	public static void main(String[] args)throws Exception{
		N 	= read();
		L 	= read();
		R 	= read();
		arr = new int[N+2][N+2];
		// 겉에 패딩 넣기
		for(int i=0; i<=N+1; i++) 
			arr[0][i] = arr[N+1][i] = 
			arr[i][0] = arr[i][N+1] = BLOCK;
		// 갑 잆력 받기
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				arr[i][j] = read();
			
		change = true;
		
		while(change) 
		{
			change 		= false;
			visit 		= new boolean[N+2][N+2];
			
			for(int i=1; i<=N; i++)
				for(int j=1; j<=N; j++)
					if(!visit[i][j]) 
					{
						position_list = new ArrayList<>(); // 합친 지역의 위치를 담을 리스트
						BFS(i,j, position_list);
						if(position_list.size() > 1) // 합쳐진 지역이 하나이상 있다면 이하 실행
						{
							change = true;
							changeArr();
						}
					}
			if(change)
				cnt++;
		}
		System.out.println(cnt);
	}
}