// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 		 cnt;
	static int 		 Y, X, MAX;
	static boolean 	 map[][];
	

	public static void backtracking(int idx) {
		if(idx == MAX) {
			cnt ++ ;
			return;
		}
		
		int y = idx / X;
		int x = idx % X;
		backtracking(idx + 1);
		
		if(check(y,x)) {
			map[y][x] = true;
			backtracking(idx+1);
			map[y][x] = false;
		}

	}
	public static boolean check(int y, int x) {
		if(y-1>=0 && x-1>=0) 
			return !(map[y-1][x] && map[y][x-1] && map[y-1][x-1]);
		return true;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y	= Integer.parseInt(st.nextToken());
		X 	= Integer.parseInt(st.nextToken());
		MAX = Y*X;
		map = new boolean[Y][X];

		backtracking(0);
		
		System.out.print(cnt);
	}
}