// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

class Point{
	int i, j;
	Point(int i, int j){
		this.i = i;
		this.j = j;
	}
}
class Main{
	
	static final int BLOCK = 100_000_000;
	static int N, L, R, cnt, arr[][], flag, flag_arr[][];
	static int population, areacnt;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean change, visit[][];
	static HashMap<Integer, Integer> flag_map;
	
	public static void changeArr() {
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++) {
				int key = flag_arr[i][j];
				arr[i][j] = flag_map.getOrDefault(key,arr[i][j]);
			}
		
	}
	public static void BFS(int i, int j) {
		boolean isVisit = false;
		population = 0;
		areacnt = 0;
		flag += 1;
		ArrayDeque<Point> q = new ArrayDeque<>();
		q.add(new Point(i,j));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(visit[now.i][now.j]) continue; // 방문했던 곳은 연산 스킵
			visit[now.i][now.j] = true;
			flag_arr[now.i][now.j] = flag; 
			population += arr[now.i][now.j];
			areacnt++;
			
			for(int xy[] : dxy) {
				int nextI = now.i + xy[0];
				int nextJ = now.j + xy[1];
				
				if(!visit[nextI][nextJ]) {
					int diff = Math.abs(arr[now.i][now.j] - arr[nextI][nextJ]);
					if(L<=diff && diff<=R) {
						isVisit = true;
						q.add(new Point(nextI, nextJ));
					}
				}
				
			}
			
		}
		
		
		if(isVisit) { // 국경을 합친 지역이 하나라도 있다면
			change = true; // 연산 계속
			int value = population / areacnt;// 덮어써야할 인원
			flag_map.put(flag, value);
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N+2][N+2];
		
		for(int i=0; i<=N+1; i++) {
			arr[0][i] = arr[N+1][i] = 
			arr[i][0] = arr[i][N+1] = BLOCK;
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		change = true;
		while(change) {
			change = false;
			visit = new boolean[N+2][N+2];
			flag = 0;
			flag_map = new HashMap<>();
			flag_arr = new int[N+2][N+2];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(!visit[i][j])
						BFS(i,j);
				}
			}
			if(change) {
				cnt++;
				changeArr();
			}
		}
		
		System.out.println(cnt);
	}
}