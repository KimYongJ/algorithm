// https://github.com/KimYongJ/algorithm
import java.util.HashMap;

class Main{
	
	static int T, N, cnt, result, arr[];
	static boolean visit[];
	static HashMap<Integer,Integer> visit_node_list;
	static StringBuilder sb;
	
	// 빠른 입력을 위한 함수
	public static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
	// DFS 함수
	public static void DFS(int node) {
		if(visit[node]) { 								// 노드를 방문했다면 종료
			int idx = 0;
			if(visit_node_list.containsKey(node)) { 	// 해당 노드를 방문한적 있다면 몇번째 방문이였는지 가져옴
				idx = visit_node_list.get(node);
			}else {
				idx = visit_node_list.size(); 			// 방문한적 없다면 방문했던 노드들의 총 갯수를 반환
			}
			result += idx;
			return;
		}
		visit[node] = true; 							// 방문처리
		visit_node_list.put(node,cnt++);				// 방문 노드 맵에 추가, 이 때 몇번째 방문인지도 체크  
		DFS(arr[node]); 								// 현재 노드가 선택한 다음 노드 전달. 
	}
	// 메인 함수
	public static void main(String[] args)throws Exception{
		sb 				= new StringBuilder();
		T 				= read();
		while(T-->0) 
		{
			result 		= 0;							// 팀이아닌 아이를 담을 결과 초기화 
			N 			= read(); 						// 노드 갯수 입력 받음
			arr 		= new int[N+1]; 				// 노드 갯수에 따른 배열 선언
			visit 		= new boolean[N+1]; 			// 노드 방문 체크할 배열
			for(int i=1; i<=N; i++) 					// 배열에 입력 값 셋팅
				arr[i] 	= read();
			
			for(int i=1; i<=N; i++) 
			{ // DFS 실행
				if(!visit[i]) 
				{ // 방문하지 않은 노드에 대해 DFS실행 
					cnt = 0; // 방문한 노드를 카운팅
					visit_node_list = new HashMap<>();// 방문한 노드를 순서대로 담을 리스트
					DFS(i); // 해당 노드를 전달.
				}
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}
}