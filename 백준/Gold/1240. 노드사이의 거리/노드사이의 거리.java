// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
	static int N, M, start, end, dist;
	static Node adNode[];
	static boolean visit[];
	public static void DFS(int node, int sum) {
		if(dist != 0) return;
		if(node == end) {
			dist = sum;
			return;
		}
		for(Node now=adNode[node]; now!=null; now=now.before) {
			if(!visit[now.node]) {
				visit[now.node] = true;
				DFS(now.node, sum + now.dist);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adNode = new Node[N+1];
		int a,b,c;
		for(int n=1; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b,c,adNode[a]);
			adNode[b] = new Node(a,c,adNode[b]);
		}
		StringBuilder sb = new StringBuilder();
		for(int m=0; m<M; m++) {
			st 		= new StringTokenizer(br.readLine());
			start 	= Integer.parseInt(st.nextToken());
			end 	= Integer.parseInt(st.nextToken());
			dist	= 0;
			visit 	= new boolean[N+1];
			visit[start] = true;
			DFS(start,0);
			sb.append(dist).append('\n');
		}
		System.out.println(sb);
	}
}