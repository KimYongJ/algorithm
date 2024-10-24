//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/14657
class Node{
	int node, time;Node next;
	Node(int node, int time, Node next){this.node=node; this.next=next; this.time=time;}
}

class Main{
	
	static int N, T, maxNode, maxCnt, maxTime;
	static Node adNode[];
	static boolean visit[];

	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node, int cnt, int time) {
		if(maxCnt < cnt)
		{
			maxCnt	= cnt;
			maxTime = time;
			maxNode = node;
		}
		else if(maxCnt == cnt && time < maxTime)
		{
			maxCnt	= cnt;
			maxTime = time;
			maxNode = node;
		}
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				DFS(next.node, cnt + 1, time + next.time);
			}
	}
	public static void main(String[] args)throws Exception{
		N		= read();			// 노드 수(2<=오만)
		T		= read();			// 하루 투자 가능시간(1<=십만)
		adNode	= new Node[N+1];
		
		for(int i=1; i<N; i++)
		{
			int a = read();			// 문제번호
			int b = read();			// 문제번호
			int c = read();			// 한문제를 넘는데 걸리는 시간(1<=천)
			adNode[a] = new Node(b, c, adNode[a]);
			adNode[b] = new Node(a, c, adNode[b]);
		}
		
		visit = new boolean[N+1];
		visit[1] = true;
		DFS(1, 0, 0);
		
		visit = new boolean[N+1];
		visit[maxNode] = true;
		maxCnt	= 0;
		maxTime = 0;
		DFS(maxNode, 0, 0);
		
		System.out.print((int)Math.ceil((double)maxTime / T));
	}
}
