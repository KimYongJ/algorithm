//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/29756
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int N, K;
	static int[][] dp;// 구간 : 체력
	static int[] score, hp;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());// 구간개수 1<=천
		K		= Integer.parseInt(st.nextToken());// 회복되는 체력 1<=10
		score	= new int[N + 1];
		hp		= new int[N + 1];
		dp		= new int[N + 1][101];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			score[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			hp[i] = Integer.parseInt(st.nextToken());
		
		for(int[] row : dp)
			Arrays.fill(row, -1);
		
		System.out.print(top_down(N, 100));
	}
	public static int top_down(int depth, int curHP) {
		if(depth == 0)
			return 0;
		if(dp[depth][curHP] >= 0)
			return dp[depth][curHP];
		// 체력은 100을 넘을 수 없다.
		int nextHP = Math.min(100, curHP + K);
		// 해당 게임을 안하는 경우
		dp[depth][curHP] = top_down(depth - 1, nextHP);
		// 해당 게임을 하는 경우
		if(nextHP - hp[depth] >= 0) {
			dp[depth][curHP] = Math.max(dp[depth][curHP], top_down(depth - 1, nextHP - hp[depth]) + score[depth]);
		}
		return dp[depth][curHP];
	}
}
