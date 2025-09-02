//https://www.acmicpc.net/problem/15685
//1초 / 512MB
//4 // 드래곤 커브의 개수(1<=20)
//3 3 0 1 // 드래곤 커브의 정보 x(시작점0<=100), y(시작점0<=100), d(시작 방향0<=3), g(세대0<=10)
//4 2 1 3
//4 2 2 1
//2 7 3 4
//네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형 개수 : 11
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
class Main{
	
	static final int len = 100;
	static List<Point> list;
	static boolean[][] map;
	static int sy, sx, ey, ex;
	static int d, g;
	static int N;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());// 드래곤 커브의 개수(1<=20)
		list = new ArrayList<>();
		map = new boolean[len + 1][len + 1];
		
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			
			sx = Integer.parseInt(st.nextToken());// 드래곤 커브의 정보 x(시작점0<=100)
			sy = Integer.parseInt(st.nextToken());// y(시작점0<=100)
			d = Integer.parseInt(st.nextToken());// d(시작 방향0<=3)
			g = Integer.parseInt(st.nextToken());// g(세대0<=10)
			
			// 0세대 : 시작점과 방향으로 끝점을 구함
			ex = d == 0 ? sx + 1 : d == 2 ? sx - 1 : sx;
			ey = d == 1 ? sy - 1 : d == 3 ? sy + 1 : sy;
			
			list.clear();
			list.add(new Point(sy, sx));
			list.add(new Point(ey, ex));
			
			while(g-->0)
				rotateAppend();// 드래곤 커브를 list에 추가
			
			// 보드에 마킹
			for(Point n : list)
				map[n.y][n.x] = true;
		}
		System.out.print(print());
	}
	static void rotateAppend() {
		
		int size = list.size(); // list 사이즈 만큼 순회
		
		for(int i=0; i<size; i++)
		{
			Point now = list.get(i);// 현재를 통해 다음(next)을 구함
			
			if(now.y == ey && now.x == ex)
				continue;
			
			Point next = getNext(now.y, now.x, ey, ex); // 다음을 공식에 따라 구함
			list.add(next);
		}
		
		Point nextEnd = list.get(size);// size는 항상 첫번째 점의 90도를 회전한 것, 첫번째 점의 회전의 회전.. 이런식으로 나아감
		ey = nextEnd.y;
		ex = nextEnd.x;
	}
	static Point getNext(int ny, int nx, int ey, int ex) {
		/*
		공식
		(ny, nx) - (ey,ex) = (dy, dx)
		gy = ey + dx
		gx = ex - dy
		*/
		int dy = ny - ey;
		int dx = nx - ex;
		return new Point(ey + dx, ex - dy);
	}
	static int print() {
		int cnt = 0;
		
		for(int y=0; y<len; y++)
			for(int x=0; x<len; x++)
				if(map[y][x] && map[y+1][x] && map[y][x+1] && map[y+1][x+1])
					++cnt;
		
		return cnt;
	}
	static class Point{
		int y, x;
		Point(int y, int x){
			this.y=y;
			this.x=x;
		}
	}
}