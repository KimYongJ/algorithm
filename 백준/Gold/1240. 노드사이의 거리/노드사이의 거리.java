// https://github.com/kimyongj/algorithm
class Node{
	int node, dist;
	Node before;
	Node(int node, int dist, Node before){
		this.node = node;
		this.dist = dist;
		this.before = before;
	}
}
class Main{
	static int N, M, commonParent, parentNode[], dist[], level[];
	static Node adNode[];
	static boolean visit[];
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void parentNodeSetting_DFS(int nowNode,int parent,int distSum,int lv) {
		parentNode[nowNode] = parent;
		for(Node next=adNode[nowNode]; next!=null; next=next.before) {
			if(!visit[next.node]) {
				visit[next.node] = true;
				dist[next.node] = distSum + next.dist; // 1번 노드부터 nextNode까지 걸린 총 거리 
				level[next.node]= lv; 
				parentNodeSetting_DFS(next.node, nowNode, distSum + next.dist, lv + 1);
			}
		}
	}
	public static int getCommonParent(int start, int end) {
		int startLevel 	= level[start];
		int endLevel	= level[end];
		while(true) {
			if(startLevel == endLevel) 
			{
				if(start == end) // 공통 노드가 되었을 경우 종료 
					return start;
				startLevel 	= level[parentNode[start]];
				start 		= parentNode[start];
			}
			if(startLevel > endLevel) {
				startLevel 	= level[parentNode[start]];
				start 		= parentNode[start];
			}else {
				endLevel 	= level[parentNode[end]];
				end 		= parentNode[end];
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N 			= read();
		M 			= read();
		adNode 		= new Node[N+1];
		parentNode 	= new int[N+1];
		dist 		= new int[N+1];
		level 		= new int[N+1];
		int a,b,c, start, end;
		for(int n=1; n<N; n++) {
			a 			= read();
			b 			= read();
			c 			= read();
			adNode[a] 	= new Node(b,c,adNode[a]);
			adNode[b] 	= new Node(a,c,adNode[b]);
		}
		visit	= new boolean[N+1];
		visit[1]= true;
		parentNodeSetting_DFS(1,0,0,1); // 1번노드 부터 시작해서 DFS탐색하며 부모노드, 부모노드에서 각노드까지거리, 각 노드별 레벨을 저장한다. 
		StringBuilder sb = new StringBuilder();
		for(int m=0; m<M; m++) {
			start 	= read();
			end 	= read();
			commonParent = getCommonParent(start, end);		// LCA에서 영감을 얻은 풀이 공통 조상을 활용해 연산한다
			sb.append( (dist[start] + dist[end]) - (dist[commonParent]*2) )
			.append('\n');
		}
		System.out.println(sb);
	}
}