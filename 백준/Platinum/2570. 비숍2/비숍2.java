//https://www.acmicpc.net/problem/2570
//1초 128MB
//5// 체스판의 크기(1<=100)
//1// 장애물 위치 개수
//3 3// 장애물 좌표 y, x
//답 : 10
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{1,-1},{1,1}};
	static int idxL, idxR;
	static int leftDown[][];
	static int rightDown[][];
	static int N, B;
	static boolean isBlock[][];
	static List<Integer> adList[];
	// 이분 매칭시 사용
	static int time;
	static int match[];
	static int visitTime[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		B = Integer.parseInt(br.readLine());
		adList = new ArrayList[N * N + 1];
		match = new int[adList.length];
		visitTime = new int[adList.length];
		isBlock = new boolean[N + 2][N + 2];
		leftDown = new int[N + 2][N + 2];
		rightDown = new int[N + 2][N + 2];
		
		for(int i=0; i<adList.length; i++)
		{
			adList[i] = new ArrayList<>();
			match[i] = -1;
		}
		while(B-- >0)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			isBlock[y][x] = true;
		}
		
		for(int y=1; y<=N; y++)
		{
			for(int x=1; x<=N; x++)
			{
				if(isBlock[y][x])
					continue;
				
				if(leftDown[y][x] == 0)
				{
					leftDown[y][x] = ++idxL;
					
					int nextY = y + dxy[0][0];
					int nextX = x + dxy[0][1];
					
					while(validate(nextY, nextX))
					{
						leftDown[nextY][nextX] = leftDown[y][x];
						
						nextY += dxy[0][0];
						nextX += dxy[0][1];
					}
				}
				
				if(rightDown[y][x] == 0)
				{
					rightDown[y][x] = ++idxR;
					int nextY = y + dxy[1][0];
					int nextX = x + dxy[1][1];
					
					while(validate(nextY, nextX))
					{
						rightDown[nextY][nextX] = rightDown[y][x];
						
						nextY += dxy[1][0];
						nextX += dxy[1][1];
					}
				}
				
			}
		}
		
		// 왼쪽 -> 오른쪽 연결
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(!isBlock[y][x])
					adList[leftDown[y][x]].add(rightDown[y][x]);

		int matchCnt = 0;
		
		for(int i=1; i<=idxL; i++)
		{
			++time;
			if(dfs(i))
				++matchCnt;
		}
		
		System.out.print(matchCnt);
	}
	static boolean dfs(int now) {
		for(int next : adList[now]) {
			if(visitTime[next] == time)
				continue;
			visitTime[next] = time;
			
			if(match[next] == -1 || dfs(match[next])) {
				match[next] = now;
				return true;
			}
		}
		return false;
	}
	static boolean validate(int y, int x) {
		return 1<=y && 1<=x && y<=N && x<=N && !isBlock[y][x];
	}
}