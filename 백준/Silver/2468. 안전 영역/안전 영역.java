// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		new Solution().solution();
	}
}

class Solution{
	
	int N, max_hight,safe_loc, arr[][];
	int dxy[][] = {{0,1},{1,0},{0,-1},{-1,0}};
	boolean visit[][];
	void solution()throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 			= Integer.parseInt(br.readLine());
		max_hight 	= 0;
		safe_loc 	= 0;
		arr 		= new int[N][N];
		for(int i=0; i<N; i++) {
			st 		= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				max_hight = Math.max(max_hight, arr[i][j]);
			}
		}
		// 가장 높은 높이부터 안전영역 갯수를 확인 하며 내려간다 
		for(int hight=max_hight-1; hight>=0; hight--) {
			visit 	= new boolean[N][N];
			int cnt = 0;
			for(int x=0; x<N; x++)
				for(int y=0; y<N; y++)
					// 방문하지 않았고, 안전영역이라면 DFS실행
					if(!visit[x][y] && arr[x][y] > hight) {
						cnt++;
						DFS(hight, x, y);
					}
			safe_loc = Math.max(safe_loc, cnt);
		}
		System.out.println(safe_loc);
	}
	// DFS팜색을 2차원에서 어떻게할 것인가 ? hight보다 큰 곳은 안전영역이다.
	// 방문하지 않았고, i보다 큰 곳이면 상하 좌우 DFS 실행하며 방문 처리
	// 다음 DFS할 좌표를 새로 생성할 때 유효성 체크 중요 
	public void DFS(int hight, int i, int j) {
		if(visit[i][j]) return;
		visit[i][j] 	= true;
		for(int xy[] : dxy) {
			int newI 	= i+xy[0];
			int newJ 	= j+xy[1];
			if(newI>=0 && newJ >=0 && newI<N && newJ<N && 
				!visit[newI][newJ] && arr[newI][newJ]>hight)
				DFS(hight, newI, newJ);
		}
	}
}

//private static int read() throws Exception { // 빠른 입력을 위한 함수
//    int c, n = System.in.read() & 15;
//    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
//    return n;
//}