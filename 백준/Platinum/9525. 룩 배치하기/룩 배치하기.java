//https://www.acmicpc.net/problem/9525
//1초 128MB
//5 // 체스판의 크기 1<=100
//X....// .은 빈칸, X는 칸에 폰이 있음을 의미
//X....
//..X..
//.X...
//....X
//주어진 체스판에 록을 배치할 때, 최대 몇개를 배치할 수 있는가
//답 : 7

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
	
	static int N;
	static int yIdx, xIdx;
	static int ymap[][];
	static int xmap[][];
	static boolean isPawn[][];
	static List<Integer> adList[];
	// 이분 매칭시 사용
	static int time;
	static int match[];
	static int visitTime[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ymap = new int[N + 1][N + 1];
		xmap = new int[N + 1][N + 1];
		isPawn = new boolean[N + 1][N + 1];
		adList = new ArrayList[N * N + 1];
		
		for(int i=0; i<adList.length; i++)
			adList[i] = new ArrayList<>();
		
		for(int y=1; y<=N; y++)
		{
			String str = br.readLine();
			for(int x=1; x<=N; x++)
			{
				isPawn[y][x] = str.charAt(x-1) == 'X';
				if(!isPawn[y][x])
				{
					ymap[y][x] = ymap[y-1][x] == 0 ? ++yIdx : ymap[y-1][x];
					xmap[y][x] = xmap[y][x-1] == 0 ? ++xIdx : xmap[y][x-1];
					adList[ymap[y][x]].add(xmap[y][x]);
				}
			}
		}
		
		match = new int[xIdx + 1];
		visitTime = new int[xIdx + 1];
		
		int cnt = 0;
		for(int y=1; y<=yIdx; y++)
		{
			++time;
			if(dfs(y))
				++cnt;
		}
		System.out.print(cnt);
	}
	static boolean dfs(int y) {
		for(int x : adList[y])
		{
			if(visitTime[x] == time)
				continue;
			visitTime[x] = time;
			if(match[x] == 0 || dfs(match[x]))
			{
				match[x] = y;
				return true;
			}
		}
		return false;
	}
}