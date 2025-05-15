//https://www.acmicpc.net/problem/4376
//1초 / 128MB / 이하 테케 설명 
//2 2 5 10		// 동물 수, 구멍수, 매 도착시간(초단위), 동물의 속도(초당 미터 단위)
//1.0 1.0			// N줄에 동물의 좌표가 주어짐(미터 단위)
//2.0 2.0
//100.0 100.0		// M줄에 구멍의 좌표가 주어짐(미터 단위)
//20.0 20.0
//못들어간 동물 수 : 1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, S, V;
	static double dist;
	static int match[];
	static boolean visit[];
	static ArrayList<Integer>[] adList;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			String str = br.readLine();
			if(str == null || "".equals(str))
				break;
			
			StringTokenizer st = new StringTokenizer(str);
			N = Integer.parseInt(st.nextToken());// 동물 수(1<=100)
			M = Integer.parseInt(st.nextToken());// 구멍수(1<=100)
			S = Integer.parseInt(st.nextToken());// 매 도착시간(초단위)(1<=100)
			V = Integer.parseInt(st.nextToken());// 동물의 속도(초당 미터 단위) (1<=100)
			dist = S * V * S * V;
			match = new int[M + 1];
			visit = new boolean[M + 1];
			adList = new ArrayList[N + 1];
			double pos[][] = new double[N + 1][2];
			
			for(int i=1; i<=N; i++)
			{
				st = new StringTokenizer(br.readLine());
				pos[i][0] = Double.parseDouble(st.nextToken());
				pos[i][1] = Double.parseDouble(st.nextToken());
				adList[i] = new ArrayList<>();
			}
			
			for(int i=1; i<=M; i++)
			{
				st = new StringTokenizer(br.readLine());
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				
				for(int j=1; j<=N; j++)
				{
					double diffX = x - pos[j][0];
					double diffY = y - pos[j][1];
					if(dist >= diffX * diffX + diffY * diffY)
						adList[j].add(i);
				}
			}
			
			int cnt = N;
			
			for(int i=1; i<=N; i++)
			{
				Arrays.fill(visit, false);
				if(dfs(i))
					--cnt;
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
	static boolean dfs(int animal) {
		
		for(int hole : adList[animal])
		{
			if(visit[hole])
				continue;
			
			visit[hole] = true;
			
			if(match[hole] == 0 || dfs(match[hole]))
			{
				match[hole] = animal;
				return true;
			}
		}
		
		return false;
	}
}