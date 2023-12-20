// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int T, N, result, arr[];
	static boolean visit[];
	static ArrayList<Integer> visit_node_list;
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
		if(visit[node]) { // 노드를 방문했다면 종료
			int idx = visit_node_list.indexOf(node); // 노드의 인덱스 출력시 자기 앞에 몇개의 노드가 있는지 체크 가능
			if(idx==-1) // 리스트 인덱스가 -1이라는 것은 1개를 방문했다는 것이기 때문에 1로 치환 후 더해줌.
				idx = visit_node_list.size();
			result += idx;
			return;
		}
		visit[node] = true; // 방문처리
		visit_node_list.add(node); // 방문 노드 리스트에 추가  
		DFS(arr[node]); // 현재 노드가 선택한 다음 노드 전달. 
	}
	// 메인 함수
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		while(T-->0) {
			result = 0;// 팀이아닌 아이를 담을 결과 초기화 
			N = Integer.parseInt(br.readLine()); // 노드 갯수 입력 받음
			st = new StringTokenizer(br.readLine());
			arr = new int[N+1]; // 노드 갯수에 따른 배열 선언
			visit = new boolean[N+1]; // 노드 방문 체크할 배열
			for(int i=1; i<=N; i++) // 배열에 입력 값 셋팅
				arr[i] = Integer.parseInt(st.nextToken());
			
			for(int i=1; i<=N; i++) { // DFS 실행
				if(!visit[i]) { // 방문하지 않은 노드에 대해 DFS실행 
					visit_node_list = new ArrayList<>();// 방문한 노드를 순서대로 담을 리스트
					DFS(i); // 해당 노드를 전달.
				}
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}
}