import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	static final int max 	= 9;
	static int map[][] 		= new int[max][max],
			   Y[] 			= new int[10],
		       X[] 			= new int[10],
			   square[] 	= new int[10],
			   zeroCnt;
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
	public static boolean check(int bit, int y, int x) 
	{	// 가로 세로 사각형에 있는지 체크
		return ((Y[y] | bit) != Y[y] && (X[x] | bit) != X[x]
				&& (square[((y/3)*3) + (x/3)] | bit) != square[((y/3)*3) + (x/3)]);
	}
	public static void Backtracking(int zero) {
		if(zero == zeroCnt)
		{
			print();
			System.exit(0);
		}
		int [] xy = zerolist.get(zero);
		int bit;
		for(int i=1; i<=max; i++) {
			bit = 1<<i;
			if(check(bit, xy[0], xy[1]))  						// i 가 세로, 가로 , 사각형에 있는지 체킹
			{
				Y[xy[0]] |= bit;								// 세로 비트마스킹
				X[xy[1]] |= bit;								// 가로 비트마스킹
				square[((xy[0]/3)*3) + (xy[1]/3)] |= bit; 		// 사각형 비트마스킹
				map[xy[0]][xy[1]] = i;
				Backtracking(zero+1);
				Y[xy[0]] ^= bit;								// 세로 마킹 해제
				X[xy[1]] ^= bit;								// 가로 마킹 해제
				square[((xy[0]/3)*3) + (xy[1]/3)] ^= bit; 		// 사각형 마킹 해제ㅔ
				map[xy[0]][xy[1]] = 0;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int bit;
		for(int i=0; i<max; i++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<max; j++) 
			{
				map[i][j] = Integer.parseInt(st.nextToken());
				bit = 1<<map[i][j];
				Y[i] |= bit;						// 행에 비트로 마킹
				X[j] |= bit;						// 열에 비트로 마킹
				square[((i/3)*3) + (j/3)] |= bit; 	// 사각형에 마킹
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