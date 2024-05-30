// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static final int 	dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 			N;
	static int 			max;
	static char 		map[][];
	static ArrayList<int[]> list;
	
	public static boolean check() {
		for(int[] position : list) 
		{
			for(int xy[] : dxy) 
			{
				int y = position[0];
				int x = position[1];
				while(true) 
				{
					y += xy[0];
					x += xy[1];
					if(y>=0 && x>=0 && y<N && x<N) 
					{
						if(map[y][x] == 'S')
							return false;
						else if(map[y][x] != 'X')
							break;
					}
					else break;
				}
			}
		}
		return true;
	}
	public static boolean comb(int depth, int idx) {
		if(depth == 3)
			return check();
		
		int ny, nx;
		for(int i=idx; i<max; i++) 
		{
			ny = i/N;
			nx = i%N;
			if(map[ny][nx] == 'X') 
			{
				map[ny][nx] = '0';
				if(comb(depth + 1, i+1)) 
					return true;
				map[ny][nx] = 'X';
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N 		= Integer.parseInt(br.readLine());
		max		= N*N;
		map		= new char[N][N];
		list 	= new ArrayList<>();
		
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
			{
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == 'T') 
				{
					list.add(new int[] {i,j});
				}
			}
		}
		
		String result = "NO";
		if(comb(0,0)) 
		{
			result = "YES";
		}
		System.out.print(result);
	}
}