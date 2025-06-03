//https://www.acmicpc.net/problem/3736
//1초 128MB
//2// 작업 개수
//0: (1) 2// 0번 작업은 (1)개의 서버를 필요로하며 그 서버 번호가 2입니다.
//1: (1) 2// 1번 작업은 (1)개의 서버를 필요로하며 그 서버 번호가 1입니다.
//1// 반복..
//0: (1) 1
// 작업과 서버 최대매칭 수 출력( 서버는 하나의 작업만 처리 )
//1
//1

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int MAX = 10_001;
	static int N;
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		init();
		
		while(true)
		{
			String str = br.readLine();
			if(str == null || "".equals(str))
				break;
			
			clear();
			
			N = Integer.parseInt(str);
			
			for(int i=0; i<N; i++)
			{
				st = new StringTokenizer(br.readLine(), " |:()");
				int job = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				
				while(cnt-->0)
				{
					int server = Integer.parseInt(st.nextToken());
					adList[job].add(server - N);
				}
			}
			
			int cnt = 0;
			
			for(int i=0; i<N; i++)
			{
				++time;
				if(dfs(i))
					++cnt;
			}
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
	static boolean dfs(int job)
	{
		for(int server : adList[job])
		{
			if(visitTime[server] == time)
				continue;
			
			visitTime[server] = time;
			
			if(match[server] == -1 || dfs(match[server]))
			{
				match[server] = job;
				return true;
			}
		}
		return false;
	}
	static void init()
	{
		match = new int[MAX];
		visitTime = new int[MAX];
		adList = new ArrayList[MAX];
		
		for(int i=0; i<MAX; i++)
			adList[i] = new ArrayList<>();
	}
	static void clear()
	{
		Arrays.fill(match, -1);
		for(int i=0; i<MAX; i++)
			adList[i].clear();
	}
}