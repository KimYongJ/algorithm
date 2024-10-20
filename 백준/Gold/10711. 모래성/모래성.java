//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10711

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{
	int y, x; Node(int y, int x){this.y=y; this.x=x;}
}
class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][]	= {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
		BufferedReader	br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		int Y				= Integer.parseInt(st.nextToken());
		int X				= Integer.parseInt(st.nextToken());
		int map[][]			= new int[Y][X];
		ArrayDeque<Node> q	= new ArrayDeque<>();
		
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
			{
				char c = str.charAt(x);
				if(c != '.')
					map[y][x] = c - '0';
				else 
					q.add(new Node(y,x));
			}
		}
		int T = -1;
		while(!q.isEmpty())
		{
			int size = q.size();
			while(size-->0)
			{
				Node now = q.poll();
				for(int xy[] : dxy)
				{
					int nextY = now.y + xy[0];
					int nextX = now.x + xy[1];
					if(0<=nextY && 0<=nextX && nextY<Y && nextX<X && map[nextY][nextX] != 0)
					{
						if(--map[nextY][nextX] == 0)
							q.add(new Node(nextY, nextX));
					}
				}
			}
			T++;
		}
		
		System.out.print(T);
	}

}