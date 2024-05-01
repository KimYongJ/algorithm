// https://github.com/KimYongJ/algorithm
class Node{
	int node;
	Node before;
	Node(int node, Node before){this.node=node;this.before=before;}
}
class Main{
	static Node adNode[];
	static boolean visit[];
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean DFS(int node, int beforeNode) 
	{// 양방향 연결에서 사이클 판별 : 자기 바로 이전 노드를 제외하고 방문한 곳을 재방문했다면 사이클임
		if(visit[node]) return false;
		visit[node] = true;
		for(Node now=adNode[node]; now!=null; now=now.before) {
			if(now.node == beforeNode)// 이전에 인접한 노드였다면 연산 스킵
				continue;
			if(!DFS(now.node, node)) // dfs의 결과가 false(방문한적있다면) false 리턴
				return false;
		}
		return true; // 여기까지 오면 사이클이 없는 것
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = read();
		int M = read();
		int a, b, cnt, caseCnt = 1;
		while(N != 0 || M != 0) 
		{
			adNode	= new Node[N+1];
			visit	= new boolean[N+1];
			cnt		= 0;
			for(int i=0; i<M; i++) 
			{
				a = read();
				b = read();
				adNode[a] = new Node(b, adNode[a]);
				adNode[b] = new Node(a, adNode[b]);
			}
			
			for(int i=1; i<=N; i++)
				if(!visit[i] && DFS(i,-1))
					cnt++;

			sb.append("Case ").append(caseCnt).append(": ");
			if(cnt == 0)
				sb.append("No trees.");
			else if(cnt == 1)
				sb.append("There is one tree.");
			else
				sb.append("A forest of ").append(cnt).append(" trees.");
			sb.append('\n');
			
			N  = read();
			M  = read();
			caseCnt++;
		}
		System.out.println(sb);
	}
	
}