// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		new Solution().solution(); // 솔루션 함수 실행
	}
}
class Solution {
	
	ArrayList<Integer> list = new ArrayList<>(); // 결과 인덱스를 담을 배열
	boolean visit[]; // DFS실행시 방문 체크할 배열
	int MAX_INDEX = 0; // 그룹당 최소 인덱스를 담을 변수
	int MAX_DIST = 0; // 그룹 안의 하나의 인자당 최대 거리를 담을 변수
	int arr[][]; // 인접 노드를 담을 2차원 배열
	int N, M;
	final int INF = 100;
	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine())+1;
		M = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int i=1; i<N; i++)
			for(int j=1; j<N; j++) {
				if(i==j)continue;
				arr[i][j] = INF;
			}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = arr[b][a] = 1;
		}
		
		// 플로이드 와샬로 모든 정점에서 모든 정점으로 가는 거리를 초기화 시켜 놓는다.
		for(int k=1; k<N; k++) {
			for(int i=1; i<N; i++) {
				if(k==i) continue;
				for(int j=1; j<N; j++) {
					if(i==j || k==j) continue;
					if(arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
		// arr[i][0]인덱스에 i라인의 최댓 값을 저장
		for(int i=1; i<N; i++) {
			int max = 0;
			for(int j=1; j<N; j++) {
				if(arr[i][j] != INF && arr[i][j] > max) {
					max = arr[i][j];
				}
			}
			arr[i][0] = max;
		}

		visit = new boolean[N]; // 방문 체크를 할 배열
		int cnt = 0;
		for(int i=1; i<N; i++) {
			if(!visit[i]) {
				cnt++;
				MAX_INDEX = i; // 인덱스 미리 셋팅
				MAX_DIST = arr[i][0];// 최대 거리 셋팅 
				DFS(i); // 하나라도 연결되어있다면 DFS로 탐색
				list.add(MAX_INDEX);
			}
		}
		Collections.sort(list); // 대표들 오름차순 정렬 
		// 이하 대표 출력
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append('\n');
		for(int i=0; i<list.size(); i++) 
			sb.append(list.get(i)).append('\n');
		System.out.println(sb);
	}
	void DFS(int idx) {
		if(!visit[idx]) {
			visit[idx] = true;
			if(MAX_DIST > arr[idx][0]) {
				MAX_DIST = arr[idx][0];
				MAX_INDEX = idx;
			}
			for(int i=1; i<N; i++) {
				if(arr[idx][i] !=0 && arr[idx][i] != INF) {
					DFS(i);
				}
			}
		}
	}
}