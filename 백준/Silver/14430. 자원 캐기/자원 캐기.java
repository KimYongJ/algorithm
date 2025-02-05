//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14430
//2초 / 25MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int Y		= Integer.parseInt(st.nextToken());	// 세로 1<=300
		int X		= Integer.parseInt(st.nextToken());	// 가로 1<=300
		int dp[][]	= new int[Y+1][X+1];
		
		for(int y=1; y<=Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
			{
				dp[y][x] = Integer.parseInt(st.nextToken()) + Math.max(dp[y-1][x], dp[y][x-1]);
			}
		}
		
		System.out.print(dp[Y][X]);
	}
}
