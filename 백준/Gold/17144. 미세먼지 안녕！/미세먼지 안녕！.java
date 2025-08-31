//https://www.acmicpc.net/problem/17144
//1초512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int [][]dxy = {{1,0},{0,1},{-1,0},{0,-1}};
	static int [][] base, inc;
	static int []sy;// 공기청정기의 y좌표
	static int Y, X, T;
	static List<int[]> path1;
	static List<int[]> path2;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		base = new int[Y][X];
		inc = new int[Y][X];
		sy = new int[2];
		path1 = new ArrayList<>();
		path2 = new ArrayList<>();
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
		
		pathSetting();
		
		while(T-->0)
		{
			diffusion();
			circulation(path1);
			circulation(path2);
		}
		
		int sum = 0;
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				sum += base[y][x];
		
		System.out.print(sum + 2); // 공기청정기는 -2의 값을 가지므로 +2로 보정
	}
	static void circulation(List<int[]> path) {
		
		for(int i=path.size()-2; i>=0; i--)
		{
			int[] a = path.get(i);
			int[] b = path.get(i + 1);
			base[b[0]][b[1]] = base[a[0]][a[1]];
		}
		
		int[] first = path.get(0);
		
		base[first[0]][first[1]] = 0;
	}
	static void pathSetting() {
		// 상단 세팅
		int y = sy[0];
		int x = 1;
		
		for(; x<X; x++)path1.add(new int[] {y,x});
		for(x = X-1, y = sy[0]-1; y>=0; y--)path1.add(new int[] {y,x});
		for(y = 0; x>=0; x--)path1.add(new int[] {y,x});
		for(x = 0; y<sy[0]; y++)path1.add(new int[] {y,x});
		
		// 하단 세팅
		y = sy[1];
		x = 1;
		for(; x<X; x++)path2.add(new int[] {y,x});
		for(x=X-1, y=sy[1]+1; y<Y; y++)path2.add(new int[] {y,x});
		for(y=Y-1; x>=0; x--) path2.add(new int[] {y,x});
		for(x=0,y=Y-2;y>sy[1]; y--)path2.add(new int[] {y,x});
		
	}
	static void diffusion() {
		for(int y=0; y<Y; y++)
			Arrays.fill(inc[y], 0);
		
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
			{
				inc[y][x] += base[y][x];
				
				if(base[y][x] <= 0)
					continue;
				
				int cnt = 0;
				int cal = base[y][x] / 5;
				
				for(int xy[] : dxy)
				{
					int ny = y + xy[0];
					int nx = x + xy[1];
					
					if(ny<0 || nx<0 || Y<=ny || X<=nx || 0 > base[ny][nx])
						continue;
					
					inc[ny][nx] += cal;
					++cnt;
				}
				inc[y][x] -= (cal) * cnt;
			}
		}
		
		int[][] tmp = inc;
		inc = base;
		base = tmp;
	}
}