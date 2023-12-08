// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Main{
	public static void main(String[] args)throws Exception{
		new Solution().solution();
	}
}
class Solution{

	int K, W, H, result = -1; 						// 나이트이동 숫자, 가로, 세로, 최종 값을 담을 result변수
	int w,h, newDist, newW, newH;
	int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}}; 		// 상하좌우 이동 좌표 
	int hor[][] = {{-1,-2},{-1,2},{-2,-1},{-2,1},{1,-2},{1,2},{2,-1},{2,1}};// 나이트 이동좌표 
	boolean visit_h[][][]; // 말이 방문한 곳
	
	ArrayDeque<Node> q = new ArrayDeque<Node>(); 	// BFS 진행할 큐 선언
	void solution() throws Exception{

		K = read(); 								// 말 이동 가능 갯수
		W = read();									// 가로
		H = read();									// 세로 
		
		visit_h = new boolean[K+1][W][H]; 			// 말의 이동 횟수당 좌표를 DP식으로 풀음
		
		for(int i=0; i<H; i++)
			for(int j=0; j<W; j++) {
				int num = read();
				if(num==1) {						// num이 1인 경우만(접근 불가일 경우) 배열에 마킹
					for(int k=0; k<K+1; k++)
						visit_h[k][j][i] = true; 	// num이 1이면 말이건, 원숭이건 방문불가이기 때문에 true저장
				}
			}
		

		// BFS 시작
		q.add(new Node(0,0,0,0)); 					// 가로, 세로, 거리, 말점프를 한숫자.
		while(!q.isEmpty()) {
			Node now = q.poll(); 					// 큐에서 데이터를 꺼낸다. 
			w = now.w; 								// 현재 가로 좌표
			h = now.h; 								// 현재 세로 좌표
			newDist = now.dist+1; 					// 다음 거리
			
			if(w==W-1 && h==H-1) { 					// 종료 조건
				result = newDist-1; 				// 현재거리를 result에 저장하고 종료
				break;
			}
			
			make_position(dxy,now.K_cnt); 			// 새로운 좌표를 생성하고, 유효성 검증 뒤 큐에 새로운 정보를 넣는 함수

			if(now.K_cnt < K)
				make_position(hor,now.K_cnt+1);		// 새로운 좌표를 생성하고, 유효성 검증 뒤 큐에 새로운 정보를 넣는 함수
		}
		
		System.out.println(result);
	}
	
	// 새로운 좌표를 생성하고, 유효성 검증 뒤 큐에 새로운 정보를 넣는 함수
	void make_position(int[][] position_list, int K_cnt ) {
		for(int xy[] : position_list) { 			// 전달 받은 좌표 리스트로 추가할 새로운 좌표를 생성한다. 
			newW = w + xy[0];
			newH = h + xy[1];
			if(newW>=0 && newH>=0 && newW<W && newH<H && !visit_h[K_cnt][newW][newH]) { 
				visit_h[K_cnt][newW][newH] = true; 	// 유효성 통과 후 해당 부분 방문 처리
				q.add(new Node(newW, newH, newDist, K_cnt));// 그 후 큐에 데이터를 담는다.
			}
		}
	}
	
	// 빠른 입력을 위한 함수
	int read() throws Exception{ 					
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
}
class Node{
	int w, h, dist, K_cnt;
	Node(int w, int h, int dist, int K_cnt){
		this.w = w;
		this.h = h;
		this.dist = dist;
		this.K_cnt = K_cnt;
	}
}

