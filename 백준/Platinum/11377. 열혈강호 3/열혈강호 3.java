//https://www.acmicpc.net/problem/11377
//3초 / 256MB / 이하 테케 설명 
//요약 : 직원N명, 일M개, K명의 직원은 일을 최대 2개, 나머진 1개의 일만 담당가능할 때, 처리할 수 있는 일의 최대수 출력
//5 5 1		// 직원수(1<1,000), 일수(1<=1,000), 일2개할 수 있는 직원(1<=N)
//3 1 2 3	// i번 직원이 할 수 있는 일의 개수와 그만큼 일이 주어짐
//3 1 2 3
//1 5
//1 5
//1 5
////답 : 4
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int time;
	static int match[];
	static int visitTime[];
	static ArrayList<Integer> adList[];
	static int N, M, K;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 직원수(1<1,000)
		M = Integer.parseInt(st.nextToken());// 일수(1<=1,000)
		K = Integer.parseInt(st.nextToken());// 일2개할 수 있는 직원(1<=N)
		
		match = new int[M + 1];
		visitTime = new int[M + 1];
		adList = new ArrayList[N + 1];
		
		for(int person=1; person<=N; person++)
		{
			adList[person] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken());
			while(j-->0)
			{
				int job = Integer.parseInt(st.nextToken());
				adList[person].add(job);
			}
		}
		
		int cnt = 0;
		
		for(int person=1; person<=N && cnt != M; person++)
		{
			++time;
			if(dfs(person))
				++cnt;
		}
		
		for(int person=1; person<=N && K != 0 && cnt != M; person++)
		{
			++time;
			if(dfs(person))
			{
				++cnt;
				--K;
			}
		}
		
		System.out.print(cnt);
	}
	// dfs 함수는 job에 사람이 매칭될 때마다 true를 반환한다. person이 갖고있는 일에 대해 매칭이 어쨋든 가능하면 무조건 true반환
	// 내가 갖고있는 job을 내가 할 수 있는지 확인하기 위해, 내가 할 수 있는 job에 다른 사람이 매칭되있다면,
	// 그 사람에게 다른 job으로 갈 수 있는지 끊임 연쇄적으로 없이 묻는다.
	static boolean dfs(int person)
	{
		for(int job : adList[person])
		{
			if(visitTime[job] == time)
				continue;
			
			visitTime[job] = time;
			
			if(match[job] == 0 || dfs(match[job]))
			{
				match[job] = person;
				return true;
			}
		}
		return false;
	}
}