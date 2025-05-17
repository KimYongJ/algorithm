//https://www.acmicpc.net/problem/30976
//1초 512MB
//2 2		// 여학생수(1<=400, 남학생수(1<=400)
//168 164	// i번째 여학생의 키
//179 183	// i번째 남학생의 키
//180 190	// i번째 여학생의 선호기준 ( 여학생은 자신의 선호 기준보다 작은 학생만 )
//155 165	// i번째 남학생의 선호기준 ( 남학생은 자신의 선호 기준보다 키가 큰 학생만 )
// 답 : 1

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 여학생수(1<=400)
		int M = Integer.parseInt(st.nextToken());// 남학생수(1<=400)
		int G[] = new int[N + 1];// 여학생의 키
		int B[] = new int[M + 1];// 남학생의 키
		int T[] = new int[N + 1];// 여학생의 선호 기준
		
		adList = new ArrayList[M + 1];
		match = new int[N + 1];
		visitTime = new int[402];
		
		// 여학생의 키 입력
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			G[i] = Integer.parseInt(st.nextToken());
		
		// 남학생의 키 입력
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++)
		{
			B[i] = Integer.parseInt(st.nextToken());
			adList[i] = new ArrayList<>();
		}
		// 여학생의 선호 기준 입력
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			T[i] = Integer.parseInt(st.nextToken());
		
		// 남학생의 선호 기준을 입력 받으면서 인접 리스트 생성
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++)
		{
			int h = Integer.parseInt(st.nextToken());// 남학생의 선호기준
			for(int j=1; j<=N; j++)// 모든 여학생 순회
				if(h < G[j] && T[j] > B[i])
					adList[i].add(j);
		}
		
		int cnt = 0;
		
		for(int i=1; i<=M; i++)
		{
			++time;
			if(dfs(i))
				++cnt;
		}
		
		System.out.print(cnt);
	}
	public static boolean dfs(int boy) {
		for(int girl : adList[boy])
		{
			if(visitTime[girl] == time)
				continue;
			
			visitTime[girl] = time;
			
			if(match[girl] == 0 || dfs(match[girl]))
			{
				match[girl] = boy;
				return true;
			}
		}
		return false;
	}
}