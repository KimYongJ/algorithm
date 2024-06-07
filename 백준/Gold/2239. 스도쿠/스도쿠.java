// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{

	static int[][]	map;
	static int[] 	rows, cols, square;

	public static boolean print() {
		StringBuilder sb = new StringBuilder();
		for(int y=0; y<9; y++) 
		{
			for(int x=0; x<9; x++)
				sb.append(map[y][x]);
			sb.append('\n');
		}
		System.out.print(sb);
		return true;
	}
	
	public static boolean backtracking(int y, int x) {
		if(y == 8 && x == 9)							// 마지막 좌표 도달시 출력 후 종료
			return print();
		
		
		if(x == 9) 										// 유효범위로 좌표 세팅
		{
			x = 0;
			y += 1;
		}
		
		if(map[y][x] != 0)								// 초기 입력된 장소는 탐색 스킵
			return backtracking(y, x + 1);
		
		int bit,
			ni		= y/3 * 3 + x /3,					// square에서 쓸 좌표 세팅
			flag	= rows[y] | cols[x] | square[ni];	// 가로,세로, 사각형에 대해 한번에 탐색하도록 flag 생성
		for(int i=1; i<=9; i++) 
		{
			bit	= 1<<i;
			if((flag & bit) == 0) 						// 유효성 검증 유효하면 이하 탐색 | 연산으로 bit에 불을키고, ^ 연산으로 비트에 불을 끈다.
			{
				rows[y]		|= bit;
				cols[x]		|= bit;
				square[ni]	|= bit;
				map[y][x] 	= i;
				if(backtracking(y, x+1)) 
					return true;
				map[y][x] 	= 0;
				rows[y] 	^= bit;
				cols[x]  	^= bit;
				square[ni] 	^= bit;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map 	= new int [9][9];	// 결과를 담을 배열
		rows 	= new int [9];		// 가로 행을 담을 배열
		cols 	= new int [9];		// 세로 행을 담을 배열
		square	= new int [9];		// 각 사각형에 대해 담을 배열
		
		for(int y=0; y<9; y++) 
		{
			String str = br.readLine();
			for(int x=0; x<9; x++) 
				if((map[y][x] = str.charAt(x)-'0') != 0)
				{
					int bit = 1<<map[y][x];
					rows[y] |= bit;
					cols[x] |= bit; 
					square[y/3 *3 + x / 3] |= bit; // 사각형의 index를 y와 x를 통해 구하는 공식
				}
		}
		
		backtracking(0,0);	 // 백트레킹 시작	
	}
}