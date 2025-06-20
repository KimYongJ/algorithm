//https://www.acmicpc.net/problem/2051
//2초 128MB
//5 5 // N, M(1<=N,M<=1,000)
//2 1 2// N개의 줄에 Ai와 연결된 정점의 개수가 주어지고 그 개수만큼 Bj의 j가 주어짐
//1 1
//2 2 3
//3 3 4 5
//1 1
//출력은 첫 줄에 최소 버텍스 커버 크기를 출력하고, 둘째 줄은 A에서 최소 버텍스 커버에 포함되어 있는 정점의 개수를 출력, 포함되어 있는 정점 번호(Ai에서 i)를 출력
//셋째 줄엔 B에서 최소 버텍스 커버에 포함되어 있는 정점의 개수를 출력하고, 포함되어 있는 정점의 번호(Bj에서 j)를 출력
//4
//2 3 4
//2 1 2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int L, R;
	static int time;
	static int visitTime[];
	static int match[];
	static int reverseMatch[];
	static List<Integer> adList[];
	
	static boolean visitLeft[];
	static boolean visitRight[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		visitTime = new int[R + 1];
		match = new int[R + 1];
		reverseMatch = new int[L + 1];// 왼쪽 노드의 미매칭 상황을 알기위해 필요하다.
		adList = new ArrayList[L + 1];
		visitLeft = new boolean[L + 1];
		visitRight = new boolean[R + 1];
		
		for(int i=0; i<=L; i++)
			adList[i] = new ArrayList<>();
		// 간선을 입력 받는다
		for(int l=1; l<=L; l++)
		{
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			while(cnt-->0)
				adList[l].add(Integer.parseInt(st.nextToken()));
		}
		
		int cnt = 0;
		// 왼쪽 정점 기준으로 이분 매칭을 돌려서 크기를 구함과 동시에 매칭된 노드들을 서로 저장한다.
		for(int l=1; l<=L; l++)
		{
			++time;
			if(bipartite(l))
				++cnt;
		}
		// 왼쪽 정점 그룹 기준으로 미매칭 노드를 시작으로 교대 경로를 탐색한다. 왼쪽 정점은 매칭되지 않은 것만, 오른쪽 정점은 매칭된것만 탐색한다.
		// dfs 함수안에서 탐색하는 모든 정점들을 방문 체크하여 어떤 노드를 방문했는지 알 수 있도록 한다.
		for(int l=1; l<=L; l++)
			if(reverseMatch[l] == 0)// 왼쪽 노드 기준, 미매칭 인것들을 기준으로 dfs 탐색
				dfs(l);
		
		StringBuilder sb = new StringBuilder();
		sb
		.append(cnt).append('\n')
		.append(getAns(visitLeft, false))// 왼쪽 정점은 차집합이기 때문에 방문하지 않은 정점을 답으로 출력
		.append('\n')
		.append(getAns(visitRight, true));// 오른쪽 정점은 교집합이기 때문에 방문한 정점을 답으로 출력
		
		System.out.print(sb);
	}
	static String getAns(boolean visit[], boolean flag) {
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int i=1; i<visit.length; i++)
		{
			if(visit[i] == flag)// 전달된 flag와 같을 때만 답으로 출력
			{
				++cnt;
				sb.append(i).append(' ');
			}
		}
		sb.insert(0, ' ');
		sb.insert(0, cnt);// 맨처음 값이 정점의 개수여야 하므로 insert 처리
		
		return sb.toString();
	}
	static void dfs(int left) {
		if(visitLeft[left])// 왼쪽 정점 방문했다면 연산 스킵
			return;
		// 왼쪽 정점 방문 처리
		visitLeft[left] = true;
		// 왼쪽 정점은 미매칭 간선만 탐색, 오른쪽 정점은 매칭만 탐색하도록 하여 비매칭 -> 매칭 -> 비매칭 -> 매칭 으로 교대 간선 탐색하도록 구현
		for(int right : adList[left])
		{
			// 오른쪽 정점을 방문했거나, 오른쪽 정점이 왼쪽 정점과 매칭되있다면 연산 스킵한다.
			if(visitRight[right] || match[right] == left)
				continue;
			// 오른쪽 정점 방문 처리
			visitRight[right] = true;
			// 재귀 탐색
			if(match[right] != 0)
				dfs(match[right]);
		}
	}
	static boolean bipartite(int left) {
		for(int right : adList[left])
		{
			if(visitTime[right] == time)
				continue;
			
			visitTime[right] = time;
			
			if(match[right] == 0 || bipartite(match[right]))
			{
				match[right] = left;
				reverseMatch[left] = right;// 미매칭 노드 파악을 위해 필요하다.
				return true;
			}
		}
		return false;
	}
}