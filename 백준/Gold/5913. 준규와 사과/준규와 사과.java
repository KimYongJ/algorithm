// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int		result;
	static int		dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean	map[][];
	public static void backtracking(int tree, int y1, int x1) 
	{
		if(y1==5 && x1== 5) {
			if(tree == 0)
				result++;
			return;
		}
		
		int ny1, nx1;
		for(int xy[] : dxy)
		{
			ny1 = xy[0] + y1;
			nx1 = xy[1] + x1;
			if(!map[ny1][nx1]) 
			{
				map[ny1][nx1] = true;
				backtracking(tree - 1, ny1, nx1);
				map[ny1][nx1] = false;
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new boolean[7][7];	// 맵 생성 및 마킹
		map[1][1] = true;
		for(int i=0; i<7; i++) 
		{
			map[i][0] = true;
			map[0][i] = true;
			map[6][i] = true;
			map[i][6] = true;
		}
		
		
		int a,b,N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			map[a][b] = true;
		}
		
		backtracking(24 - N, 1,1);
		
		System.out.print(result);
	}
}