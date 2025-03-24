//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/23748
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 고객수N(1<=50)
		int X = Integer.parseInt(st.nextToken());// X(1<=100)할당량
		int Y = Integer.parseInt(st.nextToken());// Y(1<=100)할당량
		int vx[] = new int[N + 1];
		int vy[] = new int[N + 1];
		int xsum = 0;
		int ysum = 0;
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			xsum += vx[i] = Integer.parseInt(st.nextToken());// 판매성공시 X 양(1<=100)
			ysum += vy[i] = Integer.parseInt(st.nextToken());// 판매성공시 Y 양(1<=100)
		}
		
		if(xsum < X || ysum < Y)
		{
			System.out.print(-1);
			return;
		}
		
		int dp[][] = new int[X + 1][Y + 1];
		
		for(int x = 0; x<=X; x++)
			Arrays.fill(dp[x], 100);
		
		dp[0][0] = 0; // x,y를 만드는데 드는 최소 사람 0명 세팅
		
		int lastIdx = 51;
		int min		= 100;
		for(int i = 1; i<=N; i++) {
			for(int x=X; x>=0; x--) {
				for(int y=Y; y>=0; y--) {
					if(dp[x][y] == 100) continue;
					
					int nx = Math.min(X, x + vx[i]);
					int ny = Math.min(Y, y + vy[i]);
					
					if(dp[nx][ny] > dp[x][y] + 1) {
						dp[nx][ny] = dp[x][y] + 1;
						if(ny == Y && nx == X && min > dp[nx][ny])
						{
							min = dp[nx][ny];
							lastIdx = i;
						}
					}
				}
			}
		}
		
		System.out.println(dp[X][Y]);
		System.out.println(lastIdx);
	}

}