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
		Reader in = new Reader();
		int result = Integer.MAX_VALUE;
		int	dxy[][]			= {{1,0},{0,1},{-1,0},{0,-1}};
		ArrayDeque<Node> q	= new ArrayDeque<>();
		int N 				= in.nextInt();	// 맵 크기
		int H 				= in.nextInt();	// 현재 체력 H
		int durab			= in.nextInt();	// 우산의 내구도 
		char map[][]		= new char[N+2][N+2];				// 맵
		int totalEnergy[][]	= new int[N+2][N+2];				// 위치당 도달하기 까지 남은 총체력(현재체력 + 우산내구도)
		
		for(int i=0; i<N+2; i++)
			map[i][0] = map[0][i] = map[N+1][i] = map[i][N+1] = 'x';
		
		int	sy=0, sx=0, ey=0, ex=0;
		for(int y=1; y<=N; y++) 
		{
			for(int x=1; x<=N; x++) 
			{ 
				map[y][x]	= in.nextChar();
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

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
        do sb.appendCodePoint(c);
        while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}

