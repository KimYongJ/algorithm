// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int		dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int		totalBlank;
	static int		result;
	static int		Y, X;
	static boolean	visit[][];
	
	public static void backtracking(int y, int x, int mCnt, int vCnt) {
		if(vCnt == totalBlank) 					// 모든칸을 방문했을 경우
		{
			result = Math.min(result, mCnt);	// 꺾은 횟수의 최소값을 result에 삽입
			return;
		}
		if(result <= mCnt) 						// 이미 구한 값보다 꺾은 횟수가 같거나 클 경우 미리 종료
		{
			return;
		}
		
		int ny, nx, cnt;
		for(int xy[] : dxy)
		{
			cnt = 0;
			ny = y; 
			nx = x;
			while(true) 
			{
				ny += xy[0];
				nx += xy[1];
				if(ny>=0 && nx>=0 && ny<Y && nx<X && !visit[ny][nx]) 
				{
					visit[ny][nx] = true;
					cnt ++;
				}
				else 
				{
					ny -= xy[0];
					nx -= xy[1];
					break;
				}
			}
			if(cnt > 0) 
			{
				backtracking(ny, nx, mCnt + 1, vCnt + cnt);
				while(cnt--> 0) 
				{
					visit[ny][nx] = false;
					ny -= xy[0];
					nx -= xy[1];
				}
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		
		int T = 1;
		String str = br.readLine(); // 맵크기 입력 받음 
		while(str != null && str.length() > 0) 
		{
			st			= new StringTokenizer(str);
			Y			= Integer.parseInt(st.nextToken());
			X			= Integer.parseInt(st.nextToken());
			result		= Integer.MAX_VALUE;
			totalBlank	= 0;
			visit		= new boolean[Y][X];
			
			for(int y=0; y<Y; y++) 
			{
				str = br.readLine();
				for(int x=0; x<X; x++) 
				{ 
					if(str.charAt(x) == '.')
						totalBlank++;
					else 
						visit[y][x] = true;
				}
			}
			
			for(int y=0; y<Y; y++) 
			{
				for(int x=0; x<X; x++) 
				{
					if(!visit[y][x]) 
					{
						visit[y][x] = true;
						backtracking(y, x, 0, 1);// y좌표, x좌표, 꺽은 횟수, 방문한 칸 개수
						visit[y][x] = false;
					}
				}
			}
			
			if(result == Integer.MAX_VALUE)
			{
				result = -1;
			}
			sb.append("Case ").append(T++).append(": ")
				.append(result).append('\n');
			str = br.readLine(); // 맵크기 입력 받음
		}
		System.out.print(sb);
	}
}