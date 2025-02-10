//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10164
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y = Integer.parseInt(st.nextToken());//1<=15
		int X = Integer.parseInt(st.nextToken());//1<=15
		int O = Integer.parseInt(st.nextToken())-1;
		int oy= 1;
		int ox= 1;
		int dp[][] = new int[Y+1][X+1];
		dp[0][1] = 1;
		if(O >= 0)
		{
			oy = (O / X)+1;
			ox = (O % X)+1;
			
			for(int y=1; y<=oy; y++)
				for(int x=1; x<=ox; x++)
					dp[y][x] += dp[y-1][x] + dp[y][x-1];
			
			dp[oy][ox-1] = dp[oy-1][ox] = 0;
		}
		
		for(int y=oy; y<=Y; y++)
			for(int x=ox; x<=X; x++)
				dp[y][x] += dp[y-1][x] + dp[y][x-1];
		
		System.out.print(dp[Y][X]);
	}
}
