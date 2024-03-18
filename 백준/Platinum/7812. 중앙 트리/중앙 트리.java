// https://github.com/KimYongJ/algorithm

import java.util.ArrayList;

class Point{
	int node, dist;
	Point(int node, int dist){this.node=node; this.dist=dist;}
}
class Main
{	
	static long min, sum[];
	static int N, childNode[];
	static boolean visit[];
	static ArrayList<Point>[] adlist;
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void childNode_and_sum_setting(int now) {
		childNode[now] = 1;		// 자기 자식 노드 기본 값 세팅
		for(Point next : adlist[now]) 
		{
			if(!visit[next.node]) {
				visit[next.node] = true;		// 방문 체크
				childNode_and_sum_setting(next.node);
				childNode[now] += childNode[next.node];		// now가 루트일 때 now노드의 자식 노드 개수 누적합, now노드의 자식노드도 자기의 자식노드의 누적합을 구함
				sum[now] += sum[next.node] + childNode[next.node] * next.dist;// 자기 인접노드까지의 총 거리합과 (now를 부모로하는 자식노드의 개수 * 거리)를 진행해 now의 누적합을 구한다.
			}
		}
	}
	public static void getResult(int now, long total) 
	{
		if(min > total)
			min = total;		// 기존 구한 합보다 작다면 최소값으로 세팅
		for(Point next : adlist[now]) {
			if(!visit[next.node]) {
				visit[next.node]= true; 
				// now노드에서 next노드로 가기 때문에, 기존에 연결되어있떤 now - next 노드간 연결을 끊고( 코드 :  - (childNode[next.node]*next.dist) )
				// now노드를 거쳐서 이제 next로 가는 노드의 갯수를 알야 하기 때문에 다음과 같은 코드를 쓴다. ->  +(N-childNode[next.node])*next.dist
				getResult(next.node, total - (childNode[next.node]*next.dist) + (N-childNode[next.node])*next.dist   );
			}
		}
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb 	= new StringBuilder();
		int a,b,c;
		while(true)
		{
			N			= read();
			min 		= Long.MAX_VALUE;
			sum 		= new long[N];
			childNode 	= new int[N];
			adlist		= new ArrayList[N];
			if(N == 0) 
				break;
			
			for(int i=0; i<N; i++)
				adlist[i] = new ArrayList<>();
			
			for(int i=0; i<N-1; i++) 
			{
				a 	= read();
				b 	= read();
				c	= read();
				adlist[a].add(new Point(b,c));	// a에서 b로 c만큼 소요(양방향)
				adlist[b].add(new Point(a,c));	// b에서 a로 c만크 소요(양방향)
			}
			visit = new boolean[N];
			visit[0] = true;					// 0 부터 시작이기 때문에 방문체크 
			childNode_and_sum_setting(0);
			visit = new boolean[N];
			visit[0] = true;					// 0 부터 시작이기 때문에 방문체크
			getResult(0, sum[0]); 				// 0번노드부터 시작해 탐색시작, 누적합 sum[0]을 먼저 전달함
			sb.append(min).append('\n');
		}
		System.out.println(sb);
	}
}

