// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int X, Y, W, B;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean map[][], visit[][];
	
	public static boolean validate(int y, int x) 
	{
		return y>=0 && x>=0 && y<Y && x<X;
	}
	
	public static int DFS(boolean type, int y, int x) {
		if(visit[y][x])  								// 방문한 곳인 경우 0 반환 하여 연산하지 않음 
			return 0;
		visit[y][x] = true; 							// 방문 처리 
		int cnt = 1;									// 방문 갯수 체크 
		
		int ny, nx;
		for(int xy[] : dxy) {
			ny = y + xy[0]; 							// 새로운 y좌표
			nx = x + xy[1]; 							// 새로운 x좌표
			if(validate(ny,nx) && map[ny][nx] == type) 	// 유효성 검증 + 같은 문자열이면 DFS 재진행
				cnt += DFS(type, ny, nx); 				// DFS진행하면서 방문한 공간 +해줌
		}
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		X 					= Integer.parseInt(st.nextToken());
		Y 					= Integer.parseInt(st.nextToken());
		map 				= new boolean[Y][X];
		visit 				= new boolean[Y][X];
		
		for(int i=0; i<Y; i++) {
			String str = br.readLine();
			for(int j=0; j<X; j++)
				if(str.charAt(j) == 'W')
					map[i][j] = true;				// W이면 true B이면 false
		}
		
		for(int i=0; i<Y; i++) 
			for(int j=0; j<X; j++) {
				if(!visit[i][j]) { 					// 방문하지 않은 곳만 DFS진행 
					int cnt = DFS(map[i][j],i,j); 	// DFS진행 결과로 총 몇군데를 방문했는지 반환 받음
					if(map[i][j]) 	W += cnt*cnt; 	// map정보가 true면 W에, false면 B에 값을 바인딩
					else 			B += cnt*cnt; 	// map정보가 true면 W에, false면 B에 값을 바인딩
				}
			}
		
		System.out.print(W);
		System.out.print(" ");
		System.out.print(B);
	}
}