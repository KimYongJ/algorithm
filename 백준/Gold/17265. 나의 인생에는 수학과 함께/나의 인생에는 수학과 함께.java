// https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17265

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int 	dxy[][] = {{1,0},{0,1}};
	static int 			N, MAX, MIN, nextY, nextX;
	static char 		map[][];
	
	public static int cal(int num, int sum, char operator) {
		switch(operator) 
		{
			case '*': return sum * num;
			case '+': return sum + num;
			case '-': return sum - num;
		}
		return num;
	}
	public static void DFS(int y, int x, int sum, char operator,boolean flag) {
		if(flag)
		{
			sum = cal(map[y][x] - '0', sum, operator);
			if(y==N-1 && x==N-1) 
			{
				MAX = Math.max(MAX, sum);
				MIN = Math.min(MIN, sum);
				return;
			}
		}
		for(int xy[] : dxy)
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(nextY<N && nextX<N)
				DFS(nextY, nextX, sum, map[y][x] ,!flag);
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 	= Integer.parseInt(br.readLine());
		map = new char[N][N];
		MAX = Integer.MIN_VALUE;
		MIN = Integer.MAX_VALUE;
		
		for(int y=0; y<N; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
				map[y][x] = st.nextToken().charAt(0);
		}
		
		DFS(0,0,0,'!',true);
		
		System.out.print(
					new StringBuilder().append(MAX)
					.append(' ').append(MIN).toString()
				);
	}
}