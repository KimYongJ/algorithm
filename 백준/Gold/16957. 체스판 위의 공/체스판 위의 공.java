//https://www.acmicpc.net/problem/16957
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static int dxy[][] = {{1,0},{1,-1},{1,1},{0,-1},{0,1},{-1,0},{-1,-1},{-1,1}};
	static int Y, X;
	static int number[][];
	static int cnt[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());// 세로길이 1<=500
		X = Integer.parseInt(st.nextToken());// 가로길이 1<=500
		number = new int[Y][X];
		cnt = new int[Y][X];
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		for(int y=0; y<Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++)
			{
				number[y][x] = Integer.parseInt(st.nextToken());
				cnt[y][x] = 1;
				
				pq.add(new Point(y, x, number[y][x]));
			}
		}
		
		while(!pq.isEmpty())
			setMin(pq.poll());
		
		StringBuilder sb = new StringBuilder();
		
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
				sb.append(cnt[y][x]).append(' ');
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
	static void setMin(Point now) {
		int min = number[now.y][now.x];
		int ny = -1;
		int nx = -1;
		for(int xy[] : dxy)
		{
			int nextY = now.y + xy[0];
			int nextX = now.x + xy[1];
			if(0<=nextY && 0<=nextX && nextY < Y && nextX < X && number[nextY][nextX] < min)
			{
				min = number[nextY][nextX];
				ny = nextY;
				nx = nextX;
			}
		}
		if(ny != -1) {
			cnt[ny][nx] += cnt[now.y][now.x];
			cnt[now.y][now.x] = 0; 
		}
	}
	static class Point implements Comparable<Point>{
		int y, x, num;
		Point(int y, int x, int num){
			this.y=y;
			this.x=x;
			this.num=num;
		}
		@Override
		public int compareTo(Point o) {
			return o.num - num;
		}
	}
}