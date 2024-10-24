//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/14657
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node, time;Node next;
	Node(int node, int time, Node next){this.node=node; this.next=next; this.time=time;}
}

class Main{
	
	static int N, T, maxNode, maxCnt, maxTime;
	static Node adNode[];
	static boolean visit[];

	public static void DFS(int node, int cnt, int time) {
		if(maxCnt < cnt)
		{
			maxCnt = cnt;
			maxTime = time;
			maxNode = node;
		}else if(maxCnt == cnt && time < maxTime) {
			maxCnt = cnt;
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N		= Integer.parseInt(st.nextToken());	// 노드 수(2<=오만)
		T		= Integer.parseInt(st.nextToken());	// 하루 투자 가능시간(1<=십만)
		adNode	= new Node[N+1];
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	// 문제번호
			int b = Integer.parseInt(st.nextToken());	// 문제번호
			int c = Integer.parseInt(st.nextToken());	// 한문제를 넘는데 걸리는 시간(1<=천)
			adNode[a] = new Node(b, c, adNode[a]);
			adNode[b] = new Node(a, c, adNode[b]);
		}
		
		visit = new boolean[N+1];
		visit[1] = true;
		DFS(1, 0, 0);
		
		visit = new boolean[N+1];
		visit[maxNode] = true;
		maxCnt = 0;
		maxTime = 0;
		DFS(maxNode, 0, 0);
		
		System.out.print((int)Math.ceil((double)maxTime / T));
	}
}
