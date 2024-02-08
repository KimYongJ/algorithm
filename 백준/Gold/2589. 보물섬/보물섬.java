// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Point{
	int n,m, dist;
	Point(int n, int m, int dist){
		this.n = n;
		this.m = m;
		this.dist = dist;
	}
}
class Main{
	
	final static int SEA 	= 0;
	final static int LAND 	= 1;
	static boolean visit[][];
	static int i, j, N, M, MAX_LENGTH , arr[][];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static ArrayDeque<Point> q;
	
	public static void BFS(int startN, int startM) {
		q 		= new ArrayDeque<>();
		visit 	= new boolean[N][M];
		
		q.add(new Point(startN, startM, 0));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			// 가장 긴거리 넣기 
			MAX_LENGTH = Math.max(MAX_LENGTH, now.dist);
			
			if(visit[now.n][now.m]) // 방문한 적있다면 스킵  
				continue;
			
			visit[now.n][now.m] = true;  // 방문 처리 
			
			for(int[] xy : dxy) {
				int nextN = now.n + xy[0];
				int nextM = now.m + xy[1];
				
				if( !visit[nextN][nextM] && arr[nextN][nextM] == LAND) {
					q.add(new Point(nextN, nextM, now.dist+1));
				}
			}
		}
	}
   
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		N 					= Integer.parseInt(st.nextToken())+2;
		M 					= Integer.parseInt(st.nextToken())+2;
		arr 				= new int[N][M];
		
		// 패딩 넣기 
		for(i=0; i<N; i++)
			arr[i][0] = arr[i][M-1] = SEA;
		for(i=0; i<M; i++)
			arr[0][i] = arr[N-1][i] = SEA;
		
		for(i=1; i<N-1; i++) {
			String str = br.readLine();
			for(j=1; j<M-1; j++) {
				if(str.charAt(j-1) == 'W')
					arr[i][j] = SEA;
				else 
					arr[i][j] = LAND;
			}
		}
		
		for(i=1; i<N-1; i++) {
			for(j=1; j<M-1; j++) {
				if(arr[i][j] == LAND)
					BFS(i,j);
			}
		}
		
		System.out.println(MAX_LENGTH);
		
	}
}