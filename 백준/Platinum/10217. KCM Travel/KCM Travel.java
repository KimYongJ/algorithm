//https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.Arrays;

class Node{
	int node, cost, time;
	Node(int node, int cost, int time){
		this.node = node;
		this.cost = cost;
		this.time = time;
	}
}

class Main{
	static final int INF = 100_000;
	static int N, M, K;
	static int a, b, c, t;
	static int time[][];
	static ArrayList<Node> edge[];
	
	// 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
    
	public static void main(String[] args)throws Exception{
		read();
		N = read(); // 노드 갯수
		M = read(); // 총 지원 비용
		K = read(); // 티켓 정보 수
		edge = new ArrayList[N+1];
		time = new int[N+1][M+1]; //최단 시간을 저장하며 노드번호와 드는 비용당 최단 시간을 저장
		
		for(int i=0; i<=N; i++) {
			edge[i] = new ArrayList<>();
			Arrays.fill(time[i], INF);
		}
		
		for(int i=0; i<K; i++) {
			a = read(); // 시작 노드
			b = read(); // 종료 노드
			c = read(); // 드는 비용
			t = read(); // 소요 시간
			edge[a].add(new Node(b,c,t)); // 단방향 연결
		}
		time[1][0] = 0; //시작점 초기화
		for(int m=0; m<=M; m++) {
			for(int n=1; n<=N; n++) {
				for(Node now : edge[n]) {
					if(m + now.cost <= M) {
						time[now.node][m+now.cost] = 
								Math.min(
										time[now.node][m+now.cost],
										time[n][m] + now.time
										);
					}
				}
			}
		}
		
		int result=INF;
		for(int i=0;i<=M;i++)
		{
			result=Math.min(result, time[N][i]);
		}
		System.out.println(result==INF? "Poor KCM":result);
	}
}