// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


class Main{

	static int dy[] 		= {0,-1,0,0,1}; // 서, 북, 동, 더미, 남 순서
	static int dx[] 		= {-1,0,1,0,0}; // 서, 북, 동, 더미, 남 순서
	static int cnt, room_cnt , max_area, big_area;
	static int N, M, map[][], numberMap[][];
	static boolean visit[][];
	static HashMap<Integer, Integer> mark = new HashMap<>(); // 마킹 숫자 : 넓이 순서
	
	public static boolean validate(int i, int j) {
		return i>=0 && j>=0 && i<M && j<N;
	}
	public static void DFS(int i, int j) {
		if(visit[i][j]) return;
		cnt++;
		visit[i][j] 		= true;
		numberMap[i][j] 	= room_cnt; // 구역마다 숫자로 마킹
		for(int x=1; x<=8; x<<=1) {
			if( (map[i][j] & x) == 0) { // and 연산하면 벽이 없을 경우 0이 된다.
				int p = x/2; // p : 0은 서, 1은 북, 2는 동, 4는 남
				int newI = i+dy[p];
				int newJ = j+dx[p];
				if(!visit[newI][newJ])
					DFS(newI,newJ);
			}
		}

	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		N 					= Integer.parseInt(st.nextToken());
		M 					= Integer.parseInt(st.nextToken());
		
		visit 				= new boolean[M][N]; 						
		map 				= new int[M][N];
		numberMap 			= new int[M][N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				map[i][j] 	= Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<M; i++)
			for(int j=0; j<N;j++)
				if(!visit[i][j]) { // 방문하지 않은 경우 DFS수행, DFS수행시 number를 numberMap에 마킹함 
					room_cnt++; 
					DFS(i,j);
					max_area = Math.max(max_area, cnt);
					mark.put(room_cnt , cnt); // 추후 벽을 허물 때 연산을 빠르게 하기 위해 마킹 숫자와 해당 숫자의 넓이 저장
					cnt = 0;
				}

		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				for(int n=1; n<=8; n<<=1) {
					if( (map[i][j] & n) != 0) { // 벽이 있을 경우 0이 아니다. 벽이있을 경우만 이하 실행
						int newI = i + dy[n/2];
						int newJ = j + dx[n/2];
						if(validate(newI, newJ)) { // 좌표에 문제가 없을 경우 
							int marking_number1 = numberMap[i][j];
							int marking_number2 = numberMap[newI][newJ];
							if(marking_number1 != marking_number2) {
								int area_sum = mark.get(marking_number1) + mark.get(marking_number2);
								big_area = Math.max(big_area, area_sum);
							}
						}
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(room_cnt).append('\n');
		sb.append(max_area).append('\n');
		sb.append(big_area).append('\n');
		System.out.println(sb);
	}

}