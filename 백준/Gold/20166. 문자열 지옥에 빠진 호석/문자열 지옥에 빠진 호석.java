// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main{
	static int 			dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}, {1,1},{1,-1},{-1,1},{-1,-1}};
	static int 			Y, X, K, nextX, nextY,cnt, maxlen;
	static Integer 		dp[][][];
	static String 		str;
	static char 		arrChar[], map[][];
	
	static HashMap<String, Integer> hm;
	static StringBuilder 			sb;
	
	public static int checkY(int y) {
		if(y<0) return Y-1;
		if(y==Y) return 0;
		return y;
	}
	public static int checkX(int x) {
		if(x<0) return X-1;
		if(x==X) return 0;
		return x;
	}
	// DFS함수는 해당 위치에서 탐색했을 때 해당 문자열까지 가는 총 경로를 반환한다.
	public static int DFS(int y, int x, int depth) {
		if(depth == maxlen) 				// 범위 초과시 0리턴
			return 0;
		if(dp[depth][y][x] != null) 		// 방문한적이 있다면 그 값 그대로 리턴
			return dp[depth][y][x];
		if(depth == maxlen-1 && map[y][x] == arrChar[depth])
			return dp[depth][y][x] = 1;		// 최종 목적지 도착시 값 저장 후 리턴
		
		dp[depth][y][x] = 0;				// 이 코드는 방문 체크와 동시에 해당 위치에 도달했을 때 몇개의 완성문자열을 담을 수 있는지를 저장하는것이다.
		for(int xy[] : dxy) {
			nextY = checkY(y+xy[0]);		// y좌표 생성
			nextX = checkX(x+xy[1]);		// x좌표 생성
			if(map[nextY][nextX] == arrChar[depth +1])	// 생성한 좌표가 갈 수 있을 때 
				dp[depth][y][x] += DFS(nextY, nextX, depth + 1); // DFS를 돌며 해당 깊이에 따른 좌표에 몇개의 완성문자열이 있는지 더해나간다.
		}
		return dp[depth][y][x];				// 해당 깊이에 따른 완성문자열이 몇개있는지 반환 
	}

	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb	= new StringBuilder();
		Y 	= Integer.parseInt(st.nextToken());
		X 	= Integer.parseInt(st.nextToken());
		K 	= Integer.parseInt(st.nextToken());
		map	= new char[Y][X];
		hm	= new HashMap<>();
		
		for(int y=0; y<Y; y++)
			map[y] = br.readLine().toCharArray();
		
		for(int k=0; k<K; k++) 
		{
			str = br.readLine();
			if(hm.containsKey(str))						// 해당 문자가 이미 탐색했던 문자라면
				sb.append(hm.get(str)).append('\n');	// 이미 탐색한 값을 바인딩
			else 
			{
				arrChar	= str.toCharArray();			// 문자열을 char배열에 담음
				maxlen	= arrChar.length;				// 문자열의 길이 저장
				cnt 	= 0;							// 카운팅할 변수
				dp 		= new Integer[5][Y][X];			// 깊이를 기준으로 그 좌표에 도달했을 때 몇개의 완성문자열을 갈 수 있는지를 저장할 dp 
				for(int y=0; y<Y; y++)					// 2중 for문으로 각 맵을 탐색
					for(int x=0; x<X; x++)
						if(map[y][x] == arrChar[0]) 	// 첫번째 글자가 같으면 
							cnt += DFS(y,x,0);			// DFS의 반환 값으로 해당 위치에서 탐색했을 때 완성 문자열을 몇개 만들 수 있는지 반환 받는다.
				
				sb.append(cnt).append('\n');
				hm.put(str, cnt); // 문자열과 이동경로 삽입
			}
		}
		System.out.print(sb);
	}
}