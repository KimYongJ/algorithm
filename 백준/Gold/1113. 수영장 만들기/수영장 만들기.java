//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1113
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	static int result, minHeight;
	static int Y, X, map[][];
	static boolean visit[][], check[][];
	static ArrayList<Node> position;
	
	public static boolean DFS(int y, int x, int h) {
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(map[nextY][nextX] < h)
				return false;
			if(h < map[nextY][nextX])
			{
				minHeight = Math.min(map[nextY][nextX], minHeight);
				continue;
			}
			if(visit[nextY][nextX])
				continue;
			
			position.add(new Node(nextY, nextX));
			visit[nextY][nextX] = true;
			if(!DFS(nextY, nextX, h))
				return false;
		}
		
		return true;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br		= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st		= new StringTokenizer(br.readLine());
		ArrayList<Node> list	= new ArrayList<>();
		Y		= Integer.parseInt(st.nextToken());
		X		= Integer.parseInt(st.nextToken());
		map		= new int[Y+2][X+2];
		check	= new boolean[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=X; x++)
			{
				map[y][x] = str.charAt(x-1) - '0';
				list.add(new Node(y, x, map[y][x]));
			}
		}
		
		Collections.sort(list,(a,b)-> a.h-b.h);
		
		for(Node node : list)
			if(!check[node.y][node.x])
			{
				visit		= new boolean[Y+2][X+2];
				position	= new ArrayList<>();
				minHeight	= 10;
				visit[node.y][node.x]= true; 
				position.add(node);
				if( DFS(node.y, node.x, node.h) )
				{
					result += (minHeight - node.h) * position.size();
					for(Node p : position)
						map[p.y][p.x] = minHeight;
				}
				for(Node p : position)
					check[p.y][p.x]= true;
			}

		System.out.print(result);
	}
}