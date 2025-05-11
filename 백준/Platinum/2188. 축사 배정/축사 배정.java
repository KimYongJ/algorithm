//https://github.com/KimyongJ/algorithm
//https://www.acmicpc.net/problem/2188
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int[] match;
	static boolean[] visit;
	static int[][] adList;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 소의 수 (1<=200)
		M = Integer.parseInt(st.nextToken());// 축사의 수 (1<=200)
		adList = new int[N + 1][];
		match = new int[M + 1];
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			adList[i] = new int[cnt];
			
			for(int j=0; j<cnt; j++)
				adList[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			visit = new boolean[M + 1];
			if(dfs(i))
				++cnt;
		}
		System.out.print(cnt);
	}
	static boolean dfs(int now) {
		
		for(int cage : adList[now]) {
			if(visit[cage])
				continue;
			
			visit[cage] = true;
			
			if(match[cage] == 0 || dfs(match[cage]))
			{
				match[cage] = now;
				return true;
			}
		}
		return false;
	}
}
//5 5	// 소의 수 (1<=200), 축사의 수 (1<=200)
//2 2 5		// 각 소가 들어가길 원하는 축사의 수와, 그 축사 번호가 주어짐
//3 2 3 4
//2 1 5
//3 1 2 5
//1 2
//답 : 4