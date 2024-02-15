// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


class Main{
	
	static int A, B, N, M, X;
	static int cnt, MAX, MIN;
	static boolean visit[];					// DFS진행시 방문 체크
	static ArrayList<Integer>[] winLink; 	// 이겼을 때 다른 노드로 갈 수 있게 연결한다.	(최솟값 구할 때 사용)
	static ArrayList<Integer>[] failLink;	// 졌을 때 다른 노드로 갈 수 있게 연결한다.	(최댓값 구할 때 사용)
	
	// 주어지는 노드가 arrayList를 통해 몇 군데를 방문할 수 있는가 체크
	public static int DFS(ArrayList<Integer>[] list, int nowNode) 
	{
		if(visit[nowNode]) return 0;
		visit[nowNode] = true;
		
		int cnt = 1; // 자기자신은 방문했기에 1기본 셋팅
		
		for(Integer node : list[nowNode])
			cnt += DFS(list, node);
		
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		winLink = new ArrayList[N+1];
		failLink = new ArrayList[N+1];
		
		// 연결 고리들 값을 넣을 수 있도록 초기화
		for(int i=0; i<=N; i++) 
		{
			winLink[i] = new ArrayList<>();
			failLink[i] = new ArrayList<>();
		}
		
		// 본부로부터 답을 입력 받는다.
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			if(A==X || B== X) // X가 몇번 등장했는지 체크
				cnt++;
			
			winLink[A].add(B);
			failLink[B].add(A);
		}
		
		if(cnt == 0) 
		{ // X가 한번도 등장하지 않았다면 최대 값은 1, 최솟 값은 N이 된다.
			System.out.print(1);
			System.out.print(" ");
			System.out.print(N);
		}else 
		{
			visit = new boolean[N+1];
			MAX = DFS(failLink,X);		// 최댓값을 찾아 MAX에 셋팅
			
			visit = new boolean[N+1];
			MIN = DFS(winLink,X);		// 최솟값을 찾아 MIN에 셋팅
			
			MIN = N - MIN + 1; 			//최솟값 공식 : N - 내가간사람들 + 1(플러스1을 해주는 이유는 DFS에서 나자신도 1을 체크했기 때문에 나자신은 등수에서 더해져야함)
			
			System.out.print(MAX);
			System.out.print(" ");
			System.out.print(MIN);
		}
	}

	
}