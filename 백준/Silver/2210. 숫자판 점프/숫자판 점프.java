// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
	static int nextY, nextX, map[][], dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean visit[][];
	static HashSet<String> set;
	public static void BACK(int depth, int y , int x, String str) {
		if(depth == 5) 
		{
			set.add(str);
			return;
		}
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(map[nextY][nextX] != -1 && !visit[nextY][nextX]) 
				BACK(depth + 1, nextY, nextX, str + map[nextY][nextX]);
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[7][7];
		visit = new boolean[7][7];
		set = new HashSet<>();
		for(int i=0; i<7; i++)			// 패딩 넣기
			map[0][i] = map[i][0] = 
			map[i][6] = map[6][i] = -1;
		
		for(int y=1; y<=5; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=5; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}

		for(int y=1; y<=5; y++)
			for(int x=1; x<=5; x++) 
				BACK(0,y,x, map[y][x]+"");
		System.out.println(set.size());
	}
}