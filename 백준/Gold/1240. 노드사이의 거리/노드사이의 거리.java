// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	static final long MAX = 2000000000;
	static long N, M, map[][];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new long[(int) (N+1)][(int) (N+1)];
	
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N;j++) {
				if(i==j) continue;
				map[i][j] = MAX;
			}
		}
		int a,b,c;
		for(int n=1; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(map[a][b] > c)
				map[a][b] = map[b][a] = c;
		}
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(k==i) continue;
				for(int j=1; j<=N; j++) {
					if(i==j || j==k) continue;
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[j][i] = map[i][k] + map[k][j];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sb.append(map[a][b]).append('\n');
		}
		System.out.println(sb);
	}
}