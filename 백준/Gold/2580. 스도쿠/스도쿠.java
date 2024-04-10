// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;

class Main{
	static final int max 	= 9;
	static int map[][] 		= new int[max][max],
			   Y[] 			= new int[10],
		       X[] 			= new int[10],
			   square[] 	= new int[10],
			   zeroCnt;
	static ArrayList<int[]> zerolist = new ArrayList<>();
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
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
	public static boolean check(int bit, int y, int x, int s) 
	{	// 가로 세로 사각형에 있는지 체크
		return (  (y | bit) != y && (x | bit) != x && (s | bit) != s  );
	}
	public static void Backtracking(int zero) {
		if(zero == zeroCnt)
		{
			print();
			System.exit(0);
		}
		int [] xy = zerolist.get(zero);
		int bit, secY, secX, secSq;
		secY 	= xy[0];
		secX 	= xy[1];
		secSq 	= ((xy[0]/3)*3) + (xy[1]/3);
		for(int i=1; i<=max; i++) {
			bit = 1<<i;
			if(check(bit, Y[secY], X[secX], square[secSq])) // i 가 세로, 가로 , 사각형에 있는지 체킹
			{
				Y[secY] 		|= bit;	// 세로 비트마스킹
				X[secX] 		|= bit; // 가로 비트마스킹
				square[secSq] 	|= bit; // 사각형 비트마스킹
				map[secY][secX] = i;
				Backtracking(zero+1);
				Y[secY] 		^= bit;	// 세로 마킹 해제
				X[secX] 		^= bit;	// 가로 마킹 해제
				square[secSq] 	^= bit; // 사각형 마킹 해제
				map[secY][secX] = 0;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		int bit;
		for(int i=0; i<max; i++) 
		{
			for(int j=0; j<max; j++) 
			{
				map[i][j] 	= read();
				bit 		= 1<<map[i][j];
				Y[i] 		|= bit;					// 행에 비트로 마킹
				X[j] 		|= bit;					// 열에 비트로 마킹
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