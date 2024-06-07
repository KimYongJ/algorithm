// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static int[][]	rangeX = {{0,3,6},{1,4,7},{2,5,8}};
	static int[][]	rangeY = {{0,1,2},{3,4,5},{6,7,8}};
	static int[][]	map;
	static int[] 	rows, cols, square;
	
	public static int getIndex(int y, int x) {
		for(int rx : rangeX[x/3]) {
			for(int ry : rangeY[y/3]) {
				if(rx==ry)return rx;
			}
		}
		return 0;
	}
	public static boolean backtracking(int idx) {
		if(idx > 80)
			return true;
		
		int ny = idx / 9;
		int nx = idx % 9;
		
		if(map[ny][nx] != 0)
			return backtracking(idx + 1);
		
		
		int bit, ni;
		for(int i=1; i<=9; i++) {
			bit	= 1<<i;
			ni 	= getIndex(ny,nx);
			if(	(rows[ny] & bit) == 0 && (cols[nx] & bit) == 0 && (square[ni] & bit) == 0) 
			{
				rows[ny]	|= bit;
				cols[nx]	|= bit;
				square[ni]	|= bit;
				map[ny][nx] = i;
				if(backtracking(idx + 1)) return true;
				map[ny][nx] = 0;
				rows[ny] 	^= bit;
				cols[nx]  	^= bit;
				square[ni] 	^= bit;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map 	= new int [9][9];
		rows 	= new int [9];
		cols 	= new int [9];
		square	= new int [9];
		
		for(int y=0; y<9; y++) 
		{
			String str = br.readLine();
			for(int x=0; x<9; x++) 
			{
				map[y][x] = str.charAt(x)-'0';
				if(map[y][x] == 0) 
					continue;
				int bit = 1<<map[y][x];
				rows[y] |= bit;
				cols[x] |= bit; 
				square[getIndex(y,x)] |= bit;
			}
		}
		
		backtracking(0);		
		
		StringBuilder sb = new StringBuilder();
		for(int y=0; y<9; y++) 
		{
			for(int x=0; x<9; x++)
				sb.append(map[y][x]);
			sb.append('\n');
		}
		System.out.print(sb);
	}
}