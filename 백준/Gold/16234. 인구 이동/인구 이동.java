//https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.HashMap;

class Point{
	int i, j;
	Point(int i, int j){
		this.i = i;
		this.j = j;
	}
}
class Main{
	
	static final int BLOCK = 100_000_000;
	static int N, L, R, cnt, arr[][], flag, flag_arr[][];
	static int population, areacnt;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean change, visit[][];
	static HashMap<Integer, Integer> flag_map;
	static ArrayDeque<Point> q;
	// 빠른 입력을 위한 함수
    static int read() throws Exception 
    {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
    
	public static void changeArr() 
	{
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++) 
			{
				int key = flag_arr[i][j];
				if(key != 0)
					arr[i][j] = flag_map.getOrDefault(key,arr[i][j]);
			}
		
	}
	public static void BFS(int i, int j) 
	{
		boolean isVisit = false;
		population 		= 0;
		areacnt 		= 0;
		flag 			+= 1;
		q 				= new ArrayDeque<>();
		
		q.add(new Point(i,j));
		
		while(!q.isEmpty()) 
		{
			Point now = q.poll();
			
			if(visit[now.i][now.j]) continue; // 방문했던 곳은 연산 스킵
			visit[now.i][now.j] 	= true;
			flag_arr[now.i][now.j] 	= flag; 
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
						isVisit = true;
						q.add(new Point(nextI, nextJ));
					}
				}
				
			}
			
		}
		
		
		if(isVisit) { 							// 국경을 합친 지역이 하나라도 있다면
			change = true; 						// 국경을 합친적이 한번이라도 있다는 뜻
			int value = population / areacnt;	// 덮어써야할 인원
			flag_map.put(flag, value);			// 키 값으로 덮어써야할 인원을 저장해 놓는다.
		}
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
			flag 		= 0;
			change 		= false;
			flag_map 	= new HashMap<>();
			flag_arr 	= new int[N+2][N+2];
			visit 		= new boolean[N+2][N+2];
			
			for(int i=1; i<=N; i++)
				for(int j=1; j<=N; j++)
					if(!visit[i][j])
						BFS(i,j);
				
			
			if(change) 
			{
				cnt++;
				changeArr();
			}
		}
		System.out.println(cnt);
	}
}