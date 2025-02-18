//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/29756
//1초 1024MB
import java.util.Arrays;
class Main{
	
	static int N, K;
	static int[][] dp;// 구간 : 체력
	static int[] score, hp;
	
	public static void main(String[] args)throws Exception{
		N		= read();// 구간개수 1<=천
		K		= read();// 회복되는 체력 1<=10
		score	= new int[N + 1];
		hp		= new int[N + 1];
		dp		= new int[N + 1][101];

		for(int i=1; i<=N; i++)	score[i] = read();
		for(int i=1; i<=N; i++)	hp[i] = read();
		
		for(int[] row : dp)
			Arrays.fill(row, -1);
		
		System.out.print(top_down(N, 100, dp));
	}
	public static int top_down(int depth, int curHP, int[][] dp) {
		if(depth == 0)
			return 0;
		if(dp[depth][curHP] >= 0)
			return dp[depth][curHP];
		// 체력은 100을 넘을 수 없다.
		int nextHP = Math.min(100, curHP + K);
		// 해당 게임을 안하는 경우
		dp[depth][curHP] = top_down(depth - 1, nextHP, dp);
		// 해당 게임을 하는 경우
		if(nextHP - hp[depth] >= 0)
			dp[depth][curHP] = Math.max(dp[depth][curHP], top_down(depth - 1, nextHP - hp[depth], dp) + score[depth]);
		
		return dp[depth][curHP];
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
