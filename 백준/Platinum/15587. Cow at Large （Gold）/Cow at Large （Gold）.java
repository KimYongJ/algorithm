//https://www.acmicpc.net/problem/15587
//2초 512MB
//7 1 // 헛간 개수(2<=10^5), 베시의 시작노드 위치
//1 2 // 트리 노드 정보
//1 3
//3 4
//3 5
//4 6
//5 7
//베시를 잡기 위해 필요한 최소 농부수 : 3
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int cnt;
	static int N, S;
	static int distFromStart[];
	static int distToNearestLeaf[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		distFromStart = new int[N + 1];
		distToNearestLeaf = new int[N + 1];
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
		{
			adList[i] = new ArrayList<>();
			distToNearestLeaf[i] = 1<<30;
		}
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		mark(S, -1, 1);
		
		dfs(S, -1);
		
		System.out.print(cnt);
	}
	
	static void dfs(int now, int prev) {
		if(distFromStart[now] >= distToNearestLeaf[now])
		{
			++cnt;
			return;
		}
		
		for(int next : adList[now])
			if(next != prev)
				dfs(next, now);
	}
	
	static int mark(int now, int prev, int depth) {
		int min = 1<<30;// 리프노드로부터 현재노드까지의 거리
		
		distFromStart[now] = depth;// 시작 노드로부터의 현재까지 거리
		
		for(int next : adList[now])
		{
			if(next == prev)
				continue;
			
			int l = mark(next, now, depth + 1);
			
			min = Math.min(min,l);// 자기 자식 노드 중 리프노드까지 거리가 가장 짧은 것을 가져옴
		}
		
		if(adList[now].size() <= 1) // 현재 노드가 리프노드일 경우 min을 0으로 세팅해서 추후 1이되도록함, 즉 리프노드일 경우 1저장됨
			min = 0;
		
		min += 1; // 현재 노드는 이전 노드들의 거리보다 + 1이 되어야 함
		
		distToNearestLeaf[now] = min;// 리프노드부터 현재노드까지 거리 세팅
		
		return min;// 값 반환
	}
}