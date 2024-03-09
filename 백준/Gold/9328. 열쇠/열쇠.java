// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	
	static int T, Y, X, doc, nextX, nextY;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static char c, map[][];
	static boolean key[], visit[][];
	static ArrayDeque<Point>[] list;
	static ArrayDeque<Point> q;
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
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb 	= new StringBuilder();
		StringTokenizer st;
		T 		= Integer.parseInt(br.readLine());
		list	= new ArrayDeque[26];
		String str;
		while(T-->0) 
		{
			st 		= new StringTokenizer(br.readLine());
			Y 		= Integer.parseInt(st.nextToken());
			X 		= Integer.parseInt(st.nextToken());
			map 	= new char[Y+2][X+2];
			key 	= new boolean[26];
			visit 	= new boolean[Y+2][X+2];
			q		= new ArrayDeque<>();
			doc		= 0;
			for(int i=0; i<26; i++)
				list[i] = new ArrayDeque<>();
			
			for(int y=1; y<=Y; y++) 
			{
				str = br.readLine();
				for(int x=1; x<=X; x++)
					map[y][x] = str.charAt(x-1);
			}
			str = br.readLine();
			if(str.charAt(0) != '0')
				for(int i=0; i<str.length(); i++) 
					key[str.charAt(i)-'a'] = true;
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