// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{
	int y,x, time, energy, durab;
	Node(int y, int x, int time, int energy, int durab){
		this.y=y; this.x=x;		this.time=time;
		this.energy=energy;		this.durab=durab;
	}
}
class Main{
	
	public static void main(String[] args)throws Exception{
		int result = Integer.MAX_VALUE;
		int	dxy[][]			= {{1,0},{0,1},{-1,0},{0,-1}};
		BufferedReader	br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		ArrayDeque<Node> q	= new ArrayDeque<>();
		int N 				= Integer.parseInt(st.nextToken());	// 맵 크기
		int H 				= Integer.parseInt(st.nextToken());	// 현재 체력 H
		int durab			= Integer.parseInt(st.nextToken());	// 우산의 내구도 
		char map[][]		= new char[N+2][N+2];				// 맵
		int totalEnergy[][]	= new int[N+2][N+2];				// 위치당 도달하기 까지 남은 총체력(현재체력 + 우산내구도)
		
		for(int i=0; i<N+2; i++)
			map[i][0] = map[0][i] = map[N+1][i] = map[i][N+1] = 'x';
		
		int	sy=0, sx=0, ey=0, ex=0;
		for(int y=1; y<=N; y++) 
		{
			String str = br.readLine();
			for(int x=1; x<=N; x++) 
			{ 
				map[y][x]	= str.charAt(x-1);
				if(map[y][x] == 'S') 
				{
					sy = y;
					sx = x;
				}
				if(map[y][x] == 'E') 
				{ 
					ey = y;
					ex = x;
				}
			}
		}
		
		totalEnergy[sy][sx] = H;
		q.add(new Node(sy, sx, 0, H, 0));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int xy[] : dxy) 
			{
				int nextY		= xy[0] + now.y;	// 다음 좌표
				int nextX		= xy[1] + now.x;	// 다음 좌표
				int nextDurab	= now.durab;		// 우산 내구도
				int nextEnergy	= now.energy;		// 체력
				int nextTime	= now.time + 1;		// 도달 시간
				
				if(map[nextY][nextX] == 'x')		// 유효하지 않은 범위인 경우 
					continue;
				
				if(map[nextY][nextX] == 'U')		// 우산일 경우 우산 내구도 변경
					nextDurab = durab;
				
				if(nextDurab > 0)					// 우산이 있으면 우산 내구도를 깍는다.
					nextDurab--;
				else if(nextEnergy > 0)				// 우산이 없으면 체력을 깍는다.
					nextEnergy--;
				
				if(nextEnergy == 0)					// 체력이 동나면 종료 
					continue;
				
				// 맨허튼 거리를 구해서 종료까지 다이렉트로 갈 수 있다면 미리 종료시간을 구해 불필요한 탐색을 줄인다.
				int Manhattan_Distance = Math.abs(nextY - ey) + Math.abs(nextX - ex);
				if(Manhattan_Distance <= nextEnergy + nextDurab) 
				{
					result = Math.min(Manhattan_Distance + nextTime, result);
					continue;
				}

				if(totalEnergy[nextY][nextX] < nextEnergy + nextDurab) 
				{
					totalEnergy[nextY][nextX] = nextEnergy + nextDurab;
					q.add(new Node(nextY, nextX, nextTime, nextEnergy, nextDurab));
				}
			}
			
		}
		if(result == Integer.MAX_VALUE)
			result = -1;
		System.out.print(result);
	}
}