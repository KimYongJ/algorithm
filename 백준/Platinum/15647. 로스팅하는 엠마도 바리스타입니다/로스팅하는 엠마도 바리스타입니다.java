//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1131

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node, dist; Node next;Node(int n,int d, Node t){node=n;dist=d; next=t;}
}
class Main{
	static int N;
	static long distSum;
	static int nodeCnt[];
	static long result[];
	static Node adNode[];
	static boolean visit[];


	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		nodeCnt	= new int[N+1];
		result	= new long[N+1];
		adNode	= new Node[N+1];
		
		for(int i=1; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a		= Integer.parseInt(st.nextToken());
			int b		= Integer.parseInt(st.nextToken());
			int d		= Integer.parseInt(st.nextToken());
			adNode[a]	= new Node(b, d, adNode[a]);
			adNode[b]	= new Node(a, d, adNode[b]);
		}
		visit	= new boolean[N+1];
		visit[1]= true;
		DFS1(1, 0);// 해당 DFS함수에서 1번에서 모든노드로의 총거리 합과, 각 노드당 갖는 자식 개수를 센다
		
		visit	= new boolean[N+1];
		visit[1]= true;
		DFS2(1, result[1] = distSum);

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(result[i]).append('\n');
		System.out.print(sb.toString());
	}
	public static long DFS1(int node, int dist) {
		distSum += dist;
		
		int cnt = 1;
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				cnt += DFS1(next.node, dist + next.dist);
			}
		return nodeCnt[node] = cnt;
	}
	public static void DFS2(int node, long distSum) {
		int nowNodeCnt = nodeCnt[node];
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				long plus = (N - nodeCnt[next.node]- 1) * next.dist;
				long minus= (nodeCnt[next.node] - 1)  * next.dist;
				result[next.node] = distSum + plus - minus;
				int nextNodeCnt = nodeCnt[next.node];
				nodeCnt[node] = N - nodeCnt[next.node];
				nodeCnt[next.node] = N;
				DFS2(next.node, result[next.node]);
				nodeCnt[node] = nowNodeCnt;
				nodeCnt[next.node] = nextNodeCnt;
			}
	}
}