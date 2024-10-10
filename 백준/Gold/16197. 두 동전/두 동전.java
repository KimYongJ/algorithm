//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16197
import java.util.ArrayDeque;

class Node{
	int y1, y2, x1, x2;
	Node(int y1, int x1, int y2, int x2){
		this.y1=y1; this.x1=x1; this.y2=y2;this.x2=x2;	
	}
}

class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		final int dxy[][]		= {{1,0},{0,1},{-1,0},{0,-1}};
		int Y					= read();
		int X					= read();
		int map[][]				= new int[Y][X];
		int s[][]				= new int[2][2];
		int idx					= 0;
		boolean visit[][][][]	= new boolean[Y][X][Y][X];
		ArrayDeque<Node> q		= new ArrayDeque<>();
		
		int c;
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
				if((c = System.in.read()) == '#')
					map[y][x] = 1;
				else if(c == 'o')
				{
					s[idx][0] = y;
					s[idx][1] = x;
					idx++;
				}
			System.in.read();
		}
		
		
		q.add(new Node(s[0][0], s[0][1], s[1][0], s[1][1]));
		visit[s[0][0]][s[0][1]][s[1][0]][s[1][1]] = true;
		
		int cnt = 0;
		
		while(!q.isEmpty() && ++cnt < 11)
		{
			int size = q.size();
			while(size-->0)
			{
				Node now = q.poll();
				for(int xy[] : dxy)
				{
					int nY1			= now.y1 + xy[0];
					int nX1			= now.x1 + xy[1];
					int nY2			= now.y2 + xy[0];
					int nX2			= now.x2 + xy[1];
					boolean flag1	= 0<=nY1 && nY1<Y && 0<=nX1 && nX1<X;
					boolean flag2	= 0<=nY2 && nY2<Y && 0<=nX2 && nX2<X;
					
					if( (!flag1 && flag2) || (flag1 && !flag2))
					{
						System.out.print(cnt);
						return;
					}
					
					if(!flag1 && !flag2)
						continue;
					
					if(map[nY1][nX1] == 1)
					{
						nY1 = now.y1;
						nX1 = now.x1;
					}
					
					if(map[nY2][nX2] == 1)
					{
						nY2 = now.y2;
						nX2 = now.x2;
					}
					
					if( !(nY1 == nY2 && nX1 == nX2) && !visit[nY1][nX1][nY2][nX2])
					{
						visit[nY1][nX1][nY2][nX2] = true;
						q.add(new Node(nY1, nX1, nY2, nX2));
					}
				}
			}
		}
		System.out.print(-1);
	}
}