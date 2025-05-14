//https://www.acmicpc.net/problem/17481
// 2초 / 256MB / 이하 테케 설명
//6 6					// 친구수 N(2<=1,000), 그룹 멤버 수 M(2<=1,000)
//MIYEON				// M줄에 걸쳐 멤버의 이름이 최대길이 100글자로 영어대문자로 주어짐
//MINNIE
//SOOJIN
//SOYEON
//YUQI
//SHUHUA
//2 YUQI SOOJIN		// N줄에 걸쳐 친구 별로 좋아하는 멤버 수 K(1<=M)와 해당 그룹의 멤버 이름이 주어짐
//1 SOYEON
//1 YUQI
//2 YUQI SHUHUA
//3 MIYEON SOYEON YUQI
//3 MIYEON SHUHUA SOYEON
////답, 각 매칭이 모두 가능한지 여부를 가능하면YES출력, 불가하면 NO출력 후 다음 줄에 최대한 겹치지 않게 매칭할 수 있는 최대 수를 출력
//NO
//5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
class Main{
	
	static int N, M;
	static int match[];
	static boolean visit[];
	static ArrayList<Integer>[] adList;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		match = new int[M + 1];
		visit = new boolean[M + 1];
		adList = new ArrayList[N + 1];
		
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=1; i<=M; i++)
			map.put(br.readLine(), i);
		
		for(int i=1; i<=N; i++)
		{
			adList[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			while(k-->0)
				adList[i].add(map.get(st.nextToken()));
		}
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			Arrays.fill(visit,false);
			if(dfs(i))
				++cnt;
		}
		
		System.out.print(cnt == N ? "YES" : ("NO\n" + cnt));
	}
	static boolean dfs(int person)
	{
		for(int member : adList[person])
		{
			if(visit[member])
				continue;
			
			visit[member] = true;
			if(match[member] == 0 || dfs(match[member]))
			{
				match[member] = person;
				return true;
			}
		}

		return false;
	}
}