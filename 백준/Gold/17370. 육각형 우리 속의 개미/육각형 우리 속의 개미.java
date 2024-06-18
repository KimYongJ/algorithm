// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main{
	
	static int dxy[][][] = {
								{{-1,0},{1,-1},{1,1}},
								{{1,0},{-1,1},{-1,-1}}
							};
	static int cnt;
	static int N;
	static boolean visit[][];
	public static void backtracking(int depth, int flag, int y, int x, int before) {
		if(visit[y][x] || depth == 0) 
		{
			if(depth == 0 && visit[y][x]) cnt ++;
			return;
		}
		
		visit[y][x] = true;
		int nextY, nextX, nextFlag = (flag+1)%2;
		for(int i=0; i<3; i++) 
		{
			if(i == before) 
				continue;
			
			nextY = y + dxy[flag][i][0];
			nextX = x + dxy[flag][i][1];
			backtracking(depth - 1, nextFlag, nextY, nextX, i);		
			
		}
		visit[y][x] = false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visit = new boolean[51][51];
		backtracking(N+1, 0, 25, 25, -1);
		System.out.print(cnt / 3);
	}
}