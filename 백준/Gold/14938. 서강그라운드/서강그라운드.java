//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static final int INF = 3_001;
	static int N, M, R, item[], map[][];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 지역 개수
		M = Integer.parseInt(st.nextToken()); // 예으니 수색범위
		R = Integer.parseInt(st.nextToken()); // 길의 개수
		item = new int[N]; // 지역당 아이템 개수
		map = new int[N][N]; // 맵
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			item[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				map[i][j] = INF;
			}
		
		int a,b,c;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken());
			map[a][b] = c;
			map[b][a] = c;
		}
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				if(k==i) continue;
				for(int j=0; j<N; j++) {
					if(i==k || j==k) continue;
					if(map[i][j] > map[i][k]+map[k][j]) {
						map[i][j] = map[i][k]+map[k][j];
					}
				}
			}
		}
		int result = 0, sum;
		for(int i=0; i<N; i++) {
			sum = 0;
			for(int j=0; j<N; j++) {
				if(map[i][j] <= M) {
					sum += item[j];
				}
			}
			result = Math.max(result, sum);
		}
		System.out.println(result);
	}
}