//https://www.acmicpc.net/problem/10429
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int start[][] = {{0,0},{0,2},{1,1},{2,0},{2,2}};
	static final int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
	static int N; // 목표 숫자
	static int M; // 사용해야하는 숫자
	static char map[][];
	static int path[][];
	static boolean visit[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[3][3];
		path = new int[2 * M - 1][2];
		visit = new boolean[3][3];
		
		for(int y=0; y<3; y++)
		{
			String str = br.readLine();
			for(int x=0; x<3; x++)
				map[y][x] = str.charAt(x);
		}
		
		for(int s[] : start)
		{
			visit[s[0]][s[1]] = true;
			
			path[0][0] = s[0];
			path[0][1] = s[1];
			
			if(dfs(s[0], s[1], map[s[0]][s[1]] - '0', 1, '+', 1, true))
			{
				print();
				return;
			}
			
			visit[s[0]][s[1]] = false;
		}
		
		System.out.print(0);
	}
	static boolean dfs(int y, int x, int sum, int depth, char op, int useNumberCnt, boolean findOp) {
		if(useNumberCnt == M)
			return sum == N;
		
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			
			if(!validate(nextY, nextX))
				continue;
			
			visit[nextY][nextX] = true;
			path[depth][0] = nextY;
			path[depth][1] = nextX;
			
			int plus = 0;
			int nextSum = sum;
			boolean nextFindOp = findOp == false;
			
			if(!findOp)
			{
				nextSum = cal(sum, map[nextY][nextX], op);
				plus = 1;
			}
			
			if(dfs(nextY, nextX, nextSum, depth + 1, map[nextY][nextX], useNumberCnt + plus, nextFindOp))
				return true;
			
			visit[nextY][nextX] = false;
		}
		
		return false;
	}
	static int cal(int a, char b, char op) {
		b -= '0';
		return op == '+' ? a+b : a-b;
	}
	static boolean validate(int y, int x) {
		return 0<=y && 0<=x && y<3 && x<3 && !visit[y][x];
	}
	static void print() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(1).append('\n');
		
		for(int i=0; i<path.length; i++)
			sb.append(path[i][0]).append(' ')
				.append(path[i][1]).append('\n');
		
		System.out.print(sb);
	}
}
