//https://www.acmicpc.net/problem/12843
//5초 512MB
//5 4// 강의 개수(1 ≤ n ≤ 2,000), 수업 간 관계의 개수 (1 ≤ m ≤ 1,000,000) 
//1 c// n개 줄에 대해 강의의 번호와 어느 학부의 강의인지 입력됨(c=컴퓨터학부, s=소프트웨어 학부)
//2 s
//3 c
//4 s
//5 c
//1 2// m개 줄에 겹치는 내용이 존재한 관계 번호들이 주어지며 동일한 두 강의의 관계가 중복해서 나오지 않음
//2 3
//3 4
//4 5
// 답 : 3


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int cIdx[];
	static int sIdx[];
	static List<Integer> adList[];
	// 이분 매칭시 사용
	static int time;
	static int match[];
	static int visitTime[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cIdx = new int[N + 1];
		sIdx = new int[N + 1];
		adList = new ArrayList[N + 1];
		
		for(int i=1,idxC = 0, idxS = 0; i<=N; i++)
		{
			adList[i] = new ArrayList<>(); // 인접리스트 초기화
			
			st = new StringTokenizer(br.readLine());
			
			int node = Integer.parseInt(st.nextToken());// 노드번호
			char c = st.nextToken().charAt(0);// 과목
			if(c == 'c')// 컴퓨터 과목이면 컴퓨터 과목 배열에 인덱스 마킹
				cIdx[node] = ++idxC;
			else// 소프트웨어 과목이면 해당 과목 배열에 인덱스 마킹
				sIdx[node] = ++idxS;
		}
		
		for(int i=1;i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 컴퓨터 과목기준으로 인접 리스트 생성
			if(cIdx[a] != 0)
				adList[cIdx[a]].add(sIdx[b]);
			else
				adList[cIdx[b]].add(sIdx[a]);
		}
		
		match = new int[N + 1];
		visitTime = new int[N + 1];
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			++time;
			if(dfs(i))
				++cnt;
		}
		System.out.print(N - cnt);
	}
	static boolean dfs(int now)
	{
		for(int next : adList[now])
		{
			if(visitTime[next] == time)
				continue;
			
			visitTime[next] = time;
			
			if(match[next] == 0 || dfs(match[next]))
			{
				match[next] = now;
				return true;
			}
		}
		return false;
	}
}