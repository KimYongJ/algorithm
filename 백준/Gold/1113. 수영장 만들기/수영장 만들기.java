//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1113
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node{
	int y, x, h;
	Node(int y, int x){this.y=y; this.x=x;}
	Node(int y, int x, int h){this.y=y; this.x=x; this.h=h;}
}
public class Main {
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int result;
	static int Y, X, map[][];
	static boolean outOfRange, check[][];
	
	public static void BFS(Node start) {
		ArrayList<Node> position	= new ArrayList<>();
		ArrayDeque<Node> q			= new ArrayDeque<>();
		boolean visit[][]			= new boolean[Y][X];
		int height					= start.h;
		int minHeight				= 10;
		visit[start.y][start.x]		= true;
		outOfRange					= false;
		
		position.add(start);
		q.add(start);
		
		while(!q.isEmpty())
		{
			Node now = q.poll();
			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(nextY<0 || nextX<0 || Y<=nextY || X<=nextX || map[nextY][nextX] < height)
					return;
				if(height < map[nextY][nextX])
				{
					minHeight = Math.min(map[nextY][nextX], minHeight);
					continue;
				}
				if(visit[nextY][nextX])
					continue;
				
				visit[nextY][nextX] = true;
				Node next			= new Node(nextY, nextX);
				position.add(next);
				q.add(next);
			}
		}
		
		if(!outOfRange)
		{
			result += (minHeight - height) * position.size();
			for(Node p : position)
			{
				map[p.y][p.x] = minHeight;
				check[p.y][p.x]= true; 
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br		= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st		= new StringTokenizer(br.readLine());
		ArrayList<Node> list	= new ArrayList<>();
		Y		= Integer.parseInt(st.nextToken());
		X		= Integer.parseInt(st.nextToken());
		map		= new int[Y][X];
		check	= new boolean[Y][X];
		
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
			{
				map[y][x] = str.charAt(x) - '0';
				list.add(new Node(y, x, map[y][x]));
			}
		}
		
		Collections.sort(list,(a,b)-> a.h-b.h);
		
		for(Node node : list)
			if(!check[node.y][node.x])
				BFS(node);
			

		System.out.print(result);
	}
}