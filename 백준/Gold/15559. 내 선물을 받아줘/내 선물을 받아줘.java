// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{new Main().solution();}
	
	
	char map[][];
	int area[][];
	int Y, X, flag, result;
	public void DFS(int flag, int y, int x) 
	{
		if(area[y][x] > 0) 
		{
			if(area[y][x] == flag) 		// 사이클이 발생한 공간이 자기 자신인 경우
				result++;
			return;
		}
		area[y][x] = flag;
		switch(map[y][x]) 
		{
			case 'N': DFS(flag,y-1,x);break;
			case 'S': DFS(flag,y+1,x);break;
			case 'W': DFS(flag,y,x-1);break;
			case 'E': DFS(flag,y,x+1);
		}
	}
	public void solution()throws Exception
	{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 					= Integer.parseInt(st.nextToken());
		X 					= Integer.parseInt(st.nextToken());
		flag 				= 0;
		area 				= new int[Y][X];
		map 				= new char[Y][X];
		for(int y=0; y<Y; y++)
			map[y] = br.readLine().toCharArray();
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if(area[y][x] == 0) 
					DFS(++flag,y,x);

		System.out.println(result);
	}
}