// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int n, m, max, arr[][], dp[][] = new int[4][2];
	static int[][] dxy = {{0,1},{1,0},{-1,0},{0,-1}};
	static boolean[][] visit;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());		
		arr = new int[n][m];
		visit = new boolean[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++) {
				visit[i][j] = true;
				dp[0][0] = i;
				dp[0][1] = j;
				DFS(0,arr[i][j],i,j); 			// l L z 모양 탐색 DFS
				combination(0,0,arr[i][j],i,j); // ㅗ 모양 탐색 조합
				visit[i][j] = false;
			}

		System.out.println(max);
	}
	// ㅗ 모양 탐색
	public static void combination(int r,int start,int sum,int y,int x) {// 순서 : 구할 횟수 , 더할좌표의 시작위치, 좌표들의 sum, y좌표, x좌표
		if(r==3) {// r이 3이란 것은 sum에 4개의 값이 모두 더해진 상태라는 것이다.
			max = Math.max(max, sum);
			return;
		}
		for(int i=start;i<4;i++) {
			int y1 = y+dxy[i][0]; // 새로운 좌표 생성
			int x1 = x+dxy[i][1]; // 새로운 좌표 생성
			if(y1 >=0 && y1<n && x1>=0 && x1<m) { // 좌표 범위 체크
				combination(r+1,i+1,sum+arr[y1][x1],y,x);
			}
		}
	}
	// l L z 모양 탐색
	public static void DFS(int depth,int sum,int y, int x) {// 순서 : 깊이 , 좌표들의 sum, y좌표, x좌표
		if(depth==3) {// 깊이가 3일 때 sum에 4가지가 모두 더해진 상태이기 때문에 depth가 3일 때 종료
			max = Math.max(max, sum);
			return;
		}
		for(int i=0; i<4; i++) {
			int y1 = y+dxy[i][0]; // 새로운 좌표 생성
			int x1 = x+dxy[i][1]; // 새로운 좌표 생성
			if(y1 >=0 && y1<n && x1>=0 && x1<m && !visit[y1][x1]) {// 좌표 범위 체크
				visit[y1][x1] = true; // 백트레킹 방문 체크
				DFS(depth+1,sum+arr[y1][x1],y1,x1);// 다음 연산시작
				visit[y1][x1] = false;// 백트레킹 방문 해제
			}
		}
	}
}