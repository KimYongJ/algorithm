//https://www.acmicpc.net/problem/16726
//3 4// 행, 열(1<=50)
//.X..// X는 기둥, .은 바닥
//...X
//...X
//답 : 5

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	
	static int totalBlank;
	static int Y, X;
	static int idx[][];
	static boolean isPos[][];

	// 이분 매칭시 사용
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());// 행(1<=50)
		X = Integer.parseInt(st.nextToken());// 열(1<=50)
		idx = new int[Y + 2][X + 2];
		isPos = new boolean[Y + 2][X + 2];

		boolean flag;
		int i = 0;
		int j = 0;
		for(int y=1; y<=Y; y++)
		{
			flag = y % 2 == 1;// 타일을 체스판 처럼 구분하기 위한 값
			String str = br.readLine();
			for(int x=1; x<=X; x++,flag = !flag)
			{
				isPos[y][x] = str.charAt(x - 1) == '.';
				
				if(!isPos[y][x])
					continue;
				
				++totalBlank;
				
				idx[y][x] = flag ? ++i : ++j;
			}
		}
		
		match = new int[j + 1];
		visitTime = new int[j + 1];
		adList = new ArrayList[i + 1];
		
		for(int s=0; s<=i; s++)
			adList[s] = new ArrayList<>();
		
		for(int y=1; y<=Y; y++)
		{
			int x = y % 2 == 1 ? 1 : 2;
			for(; x<=X; x+=2)
			{
				if(!isPos[y][x])
					continue;
				
				for(int xy[] : dxy)
				{
					int nextY = y + xy[0];
					int nextX = x + xy[1];
					if(isPos[nextY][nextX])
						adList[idx[y][x]].add(idx[nextY][nextX]);
				}
			}
		}
		
		int cnt = 0;
		
		for(int L=1; L<=i; L++)
		{
			++time;
			if(dfs(L))
				++cnt;
		}
		System.out.print(totalBlank - cnt);
	}
	static boolean dfs(int L)
	{
		for(int R : adList[L])
		{
			if(visitTime[R] == time)
				continue;
			
			visitTime[R] = time;
			
			if(match[R] == 0 || dfs(match[R]))
			{
				match[R] = L;
				return true;
			}
		}
		return false;
	}
}