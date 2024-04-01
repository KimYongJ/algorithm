// https://github.com/KimYongJ/algorithm

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

class Main{
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, result, cnt, idx, nextY, nextX, px[] ,py[];
	static char arr[][];
	static boolean visit[][], visit2[][];
	static ArrayDeque<Point> q;
	public static void BFS(int start) {
		visit2 = new boolean[5][5];
		cnt = 1;
		q.clear();
		visit2[py[start]][px[start]] = true;
		q.add(new Point(px[start], py[start]));
		while(!q.isEmpty()) {
			Point now = q.poll();
			for(int xy[] : dxy) {
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(nextY>=0 && nextX>=0 && nextY<5 && nextX<5 
						&& visit[nextY][nextX] && !visit2[nextY][nextX]) 
				{
					visit2[nextY][nextX] = true;
					cnt++;
					q.add(new Point(nextX, nextY));
				}
			}
		}
		if(cnt == 7)
			result ++ ;
	}
	public static void combination(int start, int depth, int ImdoCnt) {
		if(depth == 7) 
		{
			if(ImdoCnt < 4) // 임도연 파가 4명보다 작아야 한다. 
				BFS(start-1);
			return;
		}
		for(int i=start; i<25; i++) 
		{
			visit[py[i]][px[i]] = true;
			combination(i + 1, depth + 1, 
						ImdoCnt + (arr[py[i]][px[i]] == 'Y' ? 1 : 0));// 해당 위치가 임도연이면 1을, 아니면 0을 세팅
			visit[py[i]][px[i]] = false;
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		N 		= 5;
		px 		= new int[25];
		py 		= new int[25];
		arr 	= new char[5][5];
		visit	= new boolean[5][5];
		q		= new ArrayDeque<>();
		for(int y=0; y<N; y++) 
		{
			str = br.readLine();
			for(int x=0; x<N; x++) 
			{
				px[idx] 	= x;
				py[idx++] 	= y;
				arr[y][x] 	= str.charAt(x);
			}
		}
		
		combination(0,0,0); // 0~24범위 안에서 7개 고르기 
		
		System.out.println(result);
	}
}