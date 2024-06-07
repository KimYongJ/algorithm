// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{

	static int[][]	map;
	static int[] 	rows, cols, square;

	public static boolean backtracking(int y, int x) {
		if(y == 8 && x == 9)
			return true;
		
		if(x == 9) 
		{
			x = 0;
			y += 1;
		}
		
		if(map[y][x] != 0)
			return backtracking(y, x + 1);
		
		int ni = y/3 * 3 + x /3;
		int flag = rows[y] | cols[x] | square[ni];
		
		int bit;
		for(int i=1; i<=9; i++) 
		{
			bit	= 1<<i;
			if((flag & bit) == 0) 
			{
				rows[y]	|= bit;
				cols[x]	|= bit;
				square[ni]	|= bit;
				map[y][x] = i;
				if(backtracking(y, x+1)) 
					return true;
				map[y][x] = 0;
				rows[y] 	^= bit;
				cols[x]  	^= bit;
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
				square[y/3 *3 + x / 3] |= bit;
			}
		}
		
		backtracking(0,0);		
		
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