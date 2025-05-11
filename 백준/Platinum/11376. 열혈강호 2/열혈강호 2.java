//https://github.com/KimyongJ/algorithm
//https://www.acmicpc.net/problem/11376
//4초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static ArrayList<Integer>[] adList;		// 일마다 사람번호를 갖고 있음
	static int[][] match;		// [사람번호][0] + [사람번호][1] : 매칭된 job번호
	static boolean[][] visit;	// [사람번호][0] + [사람번호][1] : 방문유무
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());// 직원수(1<=1,000)
		M		= Integer.parseInt(st.nextToken());// 일의수(1<=1,000)
		match	= new int[N + 1][2];
		adList	= new ArrayList[M + 1];
		
		for(int i=0; i<=M; i++)
			adList[i] = new ArrayList<>();
		
		for(int person=1; person<=N; person++)
		{
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());

			for(int j=0; j<cnt; j++)
				adList[Integer.parseInt(st.nextToken())].add(person); 
		}
		
		int cnt = 0;
		for(int job=1; job<=M; job++)
		{
			visit = new boolean[N + 1][2];
			if(dfs(job))
				++cnt;
		}
		System.out.print(cnt);
	}
	static boolean dfs(int job) {
		for(int person : adList[job])
		{
			for(int i=0; i<=1; i++)
			{
				if(visit[person][i])
					continue;
				
				visit[person][i] = true;
				
				if(match[person][i&1] != job && (match[person][i] == 0 || dfs(match[person][i])))
				{
					match[person][i] = job;
					return true;
				}
			}
		}
		return false;
	}
}
//5 5		// 직원수(1<=1,000), 일의수(1<=1,000)
//2 1 2		// i번 직원이 할 수 있는 일의 개수와 일의 번호가 주어짐
//2 1 2
//2 1 2
//2 4 5
//0
//답 : 4