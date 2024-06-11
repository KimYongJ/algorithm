// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int	pinCnt;
	static int	minPin, minMove;
	static char map[][];
	public static void backtracking(int pin, int move) {
		if(pin == 1) 
		{
			if(minPin > 1) 
			{
				minPin = 1;
				minMove = move;
			}
			else if(1 == minPin && minMove > move) 
				minMove = move;
			return;
		}

		int x1, x2, y1, y2;
		for(int y=2; y<=6; y++) {
			for(int x=2; x<=10; x++) {
				if(map[y][x] != 'o')
					continue;
				
				for(int xy[] : dxy) {
					x1 = x + xy[0];
					y1 = y + xy[1];
					x2 = x + xy[0] + xy[0];
					y2 = y + xy[1] + xy[1];
					if(map[y1][x1] == 'o' && map[y2][x2] == '.') {
						map[y][x] = '.';
						map[y1][x1] = '.';
						map[y2][x2] = 'o';
						backtracking(pin - 1, move + 1);
						map[y][x] = 'o';
						map[y1][x1] = 'o';
						map[y2][x2] = '.';
					}
				}
			}
		}
		
		if(minPin > pin) 
		{
			minPin  = pin;
			minMove = move;
		}
		else if(minPin == pin && minMove > move)
			minMove = move;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			minPin	= Integer.MAX_VALUE;
			minMove = Integer.MAX_VALUE;
			pinCnt	= 0;
			map		= new char[9][13];
			for(int y=2; y<=6; y++) 
			{
				String str = br.readLine();
				for(int x=2; x<=10; x++) 
				{
					map[y][x] = str.charAt(x-2);
					if(map[y][x] == 'o') 
					{
						pinCnt++;
					}
				}
			}
			backtracking(pinCnt, 0);
			
			sb.append(minPin).append(' ')
				.append(minMove).append('\n');
			if(T != 0)
				br.readLine();
		}
		System.out.print(sb);
	}
}