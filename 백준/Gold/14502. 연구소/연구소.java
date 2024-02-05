import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, result, cnt0, cnt1, cnt2, arr[][];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean[][] visit, visit_bfs;
	static ArrayList<int[]> position2;
	static ArrayDeque<int[]> q;
	public static void BFS() {
		cnt0 = N*M - cnt1 - cnt2 - 3;
		q = new ArrayDeque<>();
		visit_bfs = new boolean[N][M];
		for(int[] p : position2) 
		{
			q.add(new int[] {p[0],p[1]}); // 2의 위치를 큐에 넣는다. 
		}
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int[] xy : dxy) {
				int nextY = now[0] + xy[0];
				int nextX = now[1] + xy[1];
				
				
				if(nextX>=0 && nextY>=0 && nextX<M && nextY <N
						&& !visit_bfs[nextY][nextX] && arr[nextY][nextX]==0) {
					visit_bfs[nextY][nextX] = true;
					cnt0--;
					if(result > cnt0) {
						return;
					}
					q.add(new int[] {nextY,nextX});
				}
			}
		}
		result = Math.max(result, cnt0);
	}

	public static void backtracking(int wall,int y, int x) {
		if(wall==3) { // 벽 3개를 다 놓았을 때
			BFS();
			return;
		}
		for(int i=y; i<N; i++) {
			for(int j=x; j<M; j++) {
				if(!visit[i][j] && arr[i][j]==0) {
					visit[i][j] = true;
					arr[i][j] = 1;
					backtracking(wall+1, i,0);
					arr[i][j] = 0;
					visit[i][j] = false;
				}
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new boolean[N][M];
		position2 = new ArrayList<>(); // 2의 위치를 담을 리스트 
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) // 벽의 갯수를 센다 
					cnt1++;
				else if(arr[i][j] == 2)  // 바이러스의 위치를 저장하고 갯수를 셈
				{
					position2.add(new int[] {i,j});
					cnt2++;
				}
			}
		}
		backtracking(0,0,0); // 백트레킹으로 3개를 놓는 것에 대해 완전 탐색진행 
		System.out.println(result);
	}
}