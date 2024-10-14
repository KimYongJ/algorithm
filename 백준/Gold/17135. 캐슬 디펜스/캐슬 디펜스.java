//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17135
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{int y,x; Node(int y, int x){this.y=y; this.x=x;}}

class Main{
	
	static final int dxy[][] = {{0,-1},{-1,0},{0,1}};
	static int Y, X, D, res, map[][], origin[][];
	
	public static int[] getIdx(int y, int x)
	{
		ArrayDeque<Node> q = new ArrayDeque<>();
		boolean visit[][] = new boolean[Y+1][X];
		q.add(new Node(y,x));
		visit[y][x] = true;
		while(!q.isEmpty())
		{
			Node now = q.poll();
			if(map[now.y][now.x] == 1)
				return new int[] {now.y, now.x};
			
			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(0<= nextY && 0<=nextX && nextX < X && !visit[nextY][nextX])
				{
					int abs = Math.abs(y-nextY) + Math.abs(x-nextX);
					if(abs < D)
					{
						visit[nextY][nextX] = true;
						q.add(new Node(nextY, nextX));
					}
				}
				
			}
		}
		return new int[] {Y,0};
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y		= Integer.parseInt(st.nextToken());
		X		= Integer.parseInt(st.nextToken());
		D		= Integer.parseInt(st.nextToken());
		origin	= new int[Y+1][X];
		
		for(int y=0; y<Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++)
				origin[y][x] = Integer.parseInt(st.nextToken());
		}
		
		for(int a=0; a<X-2; a++)
			for(int b=a+1; b<X-1; b++)
				for(int c=b+1; c<X; c++)
				{
					map = new int[Y+1][X];
					for(int y=0; y<Y; y++)
						System.arraycopy(origin[y], 0, map[y], 0, X);
					// a,b,c가 궁수의 위치이다.
					int archerLine	= Y - 1;	// 궁수의 위치
					int cnt = 0;
					
					while(archerLine>=0)
					{
						// getIdx는 y,x로부터 절대거리가 가장가깝고 가장 왼쪽이고, map에서 1인 값을 가져온다.
						int pos[][] = new int[3][2];
						pos[0] = getIdx(archerLine, a);
						pos[1] = getIdx(archerLine, b);
						pos[2] = getIdx(archerLine, c);
						for(int p[] : pos)
							if(map[p[0]][p[1]] == 1)
							{
								map[p[0]][p[1]] = 0;
								cnt++;
							}
						--archerLine;
					}
					res = Math.max(cnt, res);
				}
		
		System.out.print(res);
	}
}