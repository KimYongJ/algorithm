//https://www.acmicpc.net/problem/1867
//2초 128MB
//3 4// 행(열)길이(1<=500), 돌맹이수(1<=10,000)
//1 1// 중복없이 K줄에 돌멩이 위치 입력(행,열 순) 
//1 3
//2 2
//3 2
//직선 이동의 최소 횟수 출력(답) : 2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, K;
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> idx[];// y좌표에따른 x좌표를 갖고 있는다.
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 행(열)길이(1<=500)
		K = Integer.parseInt(st.nextToken());// 돌맹이수(1<=10,000)
		match = new int[N + 1];
		visitTime = new int[N + 1];
		idx = new ArrayList[N + 1];
		
		for(int i=1; i<=N; i++)
			idx[i] = new ArrayList<>();

		for(int i=0; i<K; i++)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			idx[y].add(x);
		}
		
		int cnt = 0;
		
		for(int y=1; y<=N; y++)
		{
			++time;
			if(dfs(y))
				++cnt;
		}
		
		System.out.print(cnt);
	}
	static boolean dfs(int y)
	{
		for(int x : idx[y])
		{
			if(visitTime[x] == time)
				continue;
			
			visitTime[x] = time;
			
			if(match[x] == 0 || dfs(match[x]))
			{
				match[x] = y;
				return true;
			}
		}
		return false;
	}
}