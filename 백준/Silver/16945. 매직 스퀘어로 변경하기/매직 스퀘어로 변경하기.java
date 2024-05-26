// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int result = Integer.MAX_VALUE;
	static int map[][];
	static boolean 	visit[];
	
	public static boolean check() {
		int base = map[0][0] + map[1][1] + map[2][2];
		if(base == map[2][0] + map[1][1] + map[0][2]) 
		{
			int sum1, sum2;
			for(int i=0; i<3; i++) 
			{
				sum1 = sum2 = 0;
				for(int j=0; j<3; j++) 
				{
					sum1 += map[i][j];
					sum2 += map[j][i];
				}
				if(base != sum1 || base != sum2)
					return false;
			}
			return true;
		}
		return false;
	}
	public static void DFS(int y, int x, int sum) {
		if(x == 3) {
			DFS(y+1,0, sum);
			return;
		}
		if(y==3) {
			if(check()) 
				result = Math.min(result, sum);
			return;
		}
		int tmp = map[y][x];
		for(int i=1; i<10; i++) {
			if(!visit[i]) {
				visit[i] = true;
				map[y][x] = i;
				DFS(y, x+1, sum + Math.abs(i-tmp));
				map[y][x] = tmp;
				visit[i] = false;
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map 	= new int[3][3];
		visit	= new boolean[10];
		
		for(int i=0; i<3; i++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		
		DFS(0,0,0);
		
		System.out.print(result);
	}
}