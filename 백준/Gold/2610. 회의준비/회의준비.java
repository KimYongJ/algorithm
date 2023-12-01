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
	boolean visit[]; 						// DFS실행시 방문 체크할 배열

	int CNT = 0; 							// 구성되는 위원회 수
	int arr[][]; 							// 인접 노드를 담을 2차원 배열
	int N, M; 								// 최초 입력받을 노드갯수와 연결 간선 수
	int MAX_INDEX; 							// 그룹당 최소 인덱스를 담을 변수
	int MAX_DIST; 							// 그룹 안의 하나의 인자당 최대 거리를 담을 변수
	final int INF = 100; 					// 최대 노드 연결 값 +1
	
	int read() throws Exception{ 			// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
	void print_result() { 					// 결과 출력 함수
		
		Collections.sort(list); 			// 대표들 오름차순 정렬 
		// 이하 대표 출력
		StringBuilder sb = new StringBuilder();
		
		sb.append(CNT).append('\n'); 		// 위원회 숫자 카운팅
		
		for(int i=0; i<list.size(); i++) 
			sb.append(list.get(i)).append('\n');// 위원회 대표 출력
		
		System.out.println(sb);
	}
	
	void DFS(int idx) { 					// DFS 함수 , 인덱스를 인자로 받는다.
		if(!visit[idx]) { 					// 방문한 인자가 방문하지 않았어야 DFS 실행
			visit[idx] = true; // 방문처리
			if(MAX_DIST > arr[idx][0]) { 	// 최대 방문거리가 더 작은 것 발견시 값 갱신
				MAX_DIST = arr[idx][0];		// 더 작은 최대 방문 거리로 갱신
				MAX_INDEX = idx; 			// 더 작은 최대 방문 거리를 갖는 인덱스 갱신
			}
			for(int i=1; i<N; i++) { 		// 인접 노드 탐색 
				if(arr[idx][i] != INF) 		// 인접노드가 INF아니면 해당 노드를 갖고 DFS시작
					DFS(i);
			}
		}
	}
	
	
	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine())+1;
		M 		= Integer.parseInt(br.readLine());
		arr 	= new int[N][N];
		visit 	= new boolean[N]; // DFS실행시 방문 체크 할 배열
		
		for(int i=1; i<N; i++)
			for(int j=1; j<N; j++) {
				if(i==j)continue;
				arr[i][j] = INF;
			}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = arr[b][a] = 1; // 양방향 입력
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

		
		for(int i=1; i<N; i++) {
			if(!visit[i]) {
				CNT++;					// 위원회 구성수 +1
				MAX_INDEX = i; 			// 인덱스 미리 셋팅
				MAX_DIST = arr[i][0];	// 최대 거리 셋팅 
				DFS(i); 				// 하나라도 연결되어있다면 DFS로 탐색
				list.add(MAX_INDEX);
			}
		}
		
		print_result(); // 결과 출력
	}

}