// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	Node before;
	Node(int node, Node before){this.node=node;this.before=before;}
}
class Main{
	static Node adNode[];
	static boolean visit[];
	public static boolean DFS(int node, int beforeNode) {
		if(visit[node]) return false;
		visit[node] = true;
		for(Node now=adNode[node]; now!=null; now=now.before) {
			if(now.node == beforeNode)
				continue;
			if(!DFS(now.node, node))
				return false;
		}
		return true;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder	sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int a, b, cnt, caseCnt = 1;
		while(N != 0 || M != 0) {
			adNode	= new Node[N+1];
			visit	= new boolean[N+1];
			cnt		= 0;
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
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
			st = new StringTokenizer(br.readLine());
			N  = Integer.parseInt(st.nextToken());
			M  = Integer.parseInt(st.nextToken());
			caseCnt++;
		}
		System.out.println(sb);
	}
	
}