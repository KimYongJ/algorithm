// https://github.com/kimyongj/algorithm
import java.util.ArrayList;

class Main{
	
	static int		result = Integer.MAX_VALUE;
	static int		N;				// 맵 크기
	static int		H;				// 현재 체력 H
	static int		durab;			// 우산의 내구도 
	static int		len; 			// 우산의 개수 
	static int		sy, sx, ey, ex;	// 시작 , 종료 위치
	static boolean	visit[];		// 우산의 방문 유무를 담을 배열
	static ArrayList<int[]> umbrella = new ArrayList<>();
	
	public static void backtracking(int y, int x, int hp, int dur, int time) {
		int manhattan = Math.abs(ey-y) + Math.abs(ex-x);
		if(manhattan <= hp + dur) 
		{
			result = Math.min(result, time + manhattan);
			return;
		}
		
		for(int i=0; i<len; i++) 
		{
			if(visit[i]) 
				continue;
			
			int xy[]	= umbrella.get(i);
			int dist	= Math.abs(y-xy[0]) + Math.abs(x-xy[1]);
			int nextHp	= hp;
			int nextDur = dur;
			if(dist <= nextHp + nextDur) 	// 다음 우산까지 갈 수 있을 때
			{
				nextDur -= dist;			// 우산 내구도를 거리만큼 깍는다.
				
				if(nextDur < 0)				// 우산 내구도가 0이하로 떨어진 경우 HP를 깎는다.
					nextHp += nextDur+1;	// nextDur는 마이너스일 것이기 때문에 nextHp에 더해준다. 이 때 +1을 해줌으로 써 새로 쓴 우산에서 데미지를 입게한다.
				
				if(nextHp <= 0) 			// 체력이 0 이하면 종료
					continue;
				
				visit[i] = true;
				backtracking(xy[0], xy[1], nextHp, durab - 1,time + dist);
				visit[i] = false;
			}
			
		}
	}
	public static void main(String[] args)throws Exception{
		IN in	= new IN();
		N 		= in.nextInt();	// 맵 크기
		H 		= in.nextInt();	// 현재 체력 H
		durab	= in.nextInt();	// 우산의 내구도 

		for(int y=0; y<N; y++) 
		{
			for(int x=0; x<N; x++) 
			{ 
				char c = in.nextChar();
				if(c == 'U') 
				{
					umbrella.add(new int[] {y,x});
				}
				else if(c == 'S') 
				{
					sy = y; 
					sx = x;
				}
				else if(c == 'E') 
				{ 
					ey = y; 
					ex = x;
				}
			}
		}
		
		len		= umbrella.size();
		visit	= new boolean[len];
		
		backtracking(sy, sx, H, 0, 0); // 현재 좌표 y, x, 현재 체력, 우산의 내구도, 걸린시간
		
		System.out.print(result == Integer.MAX_VALUE  ? -1 : result);
	}
}


class IN {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

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