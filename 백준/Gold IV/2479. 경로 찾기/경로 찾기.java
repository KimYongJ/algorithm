// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{
	
	static int N, K, startIdx, endIdx; // 길이가 K인 N개의 2진수가 주어짐
	static int prev[], arr[][];
	static boolean visit[];
	static ArrayList<Integer> list[];  // 인접 노드를 담을 리스트
	static ArrayDeque<Integer> q;
	static StringBuilder sb = new StringBuilder();
	public static void DFS(int idx) {
		sb.append(idx).append(' ');
		if(idx==endIdx) return;
		DFS(prev[idx]);
	}
	public static void print(int start) {
		DFS(start);
		System.out.println(sb);
	}
	// 거리가 1이면 true 아니면 false
	public static boolean isHamming(int a, int b) {
		int cnt = 0;
		for(int i=0; i<K; i++)
			if(arr[a][i] != arr[b][i]) {
				++cnt;
				if(cnt > 1) return false;
			}
				
		return cnt == 1;
	}
	public static void main(String[] args)throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		prev = new int[N+1];
		arr = new int[N+1][K];
		visit = new boolean[N+1];
		list = new ArrayList[N+1]; // 노드들의 연결을 담을 list
		
		q = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>(); // 리스트 초기화
			String str = br.readLine();
			for(int j=0; j<K; j++)
				arr[i][j] = str.charAt(j)-'0'; // 2진수를 입력 받는다.
		}
		
		// 해밍 거리 노드들을 담아 놓는다.
		for(int i=1; i<=N-1; i++) {
			for(int j=i+1; j<=N; j++) {
				if( isHamming(i,j) ) {
					list[i].add(j); // 양쪽으로 갈 수 있기 때문에 양방향 맵핑
					list[j].add(i); // 양쪽으로 갈 수 있기 때문에 양방향 맵핑
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		startIdx = Integer.parseInt(st.nextToken());
		endIdx = Integer.parseInt(st.nextToken());

		q.add(endIdx);
		visit[endIdx] = true;
		while(!q.isEmpty()) {
			int now = q.poll();

			if(now == startIdx) {
				print(now);
				return;
			}
			for(Integer node : list[now]) {
				if(visit[node]) continue; // 방문한곳은 스킵
				visit[node] = true; // 방문처리
				prev[node] = now;
				q.add(node);
			}
		}
		
		System.out.println(-1);
	}
}