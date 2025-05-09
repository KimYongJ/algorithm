//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/29447
//2초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;				// 직원 수
	static int M;				// 일의 최대 수
	static int[] job;			// 각일에 대해 현재 매칭된 사람을 담을 배열, idx가 일의 번호, value가 매칭된 사람
	static boolean[] jobVisit;	// 인덱스가 일의 번호를 의미하며, 해당 일을 방문 해서 체크했는지를 저장
	static int[][]adList;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());// 직원 수(1<=1,000)
		M		= Integer.parseInt(st.nextToken());// 일의 수(1<=1,000)
		adList	= new int[N + 1][];
		job		= new int[M + 1];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken());
			
			adList[i] = new int[j];
			
			while(--j>=0)
				adList[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;

		for(int person=1; person<=N; person++)
		{
			jobVisit = new boolean[M + 1];
			if(dfs(person))
				++cnt;
		}
		System.out.print(cnt);
	}
	static boolean dfs(int person)
	{
		for(int jobNumber : adList[person])
		{
			if(jobVisit[jobNumber])
				continue;
			
			jobVisit[jobNumber] = true;
			
			if(job[jobNumber] == 0 || dfs(job[jobNumber]))
			{
				job[jobNumber] = person;
				return true;
			}
		}
		return false;
	}
}

//5 5		// 직원 수(1<=1,000), 일의 수(1<=1,000)
//2 1 2	// 각 직원이 할 수 있는 일의 번호
//1 1
//2 2 3
//3 3 4 5
//1 1
//// 답
//4