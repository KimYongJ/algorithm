// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Main{
	static class Node{
		int now, cnt;
		Node(int now, int cnt){
			this.now = now;
			this.cnt = cnt;
		}
	}
	static int F, S, G, U, D, next, position[];
	static Node now;
	static boolean visit[];
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
	
	public static void main(String[] args)throws Exception{
		F 			= read(); 				// 총 층수
		S 			= read(); 				// 현재 위치
		G 			= read(); 				// 목표 위치
		U 			= read(); 				// 업버튼시 올라가는 층수
		D 			= read(); 				// 다운버튼시 내려가는 층수
		
		visit 		= new boolean[F+1];
		position 	= new int[] {U,-D}; 	// 오르고 내릴 버튼 셋팅
		q 			= new ArrayDeque<>();
		q.add(new Node(S,0));
		
		while(!q.isEmpty()) {
			now 	= q.poll();
			if(!visit[now.now]) { 			// 방문하지 않은 노드만 진행
				visit[now.now] = true;
				if(now.now == G) { 			// 종료 층에 도달할 경우 출력 후 종료
					System.out.println(now.cnt);
					return;
				}
				for(int p : position) {
					next = now.now + p;		// 다음 층수 
					if(next>0 && next<=F) 	// 층수 유효성 검사
						q.add(new Node(next,now.cnt + 1));
				}
			}
		}
		System.out.println("use the stairs");
	}
}