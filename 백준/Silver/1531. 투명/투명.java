//https://www.acmicpc.net/problem/1531
//2초 128MB
//3 1 // 종이개수(0<=50), M개이하면 
//21 21 80 80 // 왼쪽 아래 모서리 좌표 X, Y, 오른쪽 위 모서리 좌표 X, Y(1<=100)
//41 41 60 60
//71 71 90 90
//보이지않는 그림의 개수 : 500
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int map[][] = new int[102][102];
		
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			int lx = Integer.parseInt(st.nextToken());
			int ry = 101 - Integer.parseInt(st.nextToken());
			int rx = Integer.parseInt(st.nextToken());
			int ly = 101 - Integer.parseInt(st.nextToken());
			
			map[ly][lx] += 1;
			map[ry + 1][rx + 1] += 1;
			map[ly][rx + 1] -= 1;
			map[ry + 1][lx] -= 1;
		}
		
		int cnt = 0;
		
		for(int y=1; y<=100; y++)
		{
			for(int x=1; x<=100; x++)
			{
				map[y][x] += map[y][x-1] + map[y-1][x] - map[y-1][x-1];
				if(map[y][x] > M)
					++cnt;
			}
		}
		
		System.out.print(cnt);
	}
}