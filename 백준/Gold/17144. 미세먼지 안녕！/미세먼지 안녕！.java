//https://www.acmicpc.net/problem/17144
//1초512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int [][]dxy = {{1,0},{0,1},{-1,0},{0,-1}};
	static int [][] base, inc;
	static int []sy;// 공기청정기의 y좌표
	static int Y, X, T;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		base = new int[Y][X];
		inc = new int[Y][X];
		sy = new int[2];
		
		for(int y=0, idx = 0; y<Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++)
			{
				base[y][x] = Integer.parseInt(st.nextToken());
				
				if(base[y][x] == -1)
					sy[idx++] = y;
			}
		}
		
		while(T-->0)
		{
			diffusion();
			purify();
		}
		
		int sum = 0;
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				sum += base[y][x];
		
		System.out.print(sum + 2); // 공기청정기는 -2의 값을 가지므로 +2로 보정
	}
	static void purify() {
		// 상단 세팅
		int y = sy[0] - 2;
		int x = 0;
		
		for(; y>=0; y--) base[y+1][x] = base[y][x];
		for(y=0, x=1; x<X; x++) base[y][x-1] = base[y][x];
		for(x=X-1,y=1;y<=sy[0];y++) base[y-1][x] = base[y][x];
		for(x=X-2,y=sy[0]; x>=1; x--) base[y][x+1] = base[y][x];
		base[sy[0]][1] = 0;
		
		y = sy[1] + 2;
		x = 0;
		for(; y < Y; y++) base[y-1][x] = base[y][x];
		for(y=Y-1,x=1; x<X; x++) base[y][x - 1] = base[y][x];
		for(x=X-1, y=Y-2; sy[1]<=y; y--) base[y + 1][x] = base[y][x];
		for(y=sy[1],x=X-2;1<=x; x--)base[y][x + 1] = base[y][x];
		base[sy[1]][1] = 0;
	}
	static void diffusion() {
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
			{
				if(base[y][x] <= 0)
					continue;
				
				int cnt = 0;
				
				for(int xy[] : dxy)
				{
					int ny = y + xy[0];
					int nx = x + xy[1];
					
					if(ny<0 || nx<0 || Y<=ny || X<=nx || 0 > base[ny][nx])
						continue;
					
					inc[ny][nx] += base[y][x] / 5;
					++cnt;
				}
				base[y][x] -= (base[y][x] / 5) * cnt;
			}
		}
		
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
			{
				base[y][x] += inc[y][x];
				inc[y][x] = 0;
			}
		}
	}
}