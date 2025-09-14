//https://www.acmicpc.net/problem/17143
//1초 512MB
//4 6 8 // Y,X(2<=100), 상어 수(0<=M<=Y*C)
//4 1 3 3 8 // 상어 위치 (Y,X), 이동 칸수(S), 이동 방향(D 1부터4까지 위 아래 오른쪽 왼쪽), 크기(Z)
//1 3 5 2 9
//2 4 8 4 1
//4 5 0 1 4
//3 3 1 2 7
//1 5 8 4 3
//3 6 2 1 2
//2 2 2 3 5
//답 : 22
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{},{-1, 0},{1, 0},{0, 1},{0, -1}};
	static PriorityQueue<Shark>[][] map, dummy;
	static int Y, X, M;
	static int sum;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());// Y(2<=100)
		X = Integer.parseInt(st.nextToken());// X(2<=100)
		M = Integer.parseInt(st.nextToken());// 상어 수(0<=M<=Y*C)
		map = new PriorityQueue[Y][X];
		dummy = new PriorityQueue[Y][X];
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
			{
				map[y][x] = new PriorityQueue<>();
				dummy[y][x] = new PriorityQueue<>();
			}
		}
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;// 상어 위치 (Y,X)
			int x = Integer.parseInt(st.nextToken()) - 1;// 상어 위치 (Y,X)
			int c= Integer.parseInt(st.nextToken());// 이동 칸수 0<=1,000
			int d = Integer.parseInt(st.nextToken());// 이동 방향( 1부터 4까지 위 아래 오른쪽 왼쪽)
			int z = Integer.parseInt(st.nextToken());// 크기 1<=10,000
			input(map, y, x, new Shark(c, d, z));
		}
		
		for(int x=0; x<X; x++)
		{
			sum += catchShark(map, x);
			moveShark();
		}
		
		System.out.print(sum);
	}
	static void moveShark() {
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
			{
				if(map[y][x].isEmpty())
					continue;
				
				move(map[y][x].poll(), y, x);
			}
		}
		
		PriorityQueue[][] d = map;
		map = dummy;
		dummy = d;
	}
	static void move(Shark o, int y, int x) {
		int cnt = o.cnt;
		int dir = o.dir;
		while(cnt-->0)
		{
			y += dxy[dir][0];
			x += dxy[dir][1];
			
			if(0<=y && 0<=x && y < Y && x < X)// 유효한 범위일 때
				continue;
			
			y -= dxy[dir][0];// 좌표 원복
			x -= dxy[dir][1];// 좌표 원복
			dir = toggle(dir);
			y += dxy[dir][0];// 좌표 증가
			x += dxy[dir][1];// 좌표 증가
		}
		
		o.dir = dir;
		
		input(dummy, y, x, o);
	}
	static int toggle(int idx) {
		switch(idx)
		{
		case 1 : return 2;
		case 2 : return 1;
		case 3 : return 4;
		case 4 : return 3;
		}
		return idx;
	}
	static int catchShark(PriorityQueue<Shark>[][] pq,int x) {
		for(int y=0; y<Y; y++)
			if(!pq[y][x].isEmpty())
				return pq[y][x].poll().size;
		
		return 0;
	}
	static void input(PriorityQueue<Shark>[][] pq, int y, int x, Shark o) {
		if(pq[y][x].isEmpty())
			pq[y][x].add(o);
		else if(pq[y][x].peek().size < o.size)
		{
			pq[y][x].poll();
			pq[y][x].add(o);
		}
	}
	static class Shark implements Comparable<Shark>{
		int cnt, dir, size;
		Shark(int c, int d, int s){
			cnt = c;
			dir = d;
			size = s;
		}
		@Override
		public int compareTo(Shark a) {
			return a.size - size;
		}
	}
}