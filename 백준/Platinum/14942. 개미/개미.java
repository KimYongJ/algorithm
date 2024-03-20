// https://github.com/KimYongJ/algorithm

import java.util.ArrayList;


class Node{
	int node, dist;
	Node(int node, int dist){
		this.node = node;
		this.dist = dist;
	}
}
class Main{
	
	static int N, MAX, energy[], parent[][], parentDist[][];
	static boolean visit[];
	static ArrayList<Node>[] adlist;
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void DFS(int now) {
		for(Node next : adlist[now]) 
		{
			if(!visit[next.node]) 
			{
				visit[next.node]		= true;		// 방문처리
				// parent는 부모노드를 저장하는 배열이다. [노드번호][0]은 현재 노드의 바로 위 부모노드, [노드번호][1]은 2단계 위 부모노드, [2]는 4단계 위 부모노드 [3]은 8단계 위 부모노드
				parent[next.node][0] 	= now;
				// parentDist는 부모노드로의 거리를 저장하는 배열이다. [노드번호][0]은 현재 노드의 바로위 부모노드까지 거리, [노드번호][1]은 2단계 위 부모노드까지 거리 [2]는 4단계 위 부모까지거리 [3]은 8단계 위 부모노드까지 거리
				parentDist[next.node][0]= next.dist;
				DFS(next.node);
			}
			
		}
	}
	public static void setting() {
		for(int i=1; i<MAX; i++)
		{
			for(int node=1; node<=N; node++)
			{
				// 현재 node의 부모노드의 부모노드들을 세팅, i는 2^i번째 부모노드이다. i가 0이면 바로 위 부모노드, i가 1이면 2번째 위 부모노드, i가 2이면 4번째 3이면 8번째 부모노드
				parent[node][i] 	= parent[      parent[node][i-1]      ][i-1];
				// 현재 node에서 2^i 단계 위의 부모노드(parentDist[node][i])까지의 거리를 계산한다.
				// node에서 2^(i-1) 단계 위의 부모노드까지의 거리(parentDist[node][i-1]) 
				//	+ 그 부모노드에서 다시 2^(i-1) 단계 위의 부모노드까지의 거리(parentDist[  parent[node][i-1]  ][i-1])
				parentDist[node][i] = parentDist[  parent[node][i-1]  ][i-1] + parentDist[node][i-1];
			}
			
		}
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N 			= read();
		MAX			= (int)Math.ceil( Math.log(N) / Math.log(2));
		energy 		= new int[N+1];
		parent 		= new int[N+1][MAX];
		parentDist 	= new int[N+1][MAX];
		visit 		= new boolean[N+1];
		adlist 		= new ArrayList[N+1];
		for(int i=0; i<N; i++)	// 에너지를 입력 받는다.
		{
			energy[i+1]	= read();
			adlist[i+1] = new ArrayList<>();
		}
		
		int a,b,c;
		for(int i=1; i<N; i++) 
		{
			a 	= read();
			b 	= read();
			c 	= read();
			adlist[a].add(new Node(b,c));
			adlist[b].add(new Node(a,c));
		}
		
		visit[1] = true; 		// 1방문 체크
		DFS(1);					// DFS 함수 안에서 희소배열의 최초 값세팅(자기 바로 위 부모노드까지의 정보만(거리던 노드번호던)세팅) 
		setting();				// DFS 함수에서 세팅한 자기 부모노드의 기본정보로 전체를 세팅한다.
		
		int now;
		for(int n=1; n<=N; n++) // 모든 노드에 대해서 탐색 
		{
			now = n;
			for(int m=MAX-1; m>=0; m--) // 최상위 부터 탐색하면서 내려옴 
			{
				if(energy[n] - parentDist[now][m] >= 0) 
				{
					energy[n] -= parentDist[now][m];
					now = parent[now][m];
					if(now<=1) break;
				}
			}
			sb.append(now == 0 ? 1 : now)
				.append('\n');
		}
		System.out.println(sb);
	}
}