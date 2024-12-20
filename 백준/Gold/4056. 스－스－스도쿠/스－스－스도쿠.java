//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4056
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Pos{int y, x;Pos(int y,int x){this.y=y;this.x=x;}}

class Main{
	
	static final int ANS = (1<<10)-2;
	static int[][]	map;
	static Pos[]	pos;
	static int[]	row, col, squre;
	static boolean	isPossible;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			isPossible	= false;
			map			= new int[9][9];
			row			= new int[9];
			col			= new int[9];
			squre		= new int[9];
			pos			= new Pos[5];
			
			for(int y=0, i=0; y<9; y++)
			{
				String str = br.readLine();
				for(int x=0; x<9; x++)
				{
					int num = str.charAt(x)-'0';
					map[y][x] = num;
					if(map[y][x] == 0)
						pos[i++] = new Pos(y,x);
					else
					{
						int bit = 1<<num;
						row[y] |= bit;
						col[x] |= bit;
						squre[(y/3)*3 + (x/3)] |= bit;
					}
				}
			}
			
			back(0);
			
			if(isPossible)
			{
				for(int y=0; y<9; y++)
				{
					for(int x=0; x<9; x++)
						sb.append(map[y][x]);
					sb.append('\n');
				}
			}
			else
				sb.append("Could not complete this grid.").append('\n');
			sb.append('\n');
		}
		System.out.print(sb);
	}
	public static void back(int depth) {
		if(depth == 5)
		{
			isPossible = validate();
			return;
		}
		for(int i=1; i<=9; i++)
		{
			int y = pos[depth].y;
			int x = pos[depth].x;
			int bit = 1<<i;
			if((row[y] & bit) == 0 && (col[x] & bit) == 0 && (squre[(y/3)*3 + (x/3)] & bit) == 0) {
				map[y][x] = i;
				row[y] |= bit;
				col[x] |= bit;
				squre[(y/3)*3 + (x/3)] |= bit;
				
				back(depth + 1);
				
				if(isPossible)
					return;
				
				map[y][x] = 0;
				row[y] ^= bit;
				col[x] ^= bit;
				squre[(y/3)*3 + (x/3)] ^= bit;
			}
		}
	}
	public static boolean validate() {
		for(int x : col)  if(x != ANS) return false;
		for(int x : row)  if(x != ANS) return false;
		for(int x : squre)if(x != ANS) return false;
		return true;
	}
}