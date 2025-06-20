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
		reverseMatch = new int[L + 1];
		adList = new ArrayList[L + 1];
		visitLeft = new boolean[L + 1];
		visitRight = new boolean[R + 1];
		
		for(int i=0; i<=L; i++)
			adList[i] = new ArrayList<>();

		for(int l=1; l<=L; l++)
		{
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			while(cnt-->0)
				adList[l].add(Integer.parseInt(st.nextToken()));
		}
		
		int cnt = 0;
		
		for(int l=1; l<=L; l++)
		{
			++time;
			if(bipartite(l))
				++cnt;
		}
		
		for(int l=1; l<=L; l++)
			if(reverseMatch[l] == 0)
				dfs(l);
		
		StringBuilder sb = new StringBuilder();
		sb
		.append(cnt).append('\n')
		.append(getAns(visitLeft, false))
		.append('\n')
		.append(getAns(visitRight, true));
		
		System.out.print(sb);
	}
	static String getAns(boolean visit[], boolean flag) {
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int i=1; i<visit.length; i++)
		{
			if(visit[i] == flag)
			{
				++cnt;
				sb.append(i).append(' ');
			}
		}
		sb.insert(0, ' ');
		sb.insert(0, cnt);
		
		return sb.toString();
	}
	static void dfs(int left) {
		if(visitLeft[left])
			return;
		
		visitLeft[left] = true;
		
		for(int right : adList[left])
		{
			if(visitRight[right] || match[right] == left)
				continue;
			
			visitRight[right] = true;
			
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
				reverseMatch[left] = right;
				return true;
			}
		}
		return false;
	}
}