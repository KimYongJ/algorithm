// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

class Main
{
	static int limit, result;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean star[], visit[][], map[][];
	static ArrayList<Integer> starList;
	static ArrayDeque<int[]> q;

	public static boolean connection(int startIdex) {
		int y = startIdex/5;
		int x = startIdex%5;
		int cnt = 0;
		visit = new boolean[25][25];
		q = new ArrayDeque<>();
		visit[y][x] = true;
		q.add(new int[] {y,x});

		
		while(!q.isEmpty()) {
			int[] now = q.poll();

			if(map[now[0]][now[1]]) cnt ++;
			
			for(int[] xy : dxy) 
			{
				y = now[0] + xy[0];
				x = now[1] + xy[1];
				if(y>=0 && x>=0 && y<5 && x<5 && !visit[y][x] && map[y][x]) {
					visit[y][x] = true;
					q.add(new int[] {y,x});
				}
			}
		}
		return cnt == limit;
	}
	public static void Backtracking(int depth, int distSum, int oneIndex) // 순서 : 깊이, 원 별모양과 거리합 , 백트래킹으로 구한 1의 아무위치
	{
		if(result <= distSum)					// 최단 거리를 구해야 하므로 기존 구해진 result보다 앞으로 구할 distSum이 같거나 크면 스킵 
			return;
		if(depth == limit) 
		{
			if( connection(oneIndex) ) 			// 백트레킹으로 구한 별들이 연결되어있는지 확인
				result = distSum;
			return;
		}
		int y, x;
		for(int i=0; i<25; i++)
		{
			if(!star[i]) 
			{
				y 			= i/5;
				x 			= i%5;
				star[i] 	= true;
				map[y][x] 	= true;
				int nextDist = Math.abs(y - starList.get(depth)/5) + Math.abs(x - starList.get(depth)%5);
				Backtracking(depth+1, distSum + nextDist, i);
				map[y][x] 	= false;
				star[i] 	= false;
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		result	= Integer.MAX_VALUE;
		star 	= new boolean[25];				// 별의 위치를 완전탐색으로 담을 star 배열
		map		= new boolean[5][5];
		starList = new ArrayList<>();
		String str;
		char c;
		for(int y=0; y<5; y++) 
		{
			str = br.readLine();
			for(int x=0; x<5; x++) 
			{
				c = str.charAt(x);
				if(c=='*')
					starList.add(y*5 + x);	// 별의 인덱스를 1차원으로 변경 후 저장한다. 
			}
		}
		
		limit = starList.size();			// 별의 총 개수를 파악한다
		Backtracking(0,0,starList.get(0));
		
		System.out.println( result );
	}
}