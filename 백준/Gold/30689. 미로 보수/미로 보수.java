//https://www.acmicpc.net/problem/30689
//2초 1024MB
//3 3 // 세로, 가로 
//LLL // 미로의 방향이 먼저 주어짐
//DLU
//RUU
//3 1 5 // 각칸의 탈출 비용이 주어짐
//2 9 6
//8 7 1
//답 : 2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{-1,0},{0,1},{1,0},{0,-1}};
	static int min;
	static int Y, X;
	static char map[][];
	static int value[][];
	
	static int time;
	static int visitTime[][];
	static List<Node> cycle;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken()); // 1<=2,000
		X = Integer.parseInt(st.nextToken()); // 1<=2,000
		map = new char[Y][X];
		value = new int[Y][X];
		visitTime = new int[Y][X];
		cycle = new ArrayList<>();
		
		for(int y=0; y<Y; y++) // 방향 입력 받음
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
				map[y][x] = str.charAt(x);
		}
		
		for(int y=0; y<Y; y++) // 값 입력 받음
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++)
				value[y][x] = Integer.parseInt(st.nextToken());// 1<=500
		}
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if(visitTime[y][x] == 0)
					bfs(y, x, true);

		
		int sum = 0;
		
		visitTime = new int[Y][X];
		for(Node n : cycle)
			sum += bfs(n.y, n.x, false);
		
		System.out.print(sum);
	}
	static int bfs(int y, int x, boolean isSave) {
		int min = value[y][x];
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(y,x));
		visitTime[y][x] = ++time;
		
		while(!q.isEmpty())
		{
			Node now = q.poll();
			
			int idx = 0;
			switch(map[now.y][now.x])
			{
			case 'R' : idx = 1;break;
			case 'D' : idx = 2;break;
			case 'L' : idx = 3;break;
			}
			
			int nextY = now.y + dxy[idx][0];
			int nextX = now.x + dxy[idx][1];
			
			if(nextY < 0 || nextX < 0 || Y == nextY || X == nextX)
				continue;
			
			if(visitTime[nextY][nextX] == 0)
			{
				visitTime[nextY][nextX] = time;
				min = Math.min(min, value[nextY][nextX]);
				q.add(new Node(nextY, nextX));
			}
			else if(visitTime[nextY][nextX] == time)
			{
				if(isSave)
					cycle.add(new Node(nextY, nextX));
			}
		}
		
		return min;
	}
	static class Node{
		int y, x;
		Node(int y, int x){
			this.y=y;
			this.x=x;
		}
	}
}