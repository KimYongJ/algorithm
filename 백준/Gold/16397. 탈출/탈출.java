// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Main{
	static class Node{
		int num, t; // 순서 : 현재 번호, 버튼 누른 횟수
		Node(int num, int t){
			this.num 	= num;
			this.t 		= t;
		}
	}
	static int N, T, G, A, B, nextT; // 순서 : 시작 숫자, 버튼누르는 횟수, 종료 숫자, A버튼 누른 후 숫자, B버튼 누른 후 숫자 , 버튼 누른 횟수
	static boolean visit[];
	static Node now;
	static ArrayDeque<Node> q;
	
	// 빠른 입력을 위한 함수 
	public static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	// A버튼 클릭 함수
	public static int A_click(int num) {
		return ++num > 99_999 ? -1 : num; 		// num이 임계치 초과일 경우
	}
	// B버튼 클릭 함수
	public static int B_click(int num) {
		num *= 2; 								// 시작부터 곱하기 2
		if(num > 99_999 || num==0) return -1; 	// 임계치를 넘거나 0이면 -1 출력
		int type = 10_000; 						// 가장 높은 자릿수 확인을 위한 타입
		while(num / type == 0)					// 몫이 0이 아니면 탈출
			type /= 10;
		return num - type; 						// 해당 자릿수를 1빼준다.
	}
	public static void main(String[] args)throws Exception{
		N 			= read(); 					// 시작 숫자
		T 			= read(); 					// 버튼 누르는 횟수
		G 			= read(); 					// 종료 숫자
		visit 		= new boolean[100_000];
		q 			= new ArrayDeque<Node>();
		q.add(new Node(N,0));
		/******************이하 BFS********************/
		while(!q.isEmpty()) {
			Node now = q.poll();
			if(!visit[now.num]) { 				// 방문하지 않을 때만 실행 
				visit[now.num] = true;			// 방문 처리
				if(now.num == G) { 				// 종료 조건
					System.out.print(now.t); 	// 버튼 누른 숫자 출력 후 종료 
					return;
				}
				nextT = now.t + 1;
				if(nextT > T) continue; 		// T를 초과해서 누를 경우 연산 불가 
				A = A_click(now.num); 			// A버튼 클릭 
				if(A!=-1)						// A버튼 클릭 후 숫자가 유효할 경우 큐에 삽입
					q.add(new Node(A, nextT));
				B = B_click(now.num);
				if(B!=-1)						// B버튼 클릭 
					q.add(new Node(B, nextT));	// B버튼 클릭 후 숫자가 유효할 경우 큐에 삽입
			}
		}
		System.out.print("ANG"); 				// 여기까지 왔다는 것은 탈출 불가라는 것
	}
}