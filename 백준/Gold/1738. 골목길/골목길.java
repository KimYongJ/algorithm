// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


class Main{
	public static void  main(String[] args)throws Exception{
		new Solution().solution();
	}
}
class Solution{
	final int INF = 99_001;
	final int MIN_INF = -99_001;
	int N, M, dist[];
	int beforeAndAfter[][]; // 인덱스를 기반으로 [i][0]은 전노드, [i][1]은 후노드, i는 자기자신
	boolean visit[]; // 양의 사이클 판별시 사용, [i] 노드에서 N까지가는 경로가 가능한지 체크
	
	ArrayList<Node>[] node;
	
	ArrayList<Integer> result = new ArrayList<>();
	void solution()throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken()); // 2이상 100 이하 
		M = Integer.parseInt(st.nextToken()); // 1이상 2만 이하
		 
		node = new ArrayList[N+1];
		dist = new int[N+1];
		visit = new boolean[N+1];
		beforeAndAfter = new int[N+1][2]; //[i][0]은 전노드 [i][1]은 후노드, i는 본인
		
		for(int i=1; i<N+1; i++) {
			node[i] = new ArrayList<Node>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken()); // 0이상 1000이하
			node[start].add(new Node(end,dist)); // 일방 통행
		} 
		// 시작 1 -> 도착 N , 최대거리 구하는 벨만포드
		bellman_ford();
		
		if(dist[1]>0 || dist[N] == MIN_INF) { // 양의 사이클이 있다면 dist[1]에 양수가 들어감 + N에 도달할 수 없을 경우 dist[N]에 min_inf
			sb.append(-1);
		}else {
			DFS(N);
			for(int i=result.size()-1; i>=0; i--) {
				sb.append(result.get(i)).append(' ');
			}
		}
		System.out.println(sb);
	}
	void DFS(int idx) {
		if(idx == 1) {
			result.add(idx);
			return;
		}
		result.add(idx);
		DFS(beforeAndAfter[idx][0]); // 전노드 전달
	}
	void bellman_ford() {
		
		Arrays.fill(dist, MIN_INF); // 최장거리를 구하기 위해 셋팅
		dist[1] = 0;
		
		for(int i=0; i<N-1; i++) {// n-1번까지 반복하여 양의 사이클이 없다면 최단거리를 만들어 놓음 
			
			for(int j=1; j<N+1; j++) {
				for(Node now : node[j]) {
					int start = j; // 시작점
					int end = now.end;// 도착점
					int distance = now.dist;// 시작에서 도착까지의 거리
	
					if(dist[start] == MIN_INF) continue; // 벨만 포드 특징 한번이라도 dist에 연산된 것이 있어야 이하 연산시작(특정 노드에 대한 거리를 구하는 것이기 때문) 
					
					int distSum = dist[start] + distance;
					
					if(dist[end] < distSum) { // 최장거리를 구하기 위한 셋팅
						dist[end] = distSum;
						beforeAndAfter[start][1] = end; // start노드의 후 노드에 end를 저장
						beforeAndAfter[end][0] = start; // end노드의 전 노드에 start를 저장
					}
				}
			}
		}
		
		if(dist[N] == MIN_INF) // N에 도달할 수 없을 경우 이하 연산 스킵 
			return;
		
		for(int j=1; j<N+1; j++) {
			for(Node now : node[j]) {
				int start = j; // 시작점
				int end = now.end;// 도착점
				int distance = now.dist;// 시작에서 도착까지의 거리

				if(dist[start] == MIN_INF) continue; // 벨만 포드 특징 한번이라도 dist에 연산된 것이 있어야 이하 연산시작(특정 노드에 대한 거리를 구하는 것이기 때문) 
				
				int distSum = dist[start] + distance;
				
				if(dist[end] < distSum) { // 양의 사이클이 있을 경우 
					if(!visit[end]) {						
						if( BFS_END_TO_N(end) ) { // end노드까지 사이클이있는데 end노드에서 N까지 도달 가능하면 양의 사이클이 있는 것이다. 
							dist[1] = INF;
							return;
						}
						
					}
				}
			}
		}
	}
	boolean BFS_END_TO_N(int start) { // 사이클이 있는 경우, 전달된 노드가 N까지 갈 수 있다면 양의 사이클이 있으므로 -1출력해야함
		boolean goToEnd = false; // start 노드가 N까지 갈 수 있는지 체크 
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(start);
		LOOP : 
		while(!q.isEmpty()) {
			int nowNode = q.poll();
			if(!visit[nowNode]) {
				visit[nowNode] = true;
				for(int i=0; i<node[nowNode].size(); i++) {
					int nextNode = node[nowNode].get(i).end;
					if(nextNode == N) {
						goToEnd = true;
						break LOOP;
					}
					q.add(nextNode);
				}
			}
		}
		return goToEnd;
	}
}

class Node{
	int  end, dist;
	Node(int end, int dist){
		this.end = end;
		this.dist = dist;
	}
}