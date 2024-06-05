//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node{
	int node, cost;
	Node(int node, int cost){this.node=node; this.cost=cost;}
}
class Main{
	static int result = Integer.MAX_VALUE;
	static int N, M, S, E, C;
	static int dist[];
	static boolean visit[];
	static ArrayList<Node>[] adlist;
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 노드 개수
		M = Integer.parseInt(st.nextToken()); // 간선 개수
		S = Integer.parseInt(st.nextToken()); // 시작 번호
		E = Integer.parseInt(st.nextToken()); // 도착 번호
		C = Integer.parseInt(st.nextToken()); // 가진 총 돈
		visit = new boolean[N+1];
		adlist = new ArrayList[N+1];
		for(int i=1; i<=N; i++) 
		{
			adlist[i] = new ArrayList<>();
		}
		int a,b,c;
		for(int m=0; m<M; m++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adlist[a].add(new Node(b,c));
			adlist[b].add(new Node(a,c));
		}
		visit[S] = true;
		backtracking(S, Integer.MIN_VALUE,C);
		
		if(result == Integer.MAX_VALUE)
			result = -1;
		System.out.print(result);
	}
	public static void backtracking(int now, int min, int cost) {
		if(now == E) {
			if(result > min)
				result = min;
			return;
		}
		if(result < min) 
			return;
		
		for(Node node : adlist[now]) {
			if(!visit[node.node] && cost - node.cost >=0) {
				visit[node.node] = true;
				backtracking(node.node, Math.max(min, node.cost),cost - node.cost);
				visit[node.node] = false;
			}
		}
		
	}
}