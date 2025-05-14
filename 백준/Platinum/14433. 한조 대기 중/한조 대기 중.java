//https://www.acmicpc.net/problem/14433
//2초 256MB / 이하 테케 설명
//5 4 5 3	// 사람수N(1<=300), 영웅수M(1<=300), 각 팀의 팀원들이 원하는 영웅 수 k1, k2(1<=500) 
//1 1	// k1개의 줄에걸쳐 두수 i(1<=N),j(1<=M)가 주어짐, i번 플레이어가 j번을 고르고 싶어한다는 것
//2 2
//3 3
//4 4
//5 2
//1 1		// k2줄에 걸쳐 같은 형식으로 주어짐
//2 1
//3 1
////승급할 수 있으면 ‘네 다음 힐딱이’를, 승급할 수 없으면 ‘그만 알아보자’를 출력한다.
////그만 알아보자
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	
	static final String WIN = "네 다음 힐딱이";
	static final String LOSE = "그만 알아보자";
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M;
	static int match[];
	static boolean visit[];
	static ArrayList<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 사람수N(1<=300)
		M = Integer.parseInt(st.nextToken());// 영웅수M(1<=300)
		// 각 팀의 팀원들이 원하는 영웅 수 k1, k2(1<=500)
		int K1 = Integer.parseInt(st.nextToken());
		int K2 = Integer.parseInt(st.nextToken());

		System.out.print(getMaxMatch(K1) < getMaxMatch(K2) ? WIN : LOSE);
	}
	static int getMaxMatch(int K)throws Exception {
		match = new int[M + 1];
		visit = new boolean[M + 1];
		adList = new ArrayList[N + 1];
		
		for(int j=1; j<=N; j++)
			adList[j] = new ArrayList<>();
		
		while(K-->0)
		{
			st = new StringTokenizer(br.readLine());
			int user = Integer.parseInt(st.nextToken());
			int hero = Integer.parseInt(st.nextToken());
			adList[user].add(hero);
		}
		
		int cnt = 0;
		
		for(int user=1; user<=N; user++)
		{
			Arrays.fill(visit, false);
			if(dfs(user))
				cnt++;
		}
		
		return cnt;
	}
	static boolean dfs(int user) {
		for(int hero : adList[user])
		{
			if(visit[hero])
				continue;
			
			visit[hero] = true;
			
			if(match[hero] == 0 || dfs(match[hero]))
			{
				match[hero] = user;
				return true;
			}
		}
		return false;
	}
}