// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	
	static int c, T, Y, X, doc, nextX, nextY;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static char map[][];
	static boolean key[], visit[][];
	static ArrayDeque<Point>[] list;
	static ArrayDeque<Point> q;
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		return n;
	}
	public static void BFS() {
		q = new ArrayDeque<>();
		visit[0][0] = true;
		q.add(new Point(0,0));
		
		while(!q.isEmpty()) 
		{
			Point now = q.poll();
			
			for(int xy[] : dxy) {
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				
				if(nextY>=0 && nextX>=0 && nextY<Y+2 && nextX<X+2 && !visit[nextY][nextX] && map[nextY][nextX] != '*') {
					c = map[nextY][nextX];
					visit[nextY][nextX] = true;
					if(c == '$')
						doc++;
					else if('A' <= c && c <= 'Z') 				// 문일 경우 
					{
						c -= 'A';
						if(!key[c])	{							// 키가 없다면 위치를 저장하고, 현재 큐에는 담지 않는다. 
							list[c].add(new Point(nextY, nextX));
							continue;
						}
					}else if('a' <= c && c <='z') 				// 방문한 곳이 키일 경우 
					{
						c-='a';
						key[c] = true;							// 키를 소유한다.
						while(!list[c].isEmpty()) 				// 해당 키때문에 못 갔던 문의 위치를 큐에 넣어 다시 탐색토록 한다.
							q.add(list[c].poll());
					}
					q.add(new Point(nextY, nextX));
				}
			}
			
		}
		
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb 	= new StringBuilder();
		T 		= read();
		list	= new ArrayDeque[26];
		while(T-->0) 
		{
			Y 		= read();
			X 		= read();
			map 	= new char[Y+2][X+2];
			key 	= new boolean[26];
			visit 	= new boolean[Y+2][X+2];
			q		= new ArrayDeque<>();
			doc		= 0;
			for(int i=0; i<26; i++)
				list[i] = new ArrayDeque<>();
			
			for(int y=1; y<=Y; y++) 
			{
				for(int x=1; x<=X; x++)
					map[y][x] = (char)System.in.read();
				System.in.read();
			}
			
			while((c = System.in.read())>32) {
				if(c != '0')
					key[c-'a'] = true;
			}
			BFS();
			sb.append(doc)
				.append('\n');
		}
		System.out.println(sb);
	}
}
class Point
{
	int y, x;
	Point(int y, int x)
	{
		this.y = y; this.x = x;
	}
}