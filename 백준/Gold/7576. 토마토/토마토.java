//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7576
import java.util.ArrayDeque;

class Node{int y, x; Node(int y, int x){this.y=y; this.x=x;}}

class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
    public static void main(String[] args)throws Exception{
    	final int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};
    	ArrayDeque<Node> q = new ArrayDeque<>();
    	int X		= read();				// 가로행 (2<=천)
    	int Y		= read();				// 세로행 (2<=천)
    	int res		= 0;					// 모두 익을 때 까지 걸린 일수
    	int zero	= 0;					// 안익은 토마토 개수
    	int map[][] = new int[Y+2][X+2];	// 토마토의 위치를 나타낼 것
    	
    	for(int y=0; y<=Y+1; y++)			// 패딩 삽입
    		map[y][0] = map[y][X+1] = -1;
    	for(int x=0; x<=X+1; x++)			// 패딩 삽입
    		map[0][x] = map[Y+1][x] = -1;
    	
    	for(int y=1; y<=Y; y++)
    	{
    		for(int x=1; x<=X; x++)
    		{
    			map[y][x] = read();
    			if(map[y][x] == 1)
    				q.add(new Node(y, x));
    			else if(map[y][x] == 0)		// 안익은 토마토 개수 카운팅
    				zero++;
    		}
    	}
    	
    	while(!q.isEmpty())
    	{
    		int size = q.size();
    		while(size-- >0)
    		{
    			Node now = q.poll();
    			for(int xy[] : dxy)
    			{
    				int nextY = xy[0] + now.y;
    				int nextX = xy[1] + now.x;
    				if(map[nextY][nextX] == 0)
    				{
    					zero--;
    					map[nextY][nextX] = 1;
    					q.add(new Node(nextY, nextX));
    				}
    			}
    		}
    		res++;
    	}
    	// 안익은 토마토가 있다면 -1 출력
    	System.out.print(zero == 0 ? res - 1 : -1);
    }

}