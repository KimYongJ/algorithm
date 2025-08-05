//https://www.acmicpc.net/problem/5850
//1초 128MB
//8 // 건초더미 개수 1<=10,000
//5 3 // 건초더미 위치 X,Y 좌표, (1,1)은 왼쪽 아래 모서리, 100,100은 오른쪽 위 모서리
//5 4
//8 4
//5 5
//6 3
//7 3
//7 4
//6 5
//구멍을 제외한 건초더미의 둘레 : 14

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static final int LEN = 102;
	static boolean[][] blank, rock;
	static int cnt;
	static int N;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		rock = new boolean[LEN][LEN];
		blank = new boolean[LEN][LEN];
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			rock[y][x] = true;
		}
		
		blank[0][0] = true;
		
		dfs(0, 0);
		
		System.out.print(cnt);
	}
	static void dfs(int y, int x) {
		for(int xy[] : dxy)
		{
			int nextY = xy[0] + y;
			int nextX = xy[1] + x;
			
			if(nextY < 0 || nextX < 0 || LEN <= nextX || LEN <= nextY || blank[nextY][nextX])
				continue;
			
			if(rock[nextY][nextX])
			{
				++cnt;
				continue;
			}
			
			blank[nextY][nextX] = true;
			dfs(nextY, nextX);
		}
	}
	static class Node{
		int y, x;
		Node(int y, int x){
			this.y=y;
			this.x=x;
		}
	}
}