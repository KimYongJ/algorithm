// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	static class Node{
		int now, cnt;
		Node(int now, int cnt){
			this.now = now;
			this.cnt = cnt;
		}
	}
	static int F, S, G, U, D;
	static int position[];
	static boolean visit[];
	static ArrayDeque<Node> q;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken()); // 총 층수
		S = Integer.parseInt(st.nextToken()); // 현재 위치
		G = Integer.parseInt(st.nextToken()); // 목표 위치
		U = Integer.parseInt(st.nextToken()); // 업버튼시 올라가는 층수
		D = Integer.parseInt(st.nextToken()); // 다운버튼시 내려가는 층수
		
		visit = new boolean[F+1];
		position = new int[] {U,-D};
		q = new ArrayDeque<>();
		q.add(new Node(S,0));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			if(!visit[now.now]) { // 방문하지 않은 노드만 진행
				visit[now.now] = true;
				if(now.now == G) { // 종료 층에 도달할 경우 출력 후 종료
					System.out.println(now.cnt);
					return;
				}
				for(int p : position) {
					int next = now.now + p;
					if(next>0 && next<=F) {
						q.add(new Node(next,now.cnt + 1));
					}
				}
			}
		}
		
		System.out.println("use the stairs");
	}
}