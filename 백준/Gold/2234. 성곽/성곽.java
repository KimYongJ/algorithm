// https://github.com/KimYongJ/algorithm
import java.util.HashMap;


class Main{

	static int dy[] 		= {0,-1,0,0,1}; // 서, 북, 동, 더미, 남 순서
	static int dx[] 		= {-1,0,1,0,0}; // 서, 북, 동, 더미, 남 순서
	static int room_cnt , max_area, big_area;
	static int N, M, map[][], numberMap[][];
	static boolean visit[][];
	static HashMap<Integer, Integer> mark = new HashMap<>(); // 마킹 숫자 : 넓이 순서
	
    // 빠른 입력을 위해 만든 함수
    public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}

    // 좌표 유효성 검사하는 로직
	public static boolean validate(int i, int j) {
		return i>=0 && j>=0 && i<M && j<N;
	}
    // DFS
	public static int DFS(int i, int j) {
		if(visit[i][j]) return 0;
		visit[i][j] 		= true;
		numberMap[i][j] 	= room_cnt; 		// 구역마다 숫자로 마킹
		int area = 1;							// DFS하면서 마킹한 범위를 반환할 변수
		for(int x=1; x<=8; x<<=1) {
			if( (map[i][j] & x) == 0) { 		// and 연산하면 벽이 없을 경우 0이 된다.
				int p = x/2; 					// p : 0은 서, 1은 북, 2는 동, 4는 남
				area += DFS(i+dy[p],j+dx[p]);	// area를 더해줌
			}
		}
		return area;
	}
	
	public static void main(String[] args)throws Exception{
		N 					= read();
		M 					= read();
		visit 				= new boolean[M][N]; 						
		map 				= new int[M][N];
		numberMap 			= new int[M][N];
		for(int i=0; i<M; i++) 
			for(int j=0; j<N; j++)
				map[i][j] 	= read();
		
		for(int i=0; i<M; i++)
			for(int j=0; j<N;j++)
				if(!visit[i][j]) { // 방문하지 않은 경우 DFS수행, DFS수행시 number를 numberMap에 마킹함 
					room_cnt++;
					int total_area = DFS(i,j);
					max_area = Math.max(max_area, total_area);
					mark.put(room_cnt , total_area); // 추후 벽을 허물 때 연산을 빠르게 하기 위해 마킹 숫자와 해당 숫자의 넓이 저장
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