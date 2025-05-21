//https://www.acmicpc.net/problem/11378
//3초 256MB
//5 5 1// 직원 수(1<=1,000), 일의 수(1<=1,000), 벌점의 합(1<=사람수)
//5 1 2 3 4 5 // N개 줄에 i번 직원이 할 수 있는 일의 개수와 그 일의 번호가 주어짐
//1 1
//1 1
//1 1
//2 1 5
//최대 가능한 일 : 4

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, K;
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 직원 수(1<=1,000)
		M = Integer.parseInt(st.nextToken());// 일의 수(1<=1,000)
		K = Integer.parseInt(st.nextToken());// 벌점의 합(1<=사람수)
		match = new int[M + 1];
		visitTime = new int[M + 1];
		adList = new ArrayList[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			adList[i] = new ArrayList<>();
			for(int j=0; j<cnt; j++)
				adList[i].add(Integer.parseInt(st.nextToken()));
		}
		
		int jobCnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			++time;
			if(dfs(i))
				++jobCnt;
		}
		
		++time;
		
		for(int i=1; i<=N && K != 0; i++)
		{
			while(dfs(i) && K != 0)
			{
				++jobCnt;
				--K;
				++time;
			}
		}
		
		
		System.out.print(jobCnt);
	}
	static boolean dfs(int person)
	{
		for(int job : adList[person])
		{
			if(visitTime[job] == time)
				continue;
			
			visitTime[job] = time;
			
			if(match[job] == 0 || dfs(match[job])) {
				match[job] = person;
				return true;
			}
		}
		
		return false;
	}
}