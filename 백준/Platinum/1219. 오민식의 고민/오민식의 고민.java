// https://github.com/KimYongJ/algorithm
/*
 * 문제 요약 : 시작 노드에서 도착 노드까지 갈 때 최대로 돈을 벌 수 있는것을 찾아야 합니다.
 * 이때 양의 사이클이 있는 경우 Gee를 입력해야 합니다.
 * 사용 알고리즘 : 최대 돈을 구하면서 양의 사이클을 찾을 벨만포드알고리즘과 양의 사이클에 시작점과 종료점이 포함되어있는지 확인할 수 있는
 * BFS알고리즘을 구현합니다.
 * 주의사항 : 벨만포드 알고리즘 진행시 돈의 양이 int형을 넘어갈 수 있으므로 분기 처리 잘해주어야 합니다.
 * */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

class Main{
	static final int MAX_INF = 49_000_001; 	// 사이클이 없을 때 나올 수 있는 최대 가중치
	static final int MIN_INF = -49_000_001;	// 사이클이 없을 때 나올 수 있는 최소 가중치
	static int N, M, START_CITY, END_CITY;
	static ArrayList<Node>[] adList; 		// 인접 노드를 담을 리스트
	static int MONEY[];						// start_city부터 end_city까지 갈 때 얻는 돈을 담을 MONEY배열
	static int PLUS_MONEY[];				// 해당 노드에 도착했을 때 얻는 돈을 담을 배열

    // 빠른 입력을 위해 만든 함수
    public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}

	
	public static boolean BFS(int start, int end) {
        boolean visit[]    = new boolean[N];
		boolean isPossible = false;
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(start);
		LOOP : 
		while(!q.isEmpty()) {
			
			int nowNode = q.poll();
			
			if(!visit[nowNode]) {
				visit[nowNode] = true;// 노드 방문처리 
				
				
				for(int i=0; i<adList[nowNode].size(); i++) {
					int nextNode = adList[nowNode].get(i).adNode;// 해당 노드의 인접노드들을 확인
					
					if(nextNode == end) {// 인접노드가 최종 찾으려는 END노드이면 종료
						isPossible = true;
						break LOOP;
					}
					q.add(nextNode);
				}
				
			}
			
			
		}
		
		return isPossible;
	}
	
	public static String bellman_ford() {	// 벨만 포드 알고리즘 실행
		String str = "1";
		
		MONEY[START_CITY] = PLUS_MONEY[START_CITY]; // 초기 값은 START_CITY로 한다.
		
		for(int i=0; i<N-1; i++) { // 노드갯수 -1만큼 반복
			
			for(int j=0; j<N; j++) {
				for(Node now : adList[j]) { // 인접 노드를 가져온다.
					int start = j;
					int end = now.adNode;
					int time = now.money;
					
					if(MONEY[start] == MIN_INF) continue; // 벨만 포드 특징 , 한번이라도 연산된 것만 이하 연산을 하도록해 특정 노드에 대한 최대 시간을 구할 수 있게 한정함
					
					int newEndMoney = MONEY[start] + time + PLUS_MONEY[end];
					
					if(newEndMoney > MAX_INF)
						newEndMoney = MAX_INF;
					
					if(MONEY[end] < newEndMoney) {
						MONEY[end] = newEndMoney;
					}
					
				}
			}
		}
		// 위에서 MONEY배열에 이미 최대 돈이 다 구해져 있다. 한번더 반복하며 사이클 확인 
		if(MONEY[END_CITY] == MIN_INF) { 							// 도달 불가능할 경우
			str = "gg";
		}else {
			// 도달 가능하지만, 사이클이 있는지 확인
			LOOP : 
			for(int j=0; j<N; j++) {
				for(Node now : adList[j]) { 						// 인접 노드를 가져온다.
					int start = j;
					int end = now.adNode;
					int time = now.money;
					
					if(MONEY[start] == MIN_INF) continue; 			// 벨만 포드 특징 , 한번이라도 연산된 것만 이하 연산을 하도록해 특정 노드에 대한 최대 시간을 구할 수 있게 한정함
					
					int newEndMoney = MONEY[start] + time + PLUS_MONEY[end];
					
//					if(newEndMoney > MAX_INF) 해당 부분은 주석해야 갱신을 알 수 있음
//						newEndMoney = MAX_INF;
					
					if(MONEY[end] < newEndMoney) { 					// 사이클이 있으면 시작노드->end노드->도착 노드까지 연결되어있는지 확인
						boolean is_START_to_NODE= BFS(START_CITY, end);
						boolean is_NODE_TO_END 	= BFS(end, END_CITY);
						if( is_START_to_NODE && is_NODE_TO_END ) { 	// 시작 노드-> end -> 도착 노드 사이클이 있는 경우 Gee 셋팅 후 종료 
							str = "Gee";	
							break LOOP;
						}
					}
					
				}
			}
		}
		
		
		return str;
	}
	
    public static void main(String[] args)throws Exception{ 
        N 				= read(); 							// 노드의 갯수
        START_CITY 		= read(); 							// 시작노드 번호
        END_CITY 		= read(); 							// 도착노드 번호
        M 				= read(); 							// 간선 갯수
        MONEY			= new int[N];						// start_city부터 end_city까지 갈 때 얻는 돈을 담을 MONEY배열
        PLUS_MONEY		= new int[N];						// 노드 도착시 받을 돈을 넣을 배열 초기화
        adList 			= new ArrayList[N];					// 인접 리스트를 담을 배열

        for(int i=0; i<N; i++)   
        	adList[i] 	= new ArrayList<Node>();			// 인접 노드 리스트 초기화
        
        Arrays.fill(MONEY,MIN_INF); 						// MIN_INF로 초기화
        
        for(int i=0; i<M; i++) {
        	int a 		= read();
        	int b 		= read();
        	int d 		= -read();
        	adList[a].add(new Node(b,d)); 					// 단방향 연결
        }
        
        for(int i=0; i<N; i++) 
        	PLUS_MONEY[i] = read();
        
        String str = bellman_ford();						// 벨만 포드 알고리즘 실행
        
        if(str.length()!=1) { 								// 도달할 수 없거나, 무한일 때
        	System.out.println(str);
        }else {
        	System.out.println(MONEY[END_CITY]); 			// 도착지점에서 나올 수 있는 돈 출력
        }
        
    }
}
class Node{
	int adNode, money;
	Node(int adNode, int money){
		this.adNode = adNode;
		this.money 	= money;
	}
}