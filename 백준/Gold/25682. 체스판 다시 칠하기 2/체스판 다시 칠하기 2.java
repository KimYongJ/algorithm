//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25682

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, K;
	static char board[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y		= Integer.parseInt(st.nextToken());
		X		= Integer.parseInt(st.nextToken());
		K		= Integer.parseInt(st.nextToken());
		board	= new char[Y+1][X+1];
		
		for(int y=1; y<=Y; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=X; x++)
				board[y][x] = str.charAt(x-1);
		}
		
		System.out.print(Math.min(find('B'), find('W')));
	}
	public static int find(char flag)
	{
		int psum[][] = new int[Y+1][X+1];

		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
			{
				if((y + x)%2 == 0)
				{
					if(flag != board[y][x])psum[y][x] = 1;
				}
				else
				{
					if(flag == board[y][x])psum[y][x] = 1;
				}
				psum[y][x] += psum[y-1][x] + psum[y][x-1] - psum[y-1][x-1];
			}

		int count = 1<<30;

		for(int y=1; y+K-1<=Y; y++)
			for(int x=1; x+K-1<=X; x++)
			{
				int y1 = y + K - 1;
				int x1 = x + K - 1;
				int value = psum[y1][x1] - psum[y1][x-1] - psum[y-1][x1] + psum[y-1][x-1];
				count = Math.min(count, value);
			}

		return count;
	}
}