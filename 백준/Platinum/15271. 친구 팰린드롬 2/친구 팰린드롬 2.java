//https://www.acmicpc.net/problem/15271
//2초 / 512MB / 이하 테케 설명
// 여자는 출석 번호 홀수, 남자는 짝수, 남녀 및 친한 친구 일 때만 올려보냄 
//3 3	// 사람수(2<=200), 관계도 수(0<=(N^2 - N)/2)
//1 2	// 친한 친구 관계가 주어짐
//2 3
//3 1
//답 : 3
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	
	static int N, M;
	static int match[];
	static boolean visit[];
	static ArrayList<Integer>[] adList;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 사람수(2<=200)
		M = Integer.parseInt(st.nextToken());// 관계도 수(0<=(N^2 - N)/2)
		match = new int[N + 1];
		visit = new boolean[N + 1];
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a % 2 != b % 2) // 홀-짝 만 매칭되도록함
			{
				adList[a].add(b);
				adList[b].add(a);
			}
		}
	
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			if(match[i] != 0)
				continue;
			
			Arrays.fill(visit, false);
			
			if(dfs(i))
				cnt += 2;
		}
		
		System.out.print(Math.min(cnt + 1, N));
	}
	static boolean dfs(int cur) {
		if(visit[cur])	// 이미 방문한 노드면 실패
			return false;
		
		visit[cur] = true;
		
		for(int next : adList[cur])
		{
			if(match[next] == 0 || dfs(match[next]))
			{
				match[next] = cur;
				match[cur] = next;
				visit[next] = true;
				visit[cur] = true;
				return true;
			}
		}
		
		return false;
	}
}