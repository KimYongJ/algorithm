// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 		result = Integer.MIN_VALUE;
	static int 		Y, X, K;
	static int 		map[][];
	static boolean 	visit[][];
	
	public static void backtracking(int y, int x, int k, int sum) {
		if(K == k) {
			if(result < sum)
				result = sum;
			return;
		}
		for(int i=y; i<=Y; i++) {
			for(int j=1; j<=X; j++) {
				if(!visit[i][j] && !visit[i-1][j] && !visit[i+1][j]&& !visit[i][j-1]&& !visit[i][j+1]) {
					visit[i][j] = true;
					backtracking(i,j, k+1, sum + map[i][j]);
					visit[i][j] = false;
				}
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[Y+2][X+2];
		visit = new boolean[Y+2][X+2];
		
		for(int y=1; y<=Y;y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		backtracking(1,1,0,0);
		
		System.out.print(result);
	}
}