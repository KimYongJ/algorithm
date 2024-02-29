// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, nextY, nextX, flag, map[][];
	static HashMap<Integer, Integer> hm;
	static HashSet<Integer> set;
	
	// DFS를 돌면서 0인 구간의 갯수를 세고 반환한다.
	public static int DFS(int flag, int y, int x) 
	{
		int cnt = 1;
		map[y][x] = flag;
		if(map[y+1][x]==0) cnt += DFS(flag,y+1,x);
		if(map[y-1][x]==0) cnt += DFS(flag,y-1,x);
		if(map[y][x+1]==0) cnt += DFS(flag,y,x+1);
		if(map[y][x-1]==0) cnt += DFS(flag,y,x-1);
		return cnt;
	}
	public static void main(String[] args)throws Exception
	{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		StringBuilder sb 	= new StringBuilder();
		flag 				= 1;
		Y 					= Integer.parseInt(st.nextToken());
		X 					= Integer.parseInt(st.nextToken());
		map 				= new int[Y+2][X+2];
		hm 					= new HashMap<>();
		
		for(int y=0; y<Y+2; y++) // 빠른 연산을 위한 패딩
			map[y][0] = map[y][X+1] = -1;
		for(int x=0; x<X+2; x++) // 빠른 연산을 위한 패딩
			map[0][x] = map[Y+1][x] = -1;
		
		for(int y=1; y<=Y; y++) 
		{
			String str = br.readLine();
			for(int x=1; x<=X; x++)
				map[y][x] = str.charAt(x-1) - '0';
		}
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x] == 0)
					hm.put(++flag , DFS(flag,y,x));

		for(int y=1; y<=Y; y++) 
		{
			for(int x=1; x<=X; x++) 
			{
				int sum = 0;
				if(map[y][x] == 1) 
				{
					set = new HashSet<Integer>();
					sum = 1;
					if(map[y+1][x]>=2) set.add(map[y+1][x]);
					if(map[y-1][x]>=2) set.add(map[y-1][x]);
					if(map[y][x+1]>=2) set.add(map[y][x+1]);
					if(map[y][x-1]>=2) set.add(map[y][x-1]);
					for(int i : set)
						sum += hm.get(i);
				}
				sb.append(sum%10);
			}
			sb.append('\n');
		}
		System.out.println(sb);		
	}
}