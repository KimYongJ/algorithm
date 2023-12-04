// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	static final int MAX_INF = 49_000_001;
	static final int MIN_INF = -49_000_001;
	static int N, M, START, END;
	static int PLUS_MONEY[];		// 각 노드 도착시마다 받을 돈
	static int dist[];				// START노드부터 모든 노드로의 돈을 담을 배열
	static boolean visit[];			// BFS를 돌 때 사용할 방문 체크 배열
	static ArrayList<Node>[] adList;// 인접 리스트
	
	public static boolean BFS_END_TO_END(int start,int end) {// start노드에서 end까지 갈 수 있는지 체크
		boolean isPossible = false;
		boolean visit2[] = new boolean[N];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(start);
		LOOP:
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(!visit2[now]) {
				visit2[now] = true; // 노드 방문처리 
				
				for(int i=0; i<adList[now].size(); i++) {
					int nextNode = adList[now].get(i).end;// 해당 노드의 인접노드들을 확인
					if(nextNode == end) { // 인접노드가 최종 찾으려는 END노드이면 종료
						isPossible = true;
						break LOOP;
					}
					q.add(nextNode);
				}
			}
		}
		
		return isPossible;
	}
	public static String bellman_ford() { // 최대 수익을 찾을 벨만 포드 알고리즘 
		String str = "1";
		
		dist[START] = PLUS_MONEY[START];// 초기 값은 START도시에 도착했을 때 얻는 금액으로 셋팅
		
		for(int i=0; i<N-1; i++) {
			
			for(int j=0; j<N; j++) {
				for(Node now : adList[j]) {
					int start 	= j;
					int end 	= now.end;
					int money 	= now.money + PLUS_MONEY[end];
					
					if(dist[start] == MIN_INF) continue; // 벨만 포드 알고리즘 특징, 한번이라도 초기화 된 것만 구한다.
					
					int newEndMoney = dist[start] + money;
					if(newEndMoney > MAX_INF) {
						newEndMoney = MAX_INF;
					}
					if(dist[end] < newEndMoney) {
						dist[end] = newEndMoney;
					}
					
				}
			}
		}
		
		
		if(dist[END] == MIN_INF) { // 도착 도시에 가는것이 불가능한 경우
			str = "gg";
		}else {
			// 사이클 확인 로직
			LOOP:
			for(int j=0; j<N; j++) {
				for(Node now : adList[j]) {
					int start 	= j;
					int end 	= now.end;
					int money 	= now.money + PLUS_MONEY[end];
					
					if(dist[START] == MIN_INF) continue; // 벨만 포드 알고리즘 특징, 한번이라도 초기화 된 것만 구한다.
					
					int newEndMoney = dist[start] + money;
					
					if(dist[end] < newEndMoney) {// 값이 갱신되면 사이클이 있다는 소리
						if(!visit[end]) { // 해당 노드를 한번도 BFS체크하지 않았을 경우 이하 실행
							visit[end] = true;
							// 전달한 첫번째 인자가 두번째 인자까지 갈 수 있는지 체크, 무한 사이클이여야 Gee를 입력하기 때문에 end가 사이클이란 것을 알았으면, START에서 end까지, end에서 END까지 갈 수 있어야 한다.
							if( BFS_END_TO_END(START, end) && BFS_END_TO_END(end,END) ) {
								str = "Gee";
								break LOOP;
							}
						}
					}
					
				}
			}
		}
		return str;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 			= Integer.parseInt(st.nextToken());
		START		= Integer.parseInt(st.nextToken());
		END 		= Integer.parseInt(st.nextToken());
		M 			= Integer.parseInt(st.nextToken());
		dist		= new int[N];		// 시작 노드에서 모든 노드까지 벌 수 있는 최대 금액을 담을 dist배열
		PLUS_MONEY 	= new int[N];		// 도시마다 도착시 받을 금액
		visit		= new boolean[N];	// 사이클이 있을 때, 특정 노드가 END까지 도달할 수 있는지 확인하는데 이 때 사용할 방문배열
		adList		= new ArrayList[N]; // 인접리스트 초기화 
		for(int i=0; i<N; i++) {		// 인접리스트 초기화 및 dist 초기화 
			adList[i] = new ArrayList<Node>();
			Arrays.fill(dist, MIN_INF); // 최대를 구해야 하므로 최저 값으로 초기화
		}
		
		for(int i=0; i<M; i++) {
			st 		= new StringTokenizer(br.readLine());
			int a 	= Integer.parseInt(st.nextToken());
			int b 	= Integer.parseInt(st.nextToken());
			int d 	= -Integer.parseInt(st.nextToken()); // 비용은 음수로 받음
			adList[a].add(new Node(b,d)); // 단방향 저장
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) // 도시마다 도착시 받을 돈 셋팅
			PLUS_MONEY[i] = Integer.parseInt(st.nextToken());
		
		String result = bellman_ford();
		
		if(result.length() != 1) {
			System.out.println(result);// Gee 이거나, gg일 경우
		}else {
			System.out.println(dist[END]);
		}
		
	}
}
class Node{
	int end, money;
	Node(int end, int money){
		this.end 	= end;
		this.money 	= money;
	}
}



//int read() throws Exception{ 			// 빠른 입력을 위한 함수
//	int c, n = System.in.read() & 15;
//	boolean negative = n == 13;
//	if(negative) n = System.in.read() & 15;
//	while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
//	if(c == 13) System.in.read();
//	return negative?~n+1:n;
//}