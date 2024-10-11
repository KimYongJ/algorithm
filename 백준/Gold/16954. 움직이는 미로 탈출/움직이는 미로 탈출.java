//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16954
import java.util.ArrayDeque;

class Node{int y,x; Node(int y, int x){this.y=y; this.x=x;}}

class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][]	= {{0,0},{1,0},{0,1},{-1,0},{0,-1},{-1,1},{-1,-1},{1,1},{1,-1}};
		int T				= 8;	// 벽을 내릴 횟수
		int len				= 8;	// 배열의 길이
		int limitLine		= 1;	// 벽을 내릴 때 해당 라인 이상만 건든다.
		boolean map[][]		= new boolean[len+2][len+2];
		boolean visit[][][] = new boolean[len][len+2][len+2];
		ArrayDeque<Node> q	= new ArrayDeque<>();
		
		visit[0][len][1]	= true;
		q.add(new Node(len, 1));
		
		// 간단한 연산을 위해 맨위는 뚫려있게 개발
		for(int x=1; x<=len; x++)
			map[0][x] = true;
		
		for(int y=1; y<=len; y++)
		{
			for(int x=1; x<=len; x++)
				map[y][x] = System.in.read() == '.';// true가 갈 수 있는 곳
			System.in.read();
		}
		
		while(T-->0)
		{
			int size = q.size();
			while(size-->0)
			{
				Node now = q.poll();
				
				if(!map[now.y][now.x])
					continue;
				
				for(int xy[] : dxy)
				{
					int nextY = now.y + xy[0];
					int nextX = now.x + xy[1];
					if(map[nextY][nextX] && !visit[T][nextY][nextX])
					{
						visit[T][nextY][nextX] = true;
						q.add(new Node(nextY, nextX));
					}
				}
			}
			// 벽을 한칸씩 내린다.
			for(int y=len; y>=limitLine; y--)
				for(int x=1; x<=len; x++)
					map[y][x] = map[y-1][x];
			
			++limitLine;
		}
		
		System.out.print(q.isEmpty() ? 0 : 1);
	}
}