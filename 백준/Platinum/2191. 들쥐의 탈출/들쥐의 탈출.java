//https://www.acmicpc.net/problem/2191
//2초 128MB / 이하 테스트케이스 설명
//2 2 5 10	// 들쥐수(1≤100), 땅굴수(1≤100), 매 도착시간(1≤100), 들쥐의 초당 이동 거리(1≤100)
//1.0 1.0		// N개의 줄에 들쥐 좌표 x,y가 주어짐(절대 값1,000이하 소수점세자리 주어짐)
//2.0 2.0
//100.0 100.0	// M개 줄에 땅굴의 좌표 x,y가 주어짐(절대 값1,000이하 소수점세자리 주어짐)
//20.0 20.0
//잡아먹히는 들쥐 최솟값 : 1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int match[];
	static boolean visit[];
	static ArrayList<Integer>[] adList;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 들쥐수(1≤100)
		int M = Integer.parseInt(st.nextToken());// 땅굴수(1≤100)
		int S = Integer.parseInt(st.nextToken());// 매 도착시간(1≤100)
		int V = Integer.parseInt(st.nextToken());// 들쥐의 초당 이동 거리(1≤100)
		float limit = S * V * S * V;// 들쥐가 이동 가능한 거리의 제곱
		
		adList = new ArrayList[N + 1];
		match = new int[M + 1];
		visit = new boolean[M + 1];
		
		float mouse[][] = new float[N + 1][2];
		for(int i=1; i<=N; i++)// 들쥐의 좌표가 주어짐
		{
			st = new StringTokenizer(br.readLine());
			mouse[i][0] = Float.parseFloat(st.nextToken());
			mouse[i][1] = Float.parseFloat(st.nextToken());
			adList[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=M; i++)// 땅굴의 좌표가 주어짐
		{
			st = new StringTokenizer(br.readLine());
			float x = Float.parseFloat(st.nextToken());
			float y = Float.parseFloat(st.nextToken());
			
			for(int j=1; j<=N; j++)
			{
				float xx = x - mouse[j][0];
				float yy = y - mouse[j][1];
				if((xx * xx + yy * yy) <= limit)
					adList[j].add(i);
			}
		}
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			Arrays.fill(visit, false);
			if(dfs(i))
				++cnt;
		}
		
		System.out.print(N - cnt);
	}
	static boolean dfs(int mouse) {
		for(int hole : adList[mouse])
		{
			if(visit[hole])
				continue;
			
			visit[hole] = true;
			
			if(match[hole] == 0 || dfs(match[hole]))
			{
				match[hole] = mouse;
				return true;
			}
		}
		return false;
	}
}