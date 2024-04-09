import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	static final int max 	= 9;
	static int map[][] 		= new int[max][max];
	static int zeroCnt;
	static ArrayList<int[]> zerolist = new ArrayList<>();
	
	public static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<max; i++) 
		{
			for(int j=0; j<max; j++)
				sb.append(map[i][j]).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);	
	}
	public static boolean check(int base, int y, int x) {
		for(int i=0; i<max; i++)
			if(map[y][i] == base || map[i][x] == base) 
				return false;
		
		y = (y/3) * 3;
		x = (x/3) * 3;
		for(int i=y; i<y+3; i++)
			for(int j=x; j<x+3; j++)
				if(map[i][j] == base)
					return false;
		return true;
	}
	public static void Backtracking(int zero) {
		if(zero == zeroCnt)
		{
			print();
			System.exit(0);
		}
		int [] xy = zerolist.get(zero);
		for(int i=1; i<=max; i++) {
			if(check(i, xy[0], xy[1])) 
			{
				map[xy[0]][xy[1]] = i;
				Backtracking(zero+1);
				map[xy[0]][xy[1]] = 0;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int i=0; i<max; i++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<max; j++) 
			{
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) 
				{
					zeroCnt++;
					zerolist.add(new int[] {i,j});
				}
			}
		}
		
		Backtracking(0);
		
	}
}