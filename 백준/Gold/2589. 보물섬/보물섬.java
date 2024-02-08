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
	
	static char arr[][];
	static boolean visit[][];
	static int i, j, N, M,  MAX_LENGTH;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static ArrayDeque<Point> q;
	public static void BFS(int startN, int startM) {
		q = new ArrayDeque<>();
		visit = new boolean[N][M];
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
				
				if( !visit[nextN][nextM] && arr[nextN][nextM] == 'L') {
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
		arr 				= new char[N][M];
		
		// 패딩 넣기 
		for(i=0; i<N; i++)
			arr[i][0] = arr[i][M-1] = 'W';
		for(i=0; i<M; i++)
			arr[0][i] = arr[N-1][i] = 'W';
		
		for(i=1; i<N-1; i++) {
			char c[] = br.readLine().toCharArray();
			for(j=1; j<M-1; j++) {
				arr[i][j] = c[j-1];
			}
		}
		
		for(i=1; i<N-1; i++) {
			for(j=1; j<M-1; j++) {
				if(arr[i][j] == 'L')
					BFS(i,j);
			}
		}
		
		System.out.println(MAX_LENGTH);
		
	}
}